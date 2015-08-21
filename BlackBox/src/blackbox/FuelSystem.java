/**
 * 
 */
package blackbox;

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class FuelSystem {

	private double FuelLevelAVG;
	private double FuelLevelSTD;
	private double FuelToAirRatioAVG;
	private double FuelToAirRatioSTD;
	private double FuelPressureAVG;
	private double FuelPressureSTD;
	private String lastServiceDate;
	
	
	/**
	 * 
	 */
	public FuelSystem() {
		FuelLevelAVG = 0;
		FuelLevelSTD = 0;
		FuelToAirRatioAVG = 0;
		FuelToAirRatioSTD = 0;
		FuelPressureAVG = 0;
		FuelPressureSTD = 0;
		lastServiceDate = "";
	}

	/**
	 * 
	 * @param myFuelLevelAVG
	 * @param myFuelLevelSTD
	 * @param myFuelToAirRatioAVG
	 * @param myFuelToAirRatioSTD
	 * @param myFuelPressureAVG
	 * @param myFuelPressureSTD
	 * @param myServDate
	 */
	public FuelSystem(double myFuelLevelAVG, double myFuelLevelSTD, double myFuelToAirRatioAVG, 
			double myFuelToAirRatioSTD, double myFuelPressureAVG, double myFuelPressureSTD, String myServDate) {
		FuelLevelAVG = myFuelLevelAVG;
		FuelLevelSTD = myFuelLevelSTD;
		FuelToAirRatioAVG = myFuelToAirRatioAVG;
		FuelToAirRatioSTD = myFuelToAirRatioSTD;
		FuelPressureAVG = myFuelPressureAVG;
		FuelPressureSTD = myFuelPressureSTD;
		lastServiceDate = myServDate;
	}

	/**
	 * @param fuelLevelAVG the fuelLevelAVG to set
	 */
	public void setFuelLevelAVG(double fuelLevelAVG) {
		FuelLevelAVG = fuelLevelAVG;
	}

	/**
	 * @param fuelLevelSTD the fuelLevelSTD to set
	 */
	public void setFuelLevelSTD(double fuelLevelSTD) {
		FuelLevelSTD = fuelLevelSTD;
	}

	/**
	 * @return the fuelLevel
	 */
	public double getFuelLevel() {
		Random randomGenerator = new Random();
		return this.FuelLevelSTD*randomGenerator.nextDouble() + 
				this.FuelLevelAVG;
	}

	/**
	 * @param fuelToAirRatioAVG the fuelToAirRatioAVG to set
	 */
	public void setFuelToAirRatioAVG(double fuelToAirRatioAVG) {
		FuelToAirRatioAVG = fuelToAirRatioAVG;
	}

	/**
	 * @param fuelToAirRatioSTD the fuelToAirRatioSTD to set
	 */
	public void setFuelToAirRatioSTD(double fuelToAirRatioSTD) {
		FuelToAirRatioSTD = fuelToAirRatioSTD;
	}

	/**
	 * @return the fuelToAirRatio
	 */
	public double getFuelToAirRatio() {
		Random randomGenerator = new Random();
		return this.FuelToAirRatioSTD*randomGenerator.nextDouble() + 
				this.FuelToAirRatioAVG;
	}

	/**
	 * @param fuelPressureAVG the fuelPressureAVG to set
	 */
	public void setFuelPressureAVG(double fuelPressureAVG) {
		FuelPressureAVG = fuelPressureAVG;
	}

	/**
	 * @param fuelPressureSTD the fuelPressureSTD to set
	 */
	public void setFuelPressureSTD(double fuelPressureSTD) {
		FuelPressureSTD = fuelPressureSTD;
	}

	/**
	 * @return the fuelPressure
	 */
	public double getFuelPressure() {
		Random randomGenerator = new Random();
		return this.FuelPressureSTD*randomGenerator.nextDouble() + 
				this.FuelPressureAVG;
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

	public double getFuelLevelAVG() {
		return FuelLevelAVG;
	}

	public double getFuelLevelSTD() {
		return FuelLevelSTD;
	}
	
	

}
