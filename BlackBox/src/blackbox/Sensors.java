/**
 * @(#)Sensors.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox;

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
	
	Connection connection1 = ConnectionSqlite.dbConnector();

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

	// Create an array list for storing the data from OBD2Port
	public void createArrayList() {
		for (int i = 0; i < size; i++) {
			list.add(i, "0.0");
		}
	}

	// For debugging
	/*public void printlist() {
		for (int i = 0; i < size; i++) {
			String item = list.get(i);
			System.out.println("value at " + i + " " + item);
		}
	}*/

	// Set the Car to start or stop
	public boolean setStarting(boolean startornot) {
		return this.starting = startornot;
	}

	// Get the stop or start status of the car
	public boolean getStarting() {
		return this.starting;
	}

	// Get the drivers coefficient from database (this is for simulation purpose) 
	public double getDriverCoeff() {
		coeff = this.myProfile.getCoeff();
		return coeff;
	}

	// Get the username of the logged-in user & write value into respective column in data table
	public String getUsername() {
		String name = myProfile.getUsername();
		list.set(0, name);
		return name;
	}

	// Get "pass" or "warning" status of the report & write value into respective column in data table
	public String getStatus() {
		list.set(1, status);
		return this.status;
	}

	// To get car speed & write value into respective column in data table
	public double getCarSpeed() {
		// Driver's coefficient is here to simulate different values for different user
		coeff = this.getDriverCoeff();
		double speed = coeff * obd.readDoubleData("Speed");
		list.set(2, formatter.format(speed));
		return speed;
	}

	// To get Car RPM (For Engine) & write value into respective column in data table
	public double getCarRPM() {
		// Driver's coefficient is here to simulate different values for different user
		coeff = this.getDriverCoeff();
		double rpm = coeff * obd.readDoubleData("RPM");
		list.set(3, formatter.format(rpm));
		return rpm;
	}

	// To get Car Int Air Temp (For Cooling System) & write value into respective column in data table
	public double getCarIntAirTemp() {
		// Driver's coefficient is here to simulate different values for different user
		coeff = this.getDriverCoeff();
		double temp = coeff * obd.readDoubleData("IntAirTemp");
		list.set(4, formatter.format(temp));
		return temp;
	}

	// To get Car Oil Level (For Engine) & write value into respective column in data table
	public double getCarOilLevel() {
		// Driver's coefficient is here to simulate different values for different user
		coeff = this.getDriverCoeff();
		double ol = coeff * obd.readDoubleData("OilLevel");
		list.set(5, formatter.format(ol));
		return ol;
	}

	// To get Car Fuel Level (For Fuel System) & write value into respective column in data table
	public double getCarFuelLevel() {
		// Driver's coefficient is here to simulate different values for different user
		coeff = this.getDriverCoeff();
		double fl = coeff * obd.readDoubleData("FuelLevel");
		list.set(6, formatter.format(fl));
		return fl;
	}

	// To get Tires pressure & write value into respective column in data table
	public double[] getTiresPressure() {
		// Driver's coefficient is here to simulate different values for different user
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
		// Get the threshold values
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

		// If the below situation occurs (i.e. exceeding the threshold range, "Warning" sign will be shown in the real time monitor)
		if (currSpeed == 0)
			this.setStarting(true);
		else
			this.setStarting(false);

		if (currRPM >= threshold.getMaxRPM() || currRPM < threshold.getMinRPM()) {
			healthy = false;
		}
		
		for (int i = 0; i < 4; i++) {
			if (currTire[i] >= threshold.getMaxTirePressure() || currTire[i] < threshold.getMinTirePressure()) {
				healthy = false;
				break;
			}
		}
		
		if (currFuel < threshold.getMinFuelLevel()) {
			healthy = false;
		}
		
		if (currTemp > threshold.getMaxTemperature() || currTemp < threshold.getMinTemperature()) {
			healthy = false;
		}

		if (currOil < threshold.getMinOilLevelSensor() || currOil >= threshold.getMaxOilLevelSensor()) {
			healthy = false;
		}
		
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

				// Write into database. Index and item are matched so for loop can be used 
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

	// Get the total number of columns of a data table
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
		return size;
	}

}