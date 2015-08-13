package BlackBox.car;

/**
 * 
 */

import java.util.Random;

/**
 * @author kchilds
 *
 */
public class Car {
	
	private double speedAVG;
	private double speedSTD;
	private double accelerationAVG;
	private double accelerationSTD;
	private double yahAVG;
	private double yahSTD;
	private double pitchAVG;
	private double pitchSTD;
	private String make;
	private String model;
	private int year;
	private int [] OBD2Codes;
	public CoolingSystem sysCooling;
	public ExhaustSystem sysExhaust;
	public FuelSystem sysFuel;
	public BrakingSystem sysBrake;
	public IgnitionSystem sysIgnition;
	public SteeringSystem sysSteering;
	public Engine sysEngine;
	public Tires sysTires;
	

	/**
	 * Generic car constructor
	 */
	public Car() {
		speedAVG = 0;
		speedSTD = 0;
		accelerationAVG = 0;
		accelerationSTD = 0;
		yahAVG = 0;
		yahSTD = 0;
		pitchAVG = 0;
		pitchSTD = 0;
		make = "Car";
		model = "001";
		year = 2015;
		OBD2Codes = null;
		sysCooling = new CoolingSystem();
		sysExhaust = new ExhaustSystem();
		sysFuel = new FuelSystem();
		sysBrake = new BrakingSystem();
		sysIgnition = new IgnitionSystem();
		sysSteering = new SteeringSystem();
		sysEngine = new Engine();
		sysTires = new Tires();
		
	}
	

	/**
	 * 
	 * @param mySpeedAVG
	 * @param mySpeedSTD
	 * @param myAccelerationAVG
	 * @param myAccelerationSTD
	 * @param myYahAVG
	 * @param myYahSTD
	 * @param myPitchAVG
	 * @param myPitchSTD
	 * @param myMake
	 * @param myModel
	 * @param myYear
	 * @param myOBD
	 * @param myCooling
	 * @param myExhaust
	 * @param myFuel
	 * @param myBrakes
	 * @param myIgnition
	 * @param mySteer
	 * @param myEngine
	 * @param myTires
	 */
	public Car(double mySpeedAVG, double mySpeedSTD, double myAccelerationAVG, double myAccelerationSTD, 
			double myYahAVG, double myYahSTD, double myPitchAVG, double myPitchSTD, String myMake, 
			String myModel, int myYear, int [] myOBD, CoolingSystem myCooling, ExhaustSystem myExhaust, 
			FuelSystem myFuel, BrakingSystem myBrakes, IgnitionSystem myIgnition, SteeringSystem mySteer, 
			Engine myEngine, Tires myTires) {
		speedAVG = mySpeedAVG;
		speedSTD = mySpeedSTD;
		accelerationAVG = myAccelerationAVG;
		accelerationSTD = myAccelerationSTD;
		yahAVG = myYahAVG;
		yahSTD = myYahSTD;
		pitchAVG = myPitchAVG;
		pitchSTD = myPitchSTD;
		make = myMake;
		model = myModel;
		year = myYear;
		OBD2Codes = myOBD;
		this.sysCooling = myCooling;
		this.sysExhaust = myExhaust;
		this.sysFuel = myFuel;
		this.sysBrake = myBrakes;
		this.sysIgnition = myIgnition;
		this.sysSteering = mySteer;
		this.sysEngine = myEngine;
		this.sysTires = myTires;
	}

	/**
	 * @param speedAVG the speedAVG to set
	 */
	public void setSpeedAVG(double speedAVG) {
		this.speedAVG = speedAVG;
	}

	/**
	 * @param speedSTD the speedSTD to set
	 */
	public void setSpeedSTD(double speedSTD) {
		this.speedSTD = speedSTD;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		Random randomGenerator = new Random();
		return this.speedSTD*randomGenerator.nextDouble() + 
				this.speedAVG;
	}

	/**
	 * @param accelerationAVG the accelerationAVG to set
	 */
	public void setAccelerationAVG(double accelerationAVG) {
		this.accelerationAVG = accelerationAVG;
	}

	/**
	 * @param accelerationSTD the accelerationSTD to set
	 */
	public void setAccelerationSTD(double accelerationSTD) {
		this.accelerationSTD = accelerationSTD;
	}

	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		Random randomGenerator = new Random();
		return this.accelerationSTD*randomGenerator.nextDouble() + 
				this.accelerationAVG;
	}

	/**
	 * @param yahAVG the yahAVG to set
	 */
	public void setYahAVG(double yahAVG) {
		this.yahAVG = yahAVG;
	}

	/**
	 * @param yahSTD the yahSTD to set
	 */
	public void setYahSTD(double yahSTD) {
		this.yahSTD = yahSTD;
	}

	/**
	 * @return the yah
	 */
	public double getYah() {
		Random randomGenerator = new Random();
		return this.yahSTD*randomGenerator.nextDouble() + 
				this.yahAVG;
	}

	/**
	 * @param pitchAVG the pitchAVG to set
	 */
	public void setPitchAVG(double pitchAVG) {
		this.pitchAVG = pitchAVG;
	}

	/**
	 * @param pitchSTD the pitchSTD to set
	 */
	public void setPitchSTD(double pitchSTD) {
		this.pitchSTD = pitchSTD;
	}

	/**
	 * @return the pitch
	 */
	public double getPitchSTD() {
		Random randomGenerator = new Random();
		return this.pitchSTD*randomGenerator.nextDouble() + 
				this.pitchAVG;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}


	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}


	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}


	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}


	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}


	/**
	 * @return the oBD2Codes
	 */
	public int[] getOBD2Codes() {
		return OBD2Codes;
	}


	/**
	 * @param oBD2Codes the oBD2Codes to set
	 */
	public void setOBD2Codes(int[] oBD2Codes) {
		OBD2Codes = oBD2Codes;
	}


	/**
	 * @return the myCooling
	 */
	public CoolingSystem getMyCooling() {
		return sysCooling;
	}


	/**
	 * @param myCooling the myCooling to set
	 */
	public void setMyCooling(CoolingSystem myCooling) {
		this.sysCooling = myCooling;
	}


	/**
	 * @return the myExhaust
	 */
	public ExhaustSystem getMyExhaust() {
		return sysExhaust;
	}


	/**
	 * @param myExhaust the myExhaust to set
	 */
	public void setMyExhaust(ExhaustSystem myExhaust) {
		this.sysExhaust = myExhaust;
	}


	/**
	 * @return the myFuel
	 */
	public FuelSystem getMyFuel() {
		return sysFuel;
	}


	/**
	 * @param myFuel the myFuel to set
	 */
	public void setMyFuel(FuelSystem myFuel) {
		this.sysFuel = myFuel;
	}


	/**
	 * @return the myBrakes
	 */
	public BrakingSystem getMyBrakes() {
		return sysBrake;
	}


	/**
	 * @param myBrakes the myBrakes to set
	 */
	public void setMyBrakes(BrakingSystem myBrakes) {
		this.sysBrake = myBrakes;
	}


	/**
	 * @return the myIgnition
	 */
	public IgnitionSystem getMyIgnition() {
		return sysIgnition;
	}


	/**
	 * @param myIgnition the myIgnition to set
	 */
	public void setMyIgnition(IgnitionSystem myIgnition) {
		this.sysIgnition = myIgnition;
	}


	/**
	 * @return the mySteer
	 */
	public SteeringSystem getMySteer() {
		return sysSteering;
	}


	/**
	 * @param mySteer the mySteer to set
	 */
	public void setMySteer(SteeringSystem mySteer) {
		this.sysSteering = mySteer;
	}


	/**
	 * @return the myEngine
	 */
	public Engine getMyEngine() {
		return sysEngine;
	}


	/**
	 * @param myEngine the myEngine to set
	 */
	public void setMyEngine(Engine myEngine) {
		this.sysEngine = myEngine;
	}


	/**
	 * @return the sysCooling
	 */
	public CoolingSystem getSysCooling() {
		return sysCooling;
	}


	/**
	 * @param sysCooling the sysCooling to set
	 */
	public void setSysCooling(CoolingSystem sysCooling) {
		this.sysCooling = sysCooling;
	}


	/**
	 * @return the sysExhaust
	 */
	public ExhaustSystem getSysExhaust() {
		return sysExhaust;
	}


	/**
	 * @param sysExhaust the sysExhaust to set
	 */
	public void setSysExhaust(ExhaustSystem sysExhaust) {
		this.sysExhaust = sysExhaust;
	}


	/**
	 * @return the sysFuel
	 */
	public FuelSystem getSysFuel() {
		return sysFuel;
	}


	/**
	 * @param sysFuel the sysFuel to set
	 */
	public void setSysFuel(FuelSystem sysFuel) {
		this.sysFuel = sysFuel;
	}


	/**
	 * @return the sysBrake
	 */
	public BrakingSystem getSysBrake() {
		return sysBrake;
	}


	/**
	 * @param sysBrake the sysBrake to set
	 */
	public void setSysBrake(BrakingSystem sysBrake) {
		this.sysBrake = sysBrake;
	}


	/**
	 * @return the sysIgnition
	 */
	public IgnitionSystem getSysIgnition() {
		return sysIgnition;
	}


	/**
	 * @param sysIgnition the sysIgnition to set
	 */
	public void setSysIgnition(IgnitionSystem sysIgnition) {
		this.sysIgnition = sysIgnition;
	}


	/**
	 * @return the sysSteering
	 */
	public SteeringSystem getSysSteering() {
		return sysSteering;
	}


	/**
	 * @param sysSteering the sysSteering to set
	 */
	public void setSysSteering(SteeringSystem sysSteering) {
		this.sysSteering = sysSteering;
	}


	/**
	 * @return the sysEngine
	 */
	public Engine getSysEngine() {
		return sysEngine;
	}


	/**
	 * @param sysEngine the sysEngine to set
	 */
	public void setSysEngine(Engine sysEngine) {
		this.sysEngine = sysEngine;
	}


	/**
	 * @return the sysTires
	 */
	public Tires getSysTires() {
		return sysTires;
	}


	/**
	 * @param sysTires the sysTires to set
	 */
	public void setSysTires(Tires sysTires) {
		this.sysTires = sysTires;
	}


}
