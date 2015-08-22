/**
 * 
 */
package blackbox;

/**
 * @author KC
 *
 */
public class MaxMinValues {
	
	private double maxSpeed;
	private double minSpeed;
	private double maxAcceleration;
	private double minAcceleration;
	private double maxYah;  // for future implementations (not used in our code)
	private double minYah;  // for future implementations (not used in our code)
	private double maxPitch;  // for future implementations (not used in our code)
	private double minPitch;  // for future implementations (not used in our code)
	
	//values for engine
	private double maxAirFlowMeter;  // for future implementations (not used in our code)
	private double minAirFlowMeter;  // for future implementations (not used in our code)
	private double maxHorsePower;  // for future implementations (not used in our code)
	private double minHorsePower;  // for future implementations (not used in our code)
	private double maxRPM;
	private double minRPM;
	private double maxACCompressorClutch;  // for future implementations (not used in our code)
	private double minACCompressorClutch;  // for future implementations (not used in our code)
	private double maxIdleRPM;  // for future implementations (not used in our code)
	private double minIdleRPM;  // for future implementations (not used in our code)
	private double maxOilLevelSensor;
	private double minOilLevelSensor;
	
	
	//Cooling system
	private double maxTemperature;//Critical, can cause damage to continue at max
	private double minTemperature;
	
	//exhaust system
	private double maxFirstOxygenSensor;
	private double minFirstOxygenSensor;
	
	//fuel system
	private double maxFuelLevel;
	private double minFuelLevel;//Critical, can cause damage to continue at min
	
	//tire system
	private double maxTirePressure;
	private double minTirePressure;//Critical, can cause damage to continue at min
	

	/**
	 * These values would usually be found after ample research, but we decided to 
	 * make the values 90% of the range, just to show some interesting results
	 */
	public MaxMinValues(Car myCar) {
		
		int percentOut = 10; //gives percentage that range is outside of max / min value
		
		maxSpeed=(1-percentOut/200)*(myCar.getSpeedAVG() + myCar.getSpeedSTD());
		minSpeed=(1+percentOut/200)*(myCar.getSpeedAVG() - myCar.getSpeedSTD());
		maxAcceleration=(1-percentOut/200)*(myCar.getAccelerationAVG() + myCar.getAccelerationSTD());
		minAcceleration=(1+percentOut/200)*(myCar.getAccelerationAVG() - myCar.getAccelerationSTD());
		maxYah=0;  // for future implementations (not used in our code)
		minYah=0;  // for future implementations (not used in our code)
		maxPitch=0;  // for future implementations (not used in our code)
		minPitch=0;  // for future implementations (not used in our code)
		
		//values for engine
		maxAirFlowMeter=0;  // for future implementations (not used in our code)
		minAirFlowMeter=0;  // for future implementations (not used in our code)
		maxHorsePower=(1-percentOut/200)*(myCar.getSysEngine().getHorsePowerAVG() + 
				myCar.getSysEngine().getHorsePowerSTD());
		minHorsePower=(1+percentOut/200)*(myCar.getSysEngine().getHorsePowerAVG() - 
				myCar.getSysEngine().getHorsePowerSTD());
		maxRPM=(1-percentOut/200)*(myCar.getSysEngine().getRPM_AVG() + 
				myCar.getSysEngine().getRPM_STD());
		minRPM=(1+percentOut/200)*(myCar.getSysEngine().getRPM_AVG() - 
				myCar.getSysEngine().getRPM_STD());
		maxACCompressorClutch=0;  // for future implementations (not used in our code)
		minACCompressorClutch=0;  // for future implementations (not used in our code)
		maxIdleRPM=0;  // for future implementations (not used in our code)
		minIdleRPM=0;  // for future implementations (not used in our code)
		maxOilLevelSensor=0;  // for future implementations (not used in our code)
		minOilLevelSensor=0;  // for future implementations (not used in our code)
		
		
		//Cooling system
		maxTemperature=(1-percentOut/200)*(myCar.getSysCooling().getTemperatureAVG() + 
				myCar.getSysCooling().getTemperatureSTD());
		minTemperature=(1+percentOut/200)*(myCar.getSysCooling().getTemperatureAVG() - 
				myCar.getSysCooling().getTemperatureSTD());
		
		//exhaust system
		maxFirstOxygenSensor=(1-percentOut/200)*(myCar.getSysExhaust().getFirstOxygenSensorAVG() + 
				myCar.getSysExhaust().getFirstOxygenSensorSTD());
		minFirstOxygenSensor=(1+percentOut/200)*(myCar.getSysExhaust().getFirstOxygenSensorAVG() - 
				myCar.getSysExhaust().getFirstOxygenSensorSTD());
		
		//fuel system
		maxFuelLevel=(1-percentOut/200)*(myCar.getSysFuel().getFuelLevelAVG() + 
				myCar.getSysFuel().getFuelLevelSTD());
		minFuelLevel=(1+percentOut/200)*(myCar.getSysFuel().getFuelLevelAVG() - 
				myCar.getSysFuel().getFuelLevelSTD());
		
		//tire system
		maxTirePressure=(1-percentOut/200)*(myCar.getSysTires().getTirePressureAVG() + 
				myCar.getSysTires().getTirePressureSTD());
		minTirePressure=(1+percentOut/200)*(myCar.getSysTires().getTirePressureAVG() - 
				myCar.getSysTires().getTirePressureSTD());
	}

	//Setters and Getters
	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @return the minSpeed
	 */
	public double getMinSpeed() {
		return minSpeed;
	}

	/**
	 * @return the maxAcceleration
	 */
	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	/**
	 * @return the minAcceleration
	 */
	public double getMinAcceleration() {
		return minAcceleration;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxYah
	 */
	public double getMaxYah() {
		return maxYah;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minYah
	 */
	public double getMinYah() {
		return minYah;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxPitch
	 */
	public double getMaxPitch() {
		return maxPitch;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minPitch
	 */
	public double getMinPitch() {
		return minPitch;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxAirFlowMeter
	 */
	public double getMaxAirFlowMeter() {
		return maxAirFlowMeter;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minAirFlowMeter
	 */
	public double getMinAirFlowMeter() {
		return minAirFlowMeter;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxHorsePower
	 */
	public double getMaxHorsePower() {
		return maxHorsePower;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minHorsePower
	 */
	public double getMinHorsePower() {
		return minHorsePower;
	}

	/**
	 * @return the maxRPM
	 */
	public double getMaxRPM() {
		return maxRPM;
	}

	/**
	 * @return the minRPM
	 */
	public double getMinRPM() {
		return minRPM;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxACCompressorClutch
	 */
	public double getMaxACCompressorClutch() {
		return maxACCompressorClutch;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minACCompressorClutch
	 */
	public double getMinACCompressorClutch() {
		return minACCompressorClutch;
	}

	/**  // for future implementations (not used in our code)
	 * @return the maxIdleRPM
	 */
	public double getMaxIdleRPM() {
		return maxIdleRPM;
	}

	/**  // for future implementations (not used in our code)
	 * @return the minIdleRPM
	 */
	public double getMinIdleRPM() {
		return minIdleRPM;
	}

	/**
	 * @return the maxOilLevelSensor
	 */
	public double getMaxOilLevelSensor() {
		return maxOilLevelSensor;
	}

	/**
	 * @return the minOilLevelSensor
	 */
	public double getMinOilLevelSensor() {
		return minOilLevelSensor;
	}

	/**
	 * @return the maxTemperature
	 */
	public double getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @return the minTemperature
	 */
	public double getMinTemperature() {
		return minTemperature;
	}

	/**
	 * @return the maxFirstOxygenSensor
	 */
	public double getMaxFirstOxygenSensor() {
		return maxFirstOxygenSensor;
	}

	/**
	 * @return the minFirstOxygenSensor
	 */
	public double getMinFirstOxygenSensor() {
		return minFirstOxygenSensor;
	}

	/**
	 * @return the maxFuelLevel
	 */
	public double getMaxFuelLevel() {
		return maxFuelLevel;
	}

	/**
	 * @return the minFuelLevel
	 */
	public double getMinFuelLevel() {
		return minFuelLevel;
	}

	/**
	 * @return the maxTirePressure
	 */
	public double getMaxTirePressure() {
		return maxTirePressure;
	}

	/**
	 * @return the minTirePressure
	 */
	public double getMinTirePressure() {
		return minTirePressure;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MaxMinValues [maxSpeed=" + maxSpeed + ", minSpeed=" + minSpeed + ", maxAcceleration=" + maxAcceleration
				+ ", minAcceleration=" + minAcceleration + ", maxRPM=" + maxRPM + ", minRPM=" + minRPM
				+ ", maxOilLevelSensor=" + maxOilLevelSensor + ", minOilLevelSensor=" + minOilLevelSensor
				+ ", maxTemperature=" + maxTemperature + ", minTemperature=" + minTemperature
				+ ", maxFirstOxygenSensor=" + maxFirstOxygenSensor + ", minFirstOxygenSensor=" + minFirstOxygenSensor
				+ ", maxFuelLevel=" + maxFuelLevel + ", minFuelLevel=" + minFuelLevel + ", maxTirePressure="
				+ maxTirePressure + ", minTirePressure=" + minTirePressure + "]";
	}

	
}
