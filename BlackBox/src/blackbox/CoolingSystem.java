/**
 * 
 */
package blackbox;

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class CoolingSystem {
	
	private double temperatureAVG;
	private double temperatureSTD;
	private double maxTemperature;
	private double minTemperature;
	private double fanSpeedAVG;
	private double fanSpeedSTD;
	private double reserveFluidLevelAVG;
	private double reserveFluidLevelSTD;
	private double maxFluidLevel;
	private double minFluidLevel;
	private String lastServiceDate;

	/**
	 * 
	 */
	public CoolingSystem() {
		
		temperatureAVG = 0;
		temperatureSTD = 0;
		maxTemperature = 0;
		minTemperature = 0;
		fanSpeedAVG = 0;
		fanSpeedSTD = 0;
		reserveFluidLevelAVG = 0;
		reserveFluidLevelSTD = 0;
		maxFluidLevel = 0;
		minFluidLevel = 0;
		lastServiceDate = "";
		
	}

	/**
	 * 
	 * @param myTemp
	 * @param myMaxTemp
	 * @param myMinTemp
	 * @param myFanSpeed
	 * @param myFluidLevel
	 * @param myMaxFluid
	 * @param myMinFluid
	 * @param myServDate
	 */
	public CoolingSystem(double myTempAVG, double myTempSTD, double myMaxTemp, double myMinTemp, 
			double myFanSpeedAVG, double myFanSpeedSTD, double myFluidLevelAVG, double myFluidLevelSTD, 
			double myMaxFluid, double myMinFluid, String myServDate) {
		
		temperatureAVG = myTempAVG;
		temperatureSTD = myTempSTD;
		maxTemperature = myMaxTemp;
		minTemperature = myMinTemp;
		fanSpeedAVG = myFanSpeedAVG;
		fanSpeedSTD = myFanSpeedSTD;
		reserveFluidLevelAVG = myFluidLevelAVG;
		reserveFluidLevelSTD = myFluidLevelSTD;
		maxFluidLevel = myMaxFluid;
		minFluidLevel = myMinFluid;
		lastServiceDate = myServDate;
		
	}

	/**
	 * @param temperatureAVG the temperatureAVG to set
	 */
	public void setTemperatureAVG(double temperatureAVG) {
		this.temperatureAVG = temperatureAVG;
	}

	/**
	 * @param temperatureSTD the temperatureSTD to set
	 */
	public void setTemperatureSTD(double temperatureSTD) {
		this.temperatureSTD = temperatureSTD;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		Random randomGenerator = new Random();
		return this.temperatureSTD*randomGenerator.nextDouble() + 
				this.temperatureAVG;
	}

	/**
	 * @return the maxTemperature
	 */
	public double getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * @return the minTemperature
	 */
	public double getMinTemperature() {
		return minTemperature;
	}

	/**
	 * @param minTemperature the minTemperature to set
	 */
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 * @param fanSpeedAVG the fanSpeedAVG to set
	 */
	public void setFanSpeedAVG(double fanSpeedAVG) {
		this.fanSpeedAVG = fanSpeedAVG;
	}

	/**
	 * @param fanSpeedSTD the fanSpeedSTD to set
	 */
	public void setFanSpeedSTD(double fanSpeedSTD) {
		this.fanSpeedSTD = fanSpeedSTD;
	}
	
	/**
	 * @return the fanSpeed
	 */
	public double getFanSpeed() {
		Random randomGenerator = new Random();
		return this.fanSpeedSTD*randomGenerator.nextDouble() + 
				this.fanSpeedAVG;
	}

	/**
	 * @param reserveFluidLevelAVG the reserveFluidLevelAVG to set
	 */
	public void setReserveFluidLevelAVG(double reserveFluidLevelAVG) {
		this.reserveFluidLevelAVG = reserveFluidLevelAVG;
	}

	/**
	 * @param reserveFluidLevelSTD the reserveFluidLevelSTD to set
	 */
	public void setReserveFluidLevelSTD(double reserveFluidLevelSTD) {
		this.reserveFluidLevelSTD = reserveFluidLevelSTD;
	}

	/**
	 * @return the reserveFluidLevel
	 */
	public double getReserveFluidLevel() {
		Random randomGenerator = new Random();
		return this.reserveFluidLevelSTD*randomGenerator.nextDouble() + 
				this.reserveFluidLevelAVG;
	}

	/**
	 * @return the maxFluidLevel
	 */
	public double getMaxFluidLevel() {
		return maxFluidLevel;
	}

	/**
	 * @param maxFluidLevel the maxFluidLevel to set
	 */
	public void setMaxFluidLevel(double maxFluidLevel) {
		this.maxFluidLevel = maxFluidLevel;
	}

	/**
	 * @return the minFluidLevel
	 */
	public double getMinFluidLevel() {
		return minFluidLevel;
	}

	/**
	 * @param minFluidLevel the minFluidLevel to set
	 */
	public void setMinFluidLevel(double minFluidLevel) {
		this.minFluidLevel = minFluidLevel;
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

}
