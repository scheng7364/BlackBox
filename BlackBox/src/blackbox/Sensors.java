package blackbox;

import java.util.Observable;

public class Sensors extends Observable {

	private BlackBoxSystem bt;
	
	private double[] tires = new double[4];

	public Sensors() {
	}

	// To get car speed
	public double getCarSpeed() {

		return bt.thisOBD.readDoubleData("Speed");
	}

	// To get Tires pressure
	public double[] getTiresPressure() {

		tires[0] = bt.thisOBD.readDoubleData("TirePressure_LF");
		tires[1] = bt.thisOBD.readDoubleData("TirePressure_LR");
		tires[2] = bt.thisOBD.readDoubleData("TirePressure_RF");
		tires[3] = bt.thisOBD.readDoubleData("TirePressure_RR");

		return tires;

	}

	// To get Car RPM (For Engine)
	public double getCarRPM() {
		return bt.thisOBD.readDoubleData("RPM");
	}

	// To get Car Oil Level (For Engine)
	public double getCarOilLevel() {
		return bt.thisOBD.readDoubleData("OilLevel");
	}
	
	// To get Car Fuel Level (For Fuel System)
	public double getCarFuelLevel() {
		return bt.thisOBD.readDoubleData("FuelLevel");
	}
	
	// To get Car Int Air Temp (For Cooling System) 
	public double getCarIntAirTemp() {
		return bt.thisOBD.readDoubleData("IntAirTemp");
	}
	
	
	public boolean ifHealthy() {

		for (int i = 0; i < 4; i++) {
			if (tires[i] >= 45) {
				return false;
			}
		}

		if (this.getCarRPM() >= 5800) {
			return false;
		}

		return true;
	}
}
