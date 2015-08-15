package blackbox;

import java.util.Observable;
import java.util.Observer;

public class SensorsView implements Observer {
	
	private Sensors sensor  = new Sensors();
	
	public SensorsView(Sensors sensor) {
		this.sensor = sensor;
		sensor.addObserver(this);
	}
	
	public void update(Observable obale, Object ob) {
		
		// Updates the changes
	}

	

/*	private Car speedsensor;
	private Tires tire;

	public SensorsView(Car speedsensor, Tires tire) {

		this.speedsensor = speedsensor;
		this.tire = tire;
		speedsensor.addObserver(this);
		tire.addObserver(this);

	}

	public void update(Observable obale, Object ob) {

		// Print the changes in concole to verify output
//		String speed = ((Integer) ob).toString();
//		System.out.println("speed changed: " + speed);
		
//		String tire = ((Double) ob).toString();
//		System.out.println("Tire pressure: " + tire);

	}
*/
}