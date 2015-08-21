/**
 * 
 */
package blackbox;

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class IgnitionSystem {
	
	private Boolean IgnitionSwitch;
	private Boolean ChargingSystemLight;
	private double BatteryVoltageAVG;
	private double BatteryVoltageSTD;
	private double AlternatorVoltageAVG;
	private double AlternatorVoltageSTD;
	private String LastServiceDate;

	/**
	 * 
	 */
	public IgnitionSystem() {
		IgnitionSwitch = false;
		ChargingSystemLight = false;
		BatteryVoltageAVG = 0;
		BatteryVoltageSTD = 0;
		AlternatorVoltageAVG = 0;
		AlternatorVoltageSTD = 0;
		LastServiceDate = "";
	}

	/**
	 * 
	 * @param myOnSwitch
	 * @param myChargeLight
	 * @param myBatVoltAVG
	 * @param myBatVoltSTD
	 * @param myAltVoltAVG
	 * @param myAltVoltSTD
	 * @param myServDate
	 */
	public IgnitionSystem(Boolean myOnSwitch, Boolean myChargeLight, double myBatVoltAVG, double myBatVoltSTD, 
			double myAltVoltAVG, double myAltVoltSTD, String myServDate) {
		
		IgnitionSwitch = myOnSwitch;
		ChargingSystemLight = myChargeLight;
		BatteryVoltageAVG = myBatVoltAVG;
		BatteryVoltageSTD = myBatVoltSTD;
		AlternatorVoltageAVG = myAltVoltAVG;
		AlternatorVoltageSTD = myAltVoltSTD;
		LastServiceDate = myServDate;
	}

	/**
	 * @return the ignitionSwitch
	 */
	public Boolean getIgnitionSwitch() {
		return IgnitionSwitch;
	}

	/**
	 * @param ignitionSwitch the ignitionSwitch to set
	 */
	public void setIgnitionSwitch(Boolean ignitionSwitch) {
		IgnitionSwitch = ignitionSwitch;
	}

	/**
	 * @return the chargingSystemLight
	 */
	public Boolean getChargingSystemLight() {
		return ChargingSystemLight;
	}

	/**
	 * @param chargingSystemLight the chargingSystemLight to set
	 */
	public void setChargingSystemLight(Boolean chargingSystemLight) {
		ChargingSystemLight = chargingSystemLight;
	}

	/**
	 * @param batteryVoltageAVG the batteryVoltageAVG to set
	 */
	public void setBatteryVoltageAVG(double batteryVoltageAVG) {
		BatteryVoltageAVG = batteryVoltageAVG;
	}

	/**
	 * @param batteryVoltageSTD the batteryVoltageSTD to set
	 */
	public void setBatteryVoltageSTD(double batteryVoltageSTD) {
		BatteryVoltageSTD = batteryVoltageSTD;
	}

	/**
	 * @return the batteryVoltage
	 */
	public double getBatteryVoltage() {
		Random randomGenerator = new Random();
		return this.BatteryVoltageSTD*randomGenerator.nextDouble() + 
				this.BatteryVoltageAVG;
	}

	/**
	 * @param alternatorVoltageAVG the alternatorVoltageAVG to set
	 */
	public void setAlternatorVoltageAVG(double alternatorVoltageAVG) {
		AlternatorVoltageAVG = alternatorVoltageAVG;
	}

	/**
	 * @param alternatorVoltageSTD the alternatorVoltageSTD to set
	 */
	public void setAlternatorVoltageSTD(double alternatorVoltageSTD) {
		AlternatorVoltageSTD = alternatorVoltageSTD;
	}

	/**
	 * @return the alternatorVoltage
	 */
	public double getAlternatorVoltage() {
		Random randomGenerator = new Random();
		return this.AlternatorVoltageSTD*randomGenerator.nextDouble() + 
				this.AlternatorVoltageAVG;
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

	public double getBatteryVoltageAVG() {
		return BatteryVoltageAVG;
	}

	public double getBatteryVoltageSTD() {
		return BatteryVoltageSTD;
	}
	
	
}
