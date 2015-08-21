/**
 * 
 */
package blackbox;

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class Engine {
	
	private String type;
	private double AirFlowMeterAVG;
	private double AirFlowMeterSTD;
	private double ACCompressorClutchAVG;
	private double ACCompressorClutchSTD;
	private double KnockSensorAVG;
	private double KnockSensorSTD;
	private double BarometricSensorAVG;
	private double BarometricSensorSTD;
	private boolean FuelPumpRelay;
	private boolean FuelInjectors;
	private boolean ThrottleValveSolenoid;
	private double ThrottlePositionSensorAVG;
	private double ThrottlePositionSensorSTD;
	private double CoolantTemperatureSensorAVG;
	private double CoolantTemperatureSensorSTD;
	private double RPM_AVG;
	private double RPM_STD;
	private double HorsePowerAVG;
	private double HorsePowerSTD;
	private double IdleRPM_AVG;
	private double IdleRPM_STD;
	private double OilLevelSensorAVG;
	private double OilLevelSensorSTD;
	private double OilPressureSensorAVG;
	private double OilPressureSensorSTD;
	private boolean AutomaticChoke;
	private boolean VacuumRegulatorSolenoid;
	private double TractionControlPositionSensorAVG;
	private double TractionControlPositionSensorSTD;
	private boolean CompressionRelay;
	private double CamShaftPositionSensorAVG;
	private double CamShaftPositionSensorSTD;
	
	

	/**
	 * 
	 */
	public Engine() {
		type = "";
		AirFlowMeterAVG = 0;
		AirFlowMeterSTD = 0;
		ACCompressorClutchAVG = 0;
		ACCompressorClutchSTD = 0;
		KnockSensorAVG = 0;
		KnockSensorSTD = 0;
		BarometricSensorAVG = 0;
		BarometricSensorSTD = 0;
		FuelPumpRelay = false;
		FuelInjectors = false;
		ThrottleValveSolenoid = false;
		ThrottlePositionSensorAVG = 0;
		ThrottlePositionSensorSTD = 0;
		CoolantTemperatureSensorAVG = 0;
		CoolantTemperatureSensorSTD = 0;
		RPM_AVG = 0;
		RPM_STD = 0;
		HorsePowerAVG = 0;
		HorsePowerSTD = 0;
		IdleRPM_AVG = 0;
		IdleRPM_STD = 0;
		OilLevelSensorAVG = 0;
		OilLevelSensorSTD = 0;
		OilPressureSensorAVG = 0;
		OilPressureSensorSTD = 0;
		AutomaticChoke = false;
		VacuumRegulatorSolenoid = false;
		TractionControlPositionSensorAVG = 0;
		TractionControlPositionSensorSTD = 0;
		CompressionRelay = false;
		CamShaftPositionSensorAVG = 0;
		CamShaftPositionSensorSTD = 0;
	}


	/**
	 * 
	 * @param myType
	 * @param myAirFlowAVG
	 * @param myAirFlowSTD
	 * @param myCompressionClutchAVG
	 * @param myCompressionClutchSTD
	 * @param myKnockAVG
	 * @param myKnockSTD
	 * @param myBarometerAVG
	 * @param myBarometerSTD
	 * @param myFuelPump
	 * @param myInjectors
	 * @param myThrottle
	 * @param myThrottlePosAVG
	 * @param myThrottlePosSTD
	 * @param myCoolantTempAVG
	 * @param myCoolantTempSTD
	 * @param myRPM_AVG
	 * @param myRPM_STD
	 * @param myHorseAVG
	 * @param myHorseSTD
	 * @param myIdleAVG
	 * @param myIdleSTD
	 * @param myOilLevelAVG
	 * @param myOilLevelSTD
	 * @param myOilPressureAVG
	 * @param myOilPressureSTD
	 * @param myChoke
	 * @param myVac
	 * @param myTractionAVG
	 * @param myTractionSTD
	 * @param myCompressionRelay
	 * @param myCamPosAVG
	 * @param myCamPosSTD
	 */
	public Engine(String myType, double myAirFlowAVG, double myAirFlowSTD, double myCompressionClutchAVG, 
			double myCompressionClutchSTD, double myKnockAVG, double myKnockSTD, double myBarometerAVG, 
			double myBarometerSTD, boolean myFuelPump, boolean myInjectors, boolean myThrottle, 
			double myThrottlePosAVG, double myThrottlePosSTD, double myCoolantTempAVG, double myCoolantTempSTD,
			double myRPM_AVG, double myRPM_STD, double myHorseAVG, double myHorseSTD, double myIdleAVG, 
			double myIdleSTD, double myOilLevelAVG, double myOilLevelSTD, double myOilPressureAVG, 
			double myOilPressureSTD, boolean myChoke, boolean myVac, double myTractionAVG, 
			double myTractionSTD, boolean myCompressionRelay, double myCamPosAVG, double myCamPosSTD) {
		
		type = myType;
		AirFlowMeterAVG = myAirFlowAVG;
		AirFlowMeterSTD = myAirFlowSTD;
		ACCompressorClutchAVG = myCompressionClutchAVG;
		ACCompressorClutchSTD = myCompressionClutchSTD;
		KnockSensorAVG = myKnockAVG;
		KnockSensorSTD = myKnockSTD;
		BarometricSensorAVG = myBarometerAVG;
		BarometricSensorSTD = myBarometerSTD;
		FuelPumpRelay = myFuelPump;
		FuelInjectors = myInjectors;
		ThrottleValveSolenoid = myThrottle;
		ThrottlePositionSensorAVG = myThrottlePosAVG;
		ThrottlePositionSensorSTD = myThrottlePosSTD;
		CoolantTemperatureSensorAVG = myCoolantTempAVG;
		CoolantTemperatureSensorSTD = myCoolantTempSTD;
		RPM_AVG = myRPM_AVG;
		RPM_STD = myRPM_STD;
		HorsePowerAVG = myHorseAVG;
		HorsePowerSTD = myHorseSTD;
		IdleRPM_AVG = myIdleAVG;
		IdleRPM_STD = myIdleSTD;
		OilLevelSensorAVG = myOilLevelAVG;
		OilLevelSensorSTD = myOilLevelSTD;
		OilPressureSensorAVG = myOilPressureAVG;
		OilPressureSensorSTD = myOilPressureSTD;
		AutomaticChoke = myChoke;
		VacuumRegulatorSolenoid = myVac;
		TractionControlPositionSensorAVG = myTractionAVG;
		TractionControlPositionSensorSTD = myTractionSTD;
		CompressionRelay = myCompressionRelay;
		CamShaftPositionSensorAVG = myCamPosAVG;
		CamShaftPositionSensorSTD = myCamPosSTD;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param airFlowMeterAVG the airFlowMeterAVG to set
	 */
	public void setAirFlowMeterAVG(double airFlowMeterAVG) {
		AirFlowMeterAVG = airFlowMeterAVG;
	}

	/**
	 * @param airFlowMeterSTD the airFlowMeterSTD to set
	 */
	public void setAirFlowMeterSTD(double airFlowMeterSTD) {
		AirFlowMeterSTD = airFlowMeterSTD;
	}

	/**
	 * @return the airFlowMeter
	 */
	public double getAirFlowMeter() {
		Random randomGenerator = new Random();
		return this.AirFlowMeterSTD*randomGenerator.nextDouble() + 
				this.AirFlowMeterAVG;
	}

	/**
	 * @param aCCompressorClutchAVG the aCCompressorClutchAVG to set
	 */
	public void setACCompressorClutchAVG(double aCCompressorClutchAVG) {
		ACCompressorClutchAVG = aCCompressorClutchAVG;
	}

	/**
	 * @param aCCompressorClutchSTD the aCCompressorClutchSTD to set
	 */
	public void setACCompressorClutchSTD(double aCCompressorClutchSTD) {
		ACCompressorClutchSTD = aCCompressorClutchSTD;
	}

	/**
	 * @return the aCCompressorClutch
	 */
	public double getACCompressorClutch() {
		Random randomGenerator = new Random();
		return this.ACCompressorClutchSTD*randomGenerator.nextDouble() + 
				this.ACCompressorClutchAVG;
	}

	/**
	 * @param knockSensorAVG the knockSensorAVG to set
	 */
	public void setKnockSensorAVG(double knockSensorAVG) {
		KnockSensorAVG = knockSensorAVG;
	}

	/**
	 * @param knockSensorSTD the knockSensorSTD to set
	 */
	public void setKnockSensorSTD(double knockSensorSTD) {
		KnockSensorSTD = knockSensorSTD;
	}

	/**
	 * @return the knockSensor
	 */
	public double getKnockSensor() {
		Random randomGenerator = new Random();
		return this.KnockSensorSTD*randomGenerator.nextDouble() + 
				this.KnockSensorAVG;
	}

	/**
	 * @param barometricSensorAVG the barometricSensorAVG to set
	 */
	public void setBarometricSensorAVG(double barometricSensorAVG) {
		BarometricSensorAVG = barometricSensorAVG;
	}

	/**
	 * @param barometricSensorSTD the barometricSensorSTD to set
	 */
	public void setBarometricSensorSTD(double barometricSensorSTD) {
		BarometricSensorSTD = barometricSensorSTD;
	}

	/**
	 * @return the barometricSensor
	 */
	public double getBarometricSensor() {
		Random randomGenerator = new Random();
		return this.BarometricSensorSTD*randomGenerator.nextDouble() + 
				this.BarometricSensorAVG;
	}

	/**
	 * @return the fuelPumpRelay
	 */
	public boolean isFuelPumpRelay() {
		return FuelPumpRelay;
	}


	/**
	 * @param fuelPumpRelay the fuelPumpRelay to set
	 */
	public void setFuelPumpRelay(boolean fuelPumpRelay) {
		FuelPumpRelay = fuelPumpRelay;
	}


	/**
	 * @return the fuelInjectors
	 */
	public boolean isFuelInjectors() {
		return FuelInjectors;
	}


	/**
	 * @param fuelInjectors the fuelInjectors to set
	 */
	public void setFuelInjectors(boolean fuelInjectors) {
		FuelInjectors = fuelInjectors;
	}


	/**
	 * @return the throttleValveSolenoid
	 */
	public boolean isThrottleValveSolenoid() {
		return ThrottleValveSolenoid;
	}


	/**
	 * @param throttleValveSolenoid the throttleValveSolenoid to set
	 */
	public void setThrottleValveSolenoid(boolean throttleValveSolenoid) {
		ThrottleValveSolenoid = throttleValveSolenoid;
	}

	/**
	 * @param throttlePositionSensorAVG the throttlePositionSensorAVG to set
	 */
	public void setThrottlePositionSensorAVG(double throttlePositionSensorAVG) {
		ThrottlePositionSensorAVG = throttlePositionSensorAVG;
	}

	/**
	 * @param throttlePositionSensorSTD the throttlePositionSensorSTD to set
	 */
	public void setThrottlePositionSensorSTD(double throttlePositionSensorSTD) {
		ThrottlePositionSensorSTD = throttlePositionSensorSTD;
	}

	/**
	 * @return the throttlePositionSensor
	 */
	public double getThrottlePositionSensor() {
		Random randomGenerator = new Random();
		return this.ThrottlePositionSensorSTD*randomGenerator.nextDouble() + 
				this.ThrottlePositionSensorAVG;
	}

	/**
	 * @param coolantTemperatureSensorAVG the coolantTemperatureSensorAVG to set
	 */
	public void setCoolantTemperatureSensorAVG(double coolantTemperatureSensorAVG) {
		CoolantTemperatureSensorAVG = coolantTemperatureSensorAVG;
	}

	/**
	 * @param coolantTemperatureSensorSTD the coolantTemperatureSensorSTD to set
	 */
	public void setCoolantTemperatureSensorSTD(double coolantTemperatureSensorSTD) {
		CoolantTemperatureSensorSTD = coolantTemperatureSensorSTD;
	}

	/**
	 * @return the coolantTemperatureSensor
	 */
	public double getCoolantTemperatureSensor() {
		Random randomGenerator = new Random();
		return this.CoolantTemperatureSensorSTD*randomGenerator.nextDouble() + 
				this.CoolantTemperatureSensorAVG;
	}

	/**
	 * @param rPM_AVG the rPM_AVG to set
	 */
	public void setRPM_AVG(double rPM_AVG) {
		RPM_AVG = rPM_AVG;
	}

	/**
	 * @param rPM_STD the rPM_STD to set
	 */
	public void setRPM_STD(double rPM_STD) {
		RPM_STD = rPM_STD;
	}

	/**
	 * @return the RPM
	 */
	public double getRPM() {
		Random randomGenerator = new Random();
		return this.RPM_STD*randomGenerator.nextDouble() + 
				this.RPM_AVG;
	}

	/**
	 * @param horsePowerAVG the horsePowerAVG to set
	 */
	public void setHorsePowerAVG(double horsePowerAVG) {
		HorsePowerAVG = horsePowerAVG;
	}

	/**
	 * @param horsePowerSTD the horsePowerSTD to set
	 */
	public void setHorsePowerSTD(double horsePowerSTD) {
		HorsePowerSTD = horsePowerSTD;
	}

	/**
	 * @return the horsePower
	 */
	public double getHorsePower() {
		Random randomGenerator = new Random();
		return this.HorsePowerSTD*randomGenerator.nextDouble() + 
				this.HorsePowerAVG;
	}

	/**
	 * @param idleRPM_AVG the idleRPM_AVG to set
	 */
	public void setIdleRPM_AVG(double idleRPM_AVG) {
		IdleRPM_AVG = idleRPM_AVG;
	}

	/**
	 * @param idleRPM_STD the idleRPM_STD to set
	 */
	public void setIdleRPM_STD(double idleRPM_STD) {
		IdleRPM_STD = idleRPM_STD;
	}

	/**
	 * @return the idleRPM
	 */
	public double getIdleRPM() {
		Random randomGenerator = new Random();
		return this.IdleRPM_STD*randomGenerator.nextDouble() + 
				this.IdleRPM_AVG;
	}

	/**
	 * @param oilLevelSensorAVG the oilLevelSensorAVG to set
	 */
	public void setOilLevelSensorAVG(double oilLevelSensorAVG) {
		OilLevelSensorAVG = oilLevelSensorAVG;
	}

	/**
	 * @param oilLevelSensorSTD the oilLevelSensorSTD to set
	 */
	public void setOilLevelSensorSTD(double oilLevelSensorSTD) {
		OilLevelSensorSTD = oilLevelSensorSTD;
	}

	/**
	 * @return the oilLevelSensor
	 */
	public double getOilLevelSensor() {
		Random randomGenerator = new Random();
		return this.OilLevelSensorSTD*randomGenerator.nextDouble() + 
				this.OilLevelSensorAVG;
	}

	/**
	 * @param oilPressureSensorAVG the oilPressureSensorAVG to set
	 */
	public void setOilPressureSensorAVG(double oilPressureSensorAVG) {
		OilPressureSensorAVG = oilPressureSensorAVG;
	}

	/**
	 * @param oilPressureSensorSTD the oilPressureSensorSTD to set
	 */
	public void setOilPressureSensorSTD(double oilPressureSensorSTD) {
		OilPressureSensorSTD = oilPressureSensorSTD;
	}

	/**
	 * @return the oilPressureSensor
	 */
	public double getOilPressureSensor() {
		Random randomGenerator = new Random();
		return this.OilPressureSensorSTD*randomGenerator.nextDouble() + 
				this.OilPressureSensorAVG;
	}

	/**
	 * @return the automaticChoke
	 */
	public boolean isAutomaticChoke() {
		return AutomaticChoke;
	}


	/**
	 * @param automaticChoke the automaticChoke to set
	 */
	public void setAutomaticChoke(boolean automaticChoke) {
		AutomaticChoke = automaticChoke;
	}


	/**
	 * @return the vacuumRegulatorSolenoid
	 */
	public boolean isVacuumRegulatorSolenoid() {
		return VacuumRegulatorSolenoid;
	}


	/**
	 * @param vacuumRegulatorSolenoid the vacuumRegulatorSolenoid to set
	 */
	public void setVacuumRegulatorSolenoid(boolean vacuumRegulatorSolenoid) {
		VacuumRegulatorSolenoid = vacuumRegulatorSolenoid;
	}

	/**
	 * @param tractionControlPositionSensorAVG the tractionControlPositionSensorAVG to set
	 */
	public void setTractionControlPositionSensorAVG(double tractionControlPositionSensorAVG) {
		TractionControlPositionSensorAVG = tractionControlPositionSensorAVG;
	}

	/**
	 * @param tractionControlPositionSensorSTD the tractionControlPositionSensorSTD to set
	 */
	public void setTractionControlPositionSensorSTD(double tractionControlPositionSensorSTD) {
		TractionControlPositionSensorSTD = tractionControlPositionSensorSTD;
	}

	/**
	 * @return the tractionControlPositionSensor
	 */
	public double getTractionControlPositionSensor() {
		Random randomGenerator = new Random();
		return this.TractionControlPositionSensorSTD*randomGenerator.nextDouble() + 
				this.TractionControlPositionSensorAVG;
	}

	/**
	 * @return the compressionRelay
	 */
	public boolean isCompressionRelay() {
		return CompressionRelay;
	}


	/**
	 * @param compressionRelay the compressionRelay to set
	 */
	public void setCompressionRelay(boolean compressionRelay) {
		CompressionRelay = compressionRelay;
	}

	/**
	 * @param camShaftPositionSensorAVG the camShaftPositionSensorAVG to set
	 */
	public void setCamShaftPositionSensorAVG(double camShaftPositionSensorAVG) {
		CamShaftPositionSensorAVG = camShaftPositionSensorAVG;
	}

	/**
	 * @param camShaftPositionSensorSTD the camShaftPositionSensorSTD to set
	 */
	public void setCamShaftPositionSensorSTD(double camShaftPositionSensorSTD) {
		CamShaftPositionSensorSTD = camShaftPositionSensorSTD;
	}

	/**
	 * @return the camShaftPositionSensor
	 */
	public double getCamShaftPositionSensor() {
		Random randomGenerator = new Random();
		return this.CamShaftPositionSensorSTD*randomGenerator.nextDouble() + 
				this.CamShaftPositionSensorAVG;
	}


	public double getRPM_AVG() {
		return RPM_AVG;
	}


	public double getRPM_STD() {
		return RPM_STD;
	}


	public double getHorsePowerAVG() {
		return HorsePowerAVG;
	}


	public double getHorsePowerSTD() {
		return HorsePowerSTD;
	}


	public double getOilLevelSensorAVG() {
		return OilLevelSensorAVG;
	}


	public double getOilLevelSensorSTD() {
		return OilLevelSensorSTD;
	}
	
	

}
