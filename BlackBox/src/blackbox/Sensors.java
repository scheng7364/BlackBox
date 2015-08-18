package blackbox;

import java.util.Observable;

public class Sensors extends Observable {

	private Car car1 = new Car();

	private static int count = 0;
	
	public Sensors() {
	}
	
	// Talk to car speed sensor
	public double getCarSpeed() {
		car1.setSpeedAVG(35.0);
		car1.setSpeedSTD(10.0);
			
		return car1.getSpeed();
	}
	
	// Talk to tire pressure sensor
	public String getCarTirePressure() {
		String status = "Healthy";
		
		car1.setSysTires(new Tires(20.0, 40.0, "","",""));
		
	//	System.out.println(car1.sysTires.getTirePressure());
		if (car1.sysTires.getTirePressure() >= 40) {
			count++;
		} else count = 0;
			
		if (count > 10) {	// if the tire pressure is > 20 for 20 sec, show warning
			status = "Warning";
		}
		 
		 return status;
	}
	
}
