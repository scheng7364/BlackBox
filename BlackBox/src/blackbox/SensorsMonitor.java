package blackbox;

import java.util.Observable;
import java.util.Observer;


public class SensorsMonitor implements Observer {
		
		private Sensors sensor;
		
		public SensorsMonitor(Sensors sensor) {
			this.sensor = sensor;
			sensor.addObserver(this);
		}
		
		public void update(Observable obale, Object ifHealthy) {
			
			// Updates the changes
			boolean healthy = (Boolean) ifHealthy;
			
			// If healthy = false, set warning
			if(healthy) {
				sensor.setWarning(false);
			}
			else {
				sensor.setWarning(true);
			}
				
		}
	}

