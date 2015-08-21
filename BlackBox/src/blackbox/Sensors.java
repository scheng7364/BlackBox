package blackbox;

import java.util.Observable;

public class Sensors extends Observable {
	private OBD2Port obd;
	private boolean warning = false;
	private boolean healthy = true;
	
	private double[] tires = new double[4];

	public Sensors(OBD2Port obd2p) {
		obd = obd2p;
	}

	// To get car speed
	public double getCarSpeed() {
		return obd.readDoubleData("Speed");
	}

	// To get Tires pressure
	public double[] getTiresPressure() {

		tires[0] = obd.readDoubleData("TirePressure_LF");
		tires[1] = obd.readDoubleData("TirePressure_LR");
		tires[2] = obd.readDoubleData("TirePressure_RF");
		tires[3] = obd.readDoubleData("TirePressure_RR");

		return tires;

	}

	// To get Car RPM (For Engine)
	public double getCarRPM() {
		return obd.readDoubleData("RPM");
	}

	// To get Car Oil Level (For Engine)
	public double getCarOilLevel() {
		return obd.readDoubleData("OilLevel");
	}
	
	// To get Car Fuel Level (For Fuel System)
	public double getCarFuelLevel() {
		return obd.readDoubleData("FuelLevel");
	}
	
	// To get Car Int Air Temp (For Cooling System) 
	public double getCarIntAirTemp() {
		return obd.readDoubleData("IntAirTemp");
	}
	
	// To check if Car is healthy
	public void ifHealthy() {
		
		healthy = true;

		for (int i = 0; i < 4; i++) {
			if (tires[i] >= StandardValues.TIRE.getSV()) {
				healthy = false;
			}
		}

		if (this.getCarRPM() >= StandardValues.RPM.getSV()) {
			healthy = false;
		}

		if (this.getCarFuelLevel() < StandardValues.FL.getSV()) {
			healthy = false;
		}
		
		if (this.getCarOilLevel() < StandardValues.OL.getSV()) {
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
		return this.warning;
	}
}
