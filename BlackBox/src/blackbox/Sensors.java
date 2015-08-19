package blackbox;

import java.util.Observable;

public class Sensors extends Observable {

	private BlackBoxTester bt;
	private OBD2Port obd = new OBD2Port(bt.thisCar);

	private double[] tires = new double[4];

	public Sensors() {
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

	// to get Car RPM
	public double getCarRPM() {
		return obd.readDoubleData("RPM");
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
