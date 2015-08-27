/**
 * @(#)ExhaustSystem.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox.CarClasses;

import java.util.Random;

public class ExhaustSystem {
	
	private double firstOxygenSensorAVG;
	private double firstOxygenSensorSTD;
	private double secondOxygenSensorAVG;
	private double secondOxygenSensorSTD;
	private double catalyticConverterSensorAVG;
	private double catalyticConverterSensorSTD;
	private String lastServiceDate;
	
	public ExhaustSystem() {
		
		firstOxygenSensorAVG = 0;
		firstOxygenSensorSTD = 0;
		secondOxygenSensorAVG = 0;
		secondOxygenSensorSTD = 0;
		catalyticConverterSensorAVG = 0;
		catalyticConverterSensorSTD = 0;
		lastServiceDate = "";

	}


	public ExhaustSystem(double myFirstO2SensorAVG, double myFirstO2SensorSTD, double mySecondO2SensorAVG, 
			double mySecondO2SensorSTD, double myCatSensorAVG, double myCatSensorSTD, String myServDate) {
		
		firstOxygenSensorAVG = myFirstO2SensorAVG;
		firstOxygenSensorSTD = myFirstO2SensorSTD;
		secondOxygenSensorAVG = mySecondO2SensorAVG;
		secondOxygenSensorSTD = mySecondO2SensorSTD;
		catalyticConverterSensorAVG = myCatSensorAVG;
		catalyticConverterSensorSTD = myCatSensorSTD;
		lastServiceDate = myServDate;

	}

	/**
	 * @param firstOxygenSensorAVG the firstOxygenSensorAVG to set
	 */
	public void setFirstOxygenSensorAVG(double firstOxygenSensorAVG) {
		this.firstOxygenSensorAVG = firstOxygenSensorAVG;
	}

	/**
	 * @param firstOxygenSensorSTD the firstOxygenSensorSTD to set
	 */
	public void setFirstOxygenSensorSTD(double firstOxygenSensorSTD) {
		this.firstOxygenSensorSTD = firstOxygenSensorSTD;
	}

	/**
	 * @return the firstOxygenSensor
	 */
	public double getFirstOxygenSensor() {
		Random randomGenerator = new Random();
		return this.firstOxygenSensorSTD*randomGenerator.nextDouble() + 
				this.firstOxygenSensorAVG;
	}

	/**
	 * @param secondOxygenSensorAVG the secondOxygenSensorAVG to set
	 */
	public void setSecondOxygenSensorAVG(double secondOxygenSensorAVG) {
		this.secondOxygenSensorAVG = secondOxygenSensorAVG;
	}

	/**
	 * @param secondOxygenSensorSTD the secondOxygenSensorSTD to set
	 */
	public void setSecondOxygenSensorSTD(double secondOxygenSensorSTD) {
		this.secondOxygenSensorSTD = secondOxygenSensorSTD;
	}

	/**
	 * @return the secondOxygenSensor
	 */
	public double getSecondOxygenSensor() {
		Random randomGenerator = new Random();
		return this.secondOxygenSensorSTD*randomGenerator.nextDouble() + 
				this.secondOxygenSensorAVG;
	}

	/**
	 * @param catalyticConverterSensorAVG the catalyticConverterSensorAVG to set
	 */
	public void setCatalyticConverterSensorAVG(double catalyticConverterSensorAVG) {
		this.catalyticConverterSensorAVG = catalyticConverterSensorAVG;
	}

	/**
	 * @param catalyticConverterSensorSTD the catalyticConverterSensorSTD to set
	 */
	public void setCatalyticConverterSensorSTD(double catalyticConverterSensorSTD) {
		this.catalyticConverterSensorSTD = catalyticConverterSensorSTD;
	}

	/**
	 * @return the catalyticConverterSensor
	 */
	public double getCatalyticConverterSensor() {
		Random randomGenerator = new Random();
		return this.catalyticConverterSensorSTD*randomGenerator.nextDouble() + 
				this.catalyticConverterSensorAVG;
	}

	/**
	 * @return the lastServiceDate
	 */
	public String getLastServiceDate() {
		return lastServiceDate;
	}


	/**
	 * @param lastServiceDate the lastServiceDate to set
	 */
	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}


	public double getFirstOxygenSensorAVG() {
		return firstOxygenSensorAVG;
	}


	public double getFirstOxygenSensorSTD() {
		return firstOxygenSensorSTD;
	}
	
	
}
