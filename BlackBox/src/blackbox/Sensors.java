package blackbox;

import java.util.Observable;

import blackbox.CarClasses.Car;

public class Sensors extends Observable {
	private OBD2Port obd;
	private Car myCar;
	private DriverProfile myProfile;
	private boolean warning = false;
	private boolean healthy = true;
	//This Car should be passed to Sensors as Constructor (bcz only one car)
	//Car myCar = new Honda();  	//if we decide to go with more than one car type we should
								//pass in car types.  This seems to go for many classes.
	
	private double[] tires = new double[4];
	private double coeff = 1.0;

	public Sensors(OBD2Port obd2p, Car car, DriverProfile profile) {
		obd = obd2p;
		myCar = car;
		myProfile = profile;
	//	coeff = this.myProfile.getCoeff();
	}

	public double getDriverCoeff() {
		coeff = this.myProfile.getCoeff();
		System.out.println(coeff);
		return 	coeff;
	}
	
	// To get car speed
	public double getCarSpeed() {
		coeff = this.getDriverCoeff();
		return (coeff * obd.readDoubleData("Speed"));
	}

	// To get Tires pressure
	public double[] getTiresPressure() {

		coeff = this.getDriverCoeff();
		
		tires[0] = (coeff * obd.readDoubleData("TirePressure_LF"));
		tires[1] = (coeff * obd.readDoubleData("TirePressure_LR"));
		tires[2] = (coeff * obd.readDoubleData("TirePressure_RF"));
		tires[3] = (coeff * obd.readDoubleData("TirePressure_RR"));

		return tires;

	}

	// To get Car RPM (For Engine)
	public double getCarRPM() {
		coeff = this.getDriverCoeff();
		return (coeff * obd.readDoubleData("RPM"));
	}

	// To get Car Oil Level (For Engine)
	public double getCarOilLevel() {
		coeff = this.getDriverCoeff();
		return (coeff * obd.readDoubleData("OilLevel"));
	}
	
	// To get Car Fuel Level (For Fuel System)
	public double getCarFuelLevel() {
		coeff = this.getDriverCoeff();
		return (coeff * obd.readDoubleData("FuelLevel"));
	}
	
	// To get Car Int Air Temp (For Cooling System) 
	public double getCarIntAirTemp() {
		coeff = this.getDriverCoeff();
		return (coeff * obd.readDoubleData("IntAirTemp"));
	}
	
	// To check if Car is healthy
	public void ifHealthy() {

		MaxMinValues threshold = new MaxMinValues(myCar);
		healthy = true;

		double currRPM = this.getCarRPM();
		double currOil = this.getCarOilLevel();
		double currFuel = this.getCarFuelLevel();
		double[] currTire = this.getTiresPressure();
		
		if (currRPM >= threshold.getMaxRPM() ||
				currRPM < threshold.getMinRPM()) {
			healthy = false;
		}
		
		for (int i = 0; i < 4; i++) {
			if (currTire[i] >= threshold.getMaxTirePressure() || 
					currTire[i] < threshold.getMinTirePressure()) {
				healthy = false;
				break;
			}
		}

		if (currFuel < threshold.getMinFuelLevel()) {
			healthy = false;
		}
		
		if (currOil < threshold.getMinOilLevelSensor() ||
				currOil >= threshold.getMaxOilLevelSensor()) {
			healthy = false;
		}
						
		// Inform Sensors Monitor for the state change
		setChanged();
		notifyObservers(new Boolean(healthy));
	}
	
	// If healthy becomes false, Sensors Monitor will set warning to true
	public void setWarning(boolean ifwarning) {
		
		warning = ifwarning;
		
	}
	
	public boolean getWarning() {
		return warning;
	}
}