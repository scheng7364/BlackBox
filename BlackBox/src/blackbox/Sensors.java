package blackbox;

import java.util.Observable;

public class Sensors extends Observable {

	private int speed;

	public Sensors(int speed) {
		setSpeed(speed);
	}

	public void setSpeed(int currentSpeed) {
		speed = currentSpeed;

		setChanged();
		// notify Observers that model has changed
		notifyObservers(new Integer(speed));
	}

	public int getSpeed() {
		return speed;
	}
	
}
