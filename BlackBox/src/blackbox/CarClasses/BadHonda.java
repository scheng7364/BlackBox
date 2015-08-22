/**
 * 
 */
package blackbox.CarClasses;

/**
 * @author KC
 *
 */
public class BadHonda extends Car {
	//
	/**
	 * 
	 */
	public BadHonda() {
		
		//value for car
		this.setMake("Honda");
		this.setModel("Civic");
		this.setYear(2001);
		
		this.setSpeedAVG(30);
		this.setSpeedSTD(30);
		this.setAccelerationAVG(2);
		this.setAccelerationSTD(.5);
		this.setYahAVG(0);
		this.setYahSTD(0);
		this.setPitchAVG(0);
		this.setPitchSTD(0);
		
		//values for engine
		//int [] OBD2= {1, 2, 3, 4};		
		//this.setOBD2Codes(OBD2);
		this.sysEngine.setType("16 Valve SOHC");
		this.sysEngine.setAirFlowMeterAVG(40);
		this.sysEngine.setAirFlowMeterSTD(30);
		this.sysEngine.setHorsePowerAVG(85);
		this.sysEngine.setHorsePowerSTD(5);
		this.sysEngine.setRPM_AVG(5000);
		this.sysEngine.setRPM_STD(2200);
		this.sysEngine.setACCompressorClutchAVG(10);
		this.sysEngine.setACCompressorClutchSTD(0.6);
		this.sysEngine.setIdleRPM_AVG(400);
		this.sysEngine.setIdleRPM_STD(100);
		this.sysEngine.setOilLevelSensorAVG(60);
		this.sysEngine.setOilLevelSensorSTD(40);
		
		
		//Cooling system

		this.sysCooling.setTemperatureAVG(250);
		this.sysCooling.setTemperatureSTD(90);
		
		//exhaust system
		this.sysExhaust.setFirstOxygenSensorAVG(7);
		this.sysExhaust.setFirstOxygenSensorSTD(2);
		
		//fuel system
		this.sysFuel.setFuelLevelAVG(40);
		this.sysFuel.setFuelLevelSTD(40);
		
		//tire system
		this.sysTires.setTirePressureAVG(25);
		this.sysTires.setTirePressureSTD(15);
		this.sysTires.setBrandName("Honda Tire");
		this.sysTires.setModelNumber("974 - 515");
		this.sysTires.setServiceDate("07/21/2001");
		
	}
	
	public BadHonda(double speedAVG, double speedSTD, double accAVG, double accSTD, 
			double yahAVG, double yahSTD, double pitchAVG, double pitchSTD, 
			String model, int year) {
		new BadHonda();
		//value for car
		this.setModel(model);
		this.setYear(year);
		
		this.setSpeedAVG(speedAVG);
		this.setSpeedSTD(speedSTD);
		this.setAccelerationAVG(accAVG);
		this.setAccelerationSTD(accSTD);
		this.setYahAVG(yahAVG);
		this.setYahSTD(yahSTD);
		this.setPitchAVG(pitchAVG);
		this.setPitchSTD(pitchSTD);

		
	}

}
