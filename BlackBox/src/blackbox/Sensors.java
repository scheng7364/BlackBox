package blackbox;

import java.awt.Cursor;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JOptionPane;

import blackbox.CarClasses.Car;

public class Sensors extends Observable {
	private OBD2Port obd;
	private Car myCar;
	private DriverProfile myProfile;
	private boolean starting;
	private boolean healthy = true;
	private String status = "Pass";

	private double[] tires = new double[4];
	private double coeff = 1.0;

	private int size; // size of arraylist
	private ArrayList<String> list;
	
	Connection connection1 = sqliteConnection.dbConnector();

	// Formate Double to show one digit
	DecimalFormat formatter = new DecimalFormat("#0.0");

	public Sensors(OBD2Port obd2p, Car car, DriverProfile profile) {
		obd = obd2p;
		myCar = car;
		myProfile = profile;

		size = this.getColNum();
		list = new ArrayList<String>(size);
		this.createArrayList();
	}

	public void createArrayList() {

		for (int i = 0; i < size; i++) {
			list.add(i, "0.0");
		}
	}

	public void printlist() {
		for (int i = 0; i < size; i++) {
			String item = list.get(i);
			System.out.println("value at " + i + " " + item);
		}
	}

	public boolean setStarting(boolean startornot) {
		return this.starting = startornot;
	}

	public boolean getStarting() {
		return this.starting;
	}

	public double getDriverCoeff() {
		coeff = this.myProfile.getCoeff();
		System.out.println("From Sensors: " + coeff);
		return coeff;
	}

	public String getUsername() {
		String name = myProfile.getUsername();
		list.set(0, name);
		return name;
	}

	public String getStatus() {
		list.set(1, status);
		return this.status;
	}

	// To get car speed
	public double getCarSpeed() {
		coeff = this.getDriverCoeff();
		double speed = coeff * obd.readDoubleData("Speed");
		list.set(2, formatter.format(speed));
		return speed;
	}

	// To get Car RPM (For Engine)
	public double getCarRPM() {
		coeff = this.getDriverCoeff();
		double rpm = coeff * obd.readDoubleData("RPM");
		list.set(3, formatter.format(rpm));

		return rpm;
	}

	// To get Car Int Air Temp (For Cooling System)
	public double getCarIntAirTemp() {
		coeff = this.getDriverCoeff();
		double temp = coeff * obd.readDoubleData("IntAirTemp");
		list.set(4, formatter.format(temp));
		return temp;
	}

	// To get Car Oil Level (For Engine)
	public double getCarOilLevel() {
		coeff = this.getDriverCoeff();
		double ol = coeff * obd.readDoubleData("OilLevel");
		list.set(5, formatter.format(ol));
		return ol;
	}

	// To get Car Fuel Level (For Fuel System)
	public double getCarFuelLevel() {
		coeff = this.getDriverCoeff();
		double fl = coeff * obd.readDoubleData("FuelLevel");
		list.set(6, formatter.format(fl));
		return fl;
	}

	// To get Tires pressure
	public double[] getTiresPressure() {

		coeff = this.getDriverCoeff();

		tires[0] = (coeff * obd.readDoubleData("TirePressure_LF"));
		tires[1] = (coeff * obd.readDoubleData("TirePressure_RF"));
		tires[2] = (coeff * obd.readDoubleData("TirePressure_LR"));
		tires[3] = (coeff * obd.readDoubleData("TirePressure_RR"));

		list.set(7, formatter.format(tires[0]));
		list.set(8, formatter.format(tires[1]));
		list.set(9, formatter.format(tires[2]));
		list.set(10, formatter.format(tires[3]));

		return tires;

	}

	// To check if Car is healthy
	public void ifHealthy() {

		MaxMinValues threshold = new MaxMinValues(myCar);
		healthy = true;

		String username = this.getUsername();
		String status = this.getStatus();

		double currSpeed = this.getCarSpeed();
		double currRPM = this.getCarRPM();
		double currTemp = this.getCarIntAirTemp();
		double currOil = this.getCarOilLevel();
		double currFuel = this.getCarFuelLevel();
		double[] currTire = this.getTiresPressure();

		if (currSpeed == 0)
			this.setStarting(true);
		else
			this.setStarting(false);

		// System.out.println("starting? " + starting);

		if (currRPM >= threshold.getMaxRPM() || currRPM < threshold.getMinRPM()) {
			healthy = false;
		}
		System.out.println(healthy);

		for (int i = 0; i < 4; i++) {
			if (currTire[i] >= threshold.getMaxTirePressure() || currTire[i] < threshold.getMinTirePressure()) {
				healthy = false;
				break;
			}
		}
		System.out.println(healthy);
		if (currFuel < threshold.getMinFuelLevel()) {
			healthy = false;
		}

		System.out.println(healthy);

		if (currTemp > threshold.getMaxTemperature() || currTemp < threshold.getMinTemperature()) {
			healthy = false;
		}

		if (currOil < threshold.getMinOilLevelSensor() || currOil >= threshold.getMaxOilLevelSensor()) {
			healthy = false;
		}
		System.out.println(healthy);

		// Inform Sensors Monitor for the state change
		setChanged();
		notifyObservers(new Boolean(healthy));

		if (healthy == false)
			list.set(1, "Warning");

		// for debugging
		// this.printlist();

		// Pass the values to database
		if (currSpeed != 0) {
			try {
				// Send query to database
				String query = "Insert into logsheet(username, reportStatus, carSpeed, carRPM, carTemp, carOil, carFuel, carTFL, carTFR, carTRL, carTRR) values(?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = connection1.prepareStatement(query);

				// Write into database
				for (int i = 0; i < size; i++) {
					String item = list.get(i);
					int index = i + 1;
					pst.setString(index, item);
				}

				pst.executeUpdate();

				pst.close();

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, ex);
			}

		}
	}

	public int getColNum() {
		int colnum = 0;
		try {
			String querycol = "Select * from logSheet";
			PreparedStatement pst = connection1.prepareStatement(querycol);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			colnum = rsmd.getColumnCount();
			rs.close();
			pst.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		size = colnum;
		System.out.println(size);

		return size;
	}

}