package blackbox;

import java.util.Observable;
import java.util.Observer;

public class SensorsView implements Observer {
	private BlackBoxTester bt;
	//private Sensors sensor;
	
	private Sensors sensor = bt.thisSensor;
	
	public SensorsView(Sensors sensor) {
		this.sensor = sensor;
		sensor.addObserver(this);
	}
	
	public void update(Observable obale, Object ob) {
		
		// Updates the changes
	}
}