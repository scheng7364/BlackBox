/**
 * 
 */
package blackbox.CarClasses;

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class BrakingSystem {
	
	private Boolean ParkingBrake;
	private Boolean Antilock;
	private double MasterCylinderPressureAVG;
	private double MasterCylinderPressureSTD;
	private double BrakeLinePressureAVG;
	private double BrakeLinePressureSTD;
	private Boolean WearIndicator;
	private String LastServiceDate;

	/**
	 * 
	 */
	public BrakingSystem() {
		ParkingBrake = false;
		Antilock = false;
		MasterCylinderPressureAVG = 0;		
		MasterCylinderPressureSTD = 0;
		BrakeLinePressureAVG = 0;
		BrakeLinePressureSTD = 0;
		WearIndicator = false;
		LastServiceDate = "";
	}
	
	/**
	 * 
	 * @param myPark
	 * @param myLock
	 * @param myMaster
	 * @param myLine
	 * @param myWear
	 * @param myServDate
	 */
	public BrakingSystem(Boolean myPark, Boolean myLock, double myMasterAVG, double myMasterSTD, 
			double myLineAVG, double myLineSTD, Boolean myWear, String myServDate) {
		ParkingBrake = myPark;
		Antilock = myLock;
		MasterCylinderPressureAVG = myMasterAVG;
		MasterCylinderPressureSTD = myMasterSTD;
		BrakeLinePressureAVG = myLineAVG;
		BrakeLinePressureSTD = myLineSTD;
		WearIndicator = myWear;
		LastServiceDate = myServDate;
	}

	/**
	 * @return the parkingBrake
	 */
	public Boolean getParkingBrake() {
		return ParkingBrake;
	}

	/**
	 * @param parkingBrake the parkingBrake to set
	 */
	public void setParkingBrake(Boolean parkingBrake) {
		ParkingBrake = parkingBrake;
	}

	/**
	 * @param masterCylinderPressureAVG the masterCylinderPressureAVG to set
	 */
	public void setMasterCylinderPressureAVG(double masterCylinderPressureAVG) {
		MasterCylinderPressureAVG = masterCylinderPressureAVG;
	}

	/**
	 * @param masterCylinderPressureSTD the masterCylinderPressureSTD to set
	 */
	public void setMasterCylinderPressureSTD(double masterCylinderPressureSTD) {
		MasterCylinderPressureSTD = masterCylinderPressureSTD;
	}

	/**
	 * @return the masterCylinderPressure
	 */
	public double getMasterCylinderPressure() {
		Random randomGenerator = new Random();
		return this.MasterCylinderPressureSTD*randomGenerator.nextDouble() + 
				this.MasterCylinderPressureAVG;
	}

	/**
	 * @param brakeLinePressureAVG the brakeLinePressureAVG to set
	 */
	public void setBrakeLinePressureAVG(double brakeLinePressureAVG) {
		BrakeLinePressureAVG = brakeLinePressureAVG;
	}

	/**
	 * @param brakeLinePressureSTD the brakeLinePressureSTD to set
	 */
	public void setBrakeLinePressureSTD(double brakeLinePressureSTD) {
		BrakeLinePressureSTD = brakeLinePressureSTD;
	}

	/**
	 * @return the brakeLinePressure
	 */
	public double getBrakeLinePressure() {
		Random randomGenerator = new Random();
		return this.BrakeLinePressureSTD*randomGenerator.nextDouble() + 
				this.BrakeLinePressureAVG;
	}
	
	/**
	 * @return the wearIndicator
	 */
	public Boolean getWearIndicator() {
		return WearIndicator;
	}

	/**
	 * @param wearIndicator the wearIndicator to set
	 */
	public void setWearIndicator(Boolean wearIndicator) {
		WearIndicator = wearIndicator;
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

	/**
	 * @return the Anti-lock
	 */
	public Boolean getAntilock() {
		return Antilock;
	}

	/**
	 * @param antilock the Anti-lock to set
	 */
	public void setAntilock(Boolean antilock) {
		Antilock = antilock;
	}


}
