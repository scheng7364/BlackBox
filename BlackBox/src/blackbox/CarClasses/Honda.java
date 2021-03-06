/**
 * @(#)Honda.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox.CarClasses;

public class Honda extends Car {

	public Honda() {
		
		//value for car
		this.setMake("Honda");
		this.setModel("Civic");
		this.setYear(2015);
		
		this.setSpeedAVG(50);
		this.setSpeedSTD(50);
		this.setAccelerationAVG(10);
		this.setAccelerationSTD(2);
		this.setYahAVG(0);
		this.setYahSTD(0);
		this.setPitchAVG(0);
		this.setPitchSTD(0);
		
		//values for engine
		this.sysEngine.setType("16 Valve SOHC V-Tec");
		this.sysEngine.setAirFlowMeterAVG(40);
		this.sysEngine.setAirFlowMeterSTD(30);
		this.sysEngine.setHorsePowerAVG(130);
		this.sysEngine.setHorsePowerSTD(13);
		this.sysEngine.setRPM_AVG(4500);
		this.sysEngine.setRPM_STD(2200);
		this.sysEngine.setACCompressorClutchAVG(10);
		this.sysEngine.setACCompressorClutchSTD(0.6);
		this.sysEngine.setIdleRPM_AVG(600);
		this.sysEngine.setIdleRPM_STD(50);
		this.sysEngine.setOilLevelSensorAVG(100);
		this.sysEngine.setOilLevelSensorSTD(40);
		
		
		//Cooling system
		this.sysCooling.setMaxTemperature(250);
		this.sysCooling.setMinTemperature(100);
		this.sysCooling.setTemperatureAVG(175);
		this.sysCooling.setTemperatureSTD(80);
		
		//exhaust system
		this.sysExhaust.setFirstOxygenSensorAVG(11);
		this.sysExhaust.setFirstOxygenSensorSTD(1.5);
		
		//fuel system
		this.sysFuel.setFuelLevelAVG(50);
		this.sysFuel.setFuelLevelSTD(30);
		
		//tire system
		this.sysTires.setTirePressureAVG(35);
		this.sysTires.setTirePressureSTD(15);
		this.sysTires.setBrandName("Honda Tire");
		this.sysTires.setModelNumber("974 - 515");
		this.sysTires.setServiceDate("07/21/2015");
		
	}
	
	public Honda(double speedAVG, double speedSTD, double accAVG, double accSTD, 
			double yahAVG, double yahSTD, double pitchAVG, double pitchSTD, 
			String model, int year) {
		new Honda();
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
