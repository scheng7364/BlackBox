/**
 * @(#)SteeringSystem.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox.CarClasses;


import java.util.Random;

public class SteeringSystem {
	
	private String SteeringType;
	private double SteeringFluidLevelAVG;
	private double SteeringFluidLevelSTD;
	private double MaxFluid;
	private double MinFluid;
	private double FluidPressureAVG;
	private double FluidPressureSTD;
	private double MaxPressure;
	private double MinPressure;
	private String LastServiceDate;

	public SteeringSystem() {
		SteeringType = "";
		SteeringFluidLevelAVG = 0;
		SteeringFluidLevelSTD = 0;
		MaxFluid = 0;
		MinFluid = 0;
		FluidPressureAVG = 0;
		FluidPressureSTD = 0;
		MaxPressure = 0;
		MinPressure = 0;
		LastServiceDate = "";
	}

	/**
	 * 
	 * @param myType
	 * @param myFluidLevelAVG
	 * @param myFluidLevelSTD
	 * @param myMaxFluid
	 * @param myMinFluid
	 * @param myPressureAVG
	 * @param myPressureSTD
	 * @param myMaxPress
	 * @param myMinPress
	 * @param myServDate
	 */
	public SteeringSystem(String myType, double myFluidLevelAVG, double myFluidLevelSTD, 
			double myMaxFluid, double myMinFluid, double myPressureAVG, double myPressureSTD,
			double myMaxPress, double myMinPress, String myServDate) {
		SteeringType = myType;
		SteeringFluidLevelAVG = myFluidLevelAVG;
		SteeringFluidLevelSTD = myFluidLevelSTD;
		MaxFluid = myMaxFluid;
		MinFluid = myMinFluid;
		FluidPressureAVG = myPressureAVG;
		FluidPressureSTD = myPressureSTD;
		MaxPressure = myMaxPress;
		MinPressure = myMinPress;
		LastServiceDate = myServDate;
	}

	/**
	 * @return the steeringType
	 */
	public String getSteeringType() {
		return SteeringType;
	}


	/**
	 * @param steeringType the steeringType to set
	 */
	public void setSteeringType(String steeringType) {
		SteeringType = steeringType;
	}

	/**
	 * @param steeringFluidLevelAVG the steeringFluidLevelAVG to set
	 */
	public void setSteeringFluidLevelAVG(double steeringFluidLevelAVG) {
		SteeringFluidLevelAVG = steeringFluidLevelAVG;
	}

	/**
	 * @param steeringFluidLevelSTD the steeringFluidLevelSTD to set
	 */
	public void setSteeringFluidLevelSTD(double steeringFluidLevelSTD) {
		SteeringFluidLevelSTD = steeringFluidLevelSTD;
	}

	/**
	 * @return the steeringFluidLevel
	 */
	public double getSteeringFluidLevel() {
		Random randomGenerator = new Random();
		return this.SteeringFluidLevelSTD*randomGenerator.nextDouble() + 
				this.SteeringFluidLevelAVG;
	}

	/**
	 * @return the maxFluid
	 */
	public double getMaxFluid() {
		return MaxFluid;
	}


	/**
	 * @param maxFluid the maxFluid to set
	 */
	public void setMaxFluid(double maxFluid) {
		MaxFluid = maxFluid;
	}


	/**
	 * @return the minFluid
	 */
	public double getMinFluid() {
		return MinFluid;
	}


	/**
	 * @param minFluid the minFluid to set
	 */
	public void setMinFluid(double minFluid) {
		MinFluid = minFluid;
	}

	/**
	 * @param fluidPressureAVG the fluidPressureAVG to set
	 */
	public void setFluidPressureAVG(double fluidPressureAVG) {
		FluidPressureAVG = fluidPressureAVG;
	}

	/**
	 * @param fluidPressureSTD the fluidPressureSTD to set
	 */
	public void setFluidPressureSTD(double fluidPressureSTD) {
		FluidPressureSTD = fluidPressureSTD;
	}

	/**
	 * @return the fluidPressure
	 */
	public double getFluidPressure() {
		Random randomGenerator = new Random();
		return this.FluidPressureSTD*randomGenerator.nextDouble() + 
				this.FluidPressureAVG;
	}

	/**
	 * @return the maxPressure
	 */
	public double getMaxPressure() {
		return MaxPressure;
	}


	/**
	 * @param maxPressure the maxPressure to set
	 */
	public void setMaxPressure(double maxPressure) {
		MaxPressure = maxPressure;
	}


	/**
	 * @return the minPressure
	 */
	public double getMinPressure() {
		return MinPressure;
	}


	/**
	 * @param minPressure the minPressure to set
	 */
	public void setMinPressure(double minPressure) {
		MinPressure = minPressure;
	}


	/**
	 * @return the lastServiceDate
	 */
	public String getLastServiceDate() {
		return LastServiceDate;
	}


	/**
	 * @param lastServiceDate the lastServiceDate to set
	 */
	public void setLastServiceDate(String lastServiceDate) {
		LastServiceDate = lastServiceDate;
	}
	
}
