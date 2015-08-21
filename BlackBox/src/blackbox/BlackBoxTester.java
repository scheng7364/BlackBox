package blackbox;

import java.awt.EventQueue;

//Set Standard Values for Car Diagnosing 
enum StandardValues {
	RPM(6100), // RPM
	OL(50), // Oil Level
	FL(40), // Fuel Level
	TEMP(240), // Air Temperature
	TIRE(50); // Tire Pressure

	private final double standard;

	StandardValues(double standval) {
		this.standard = standval;
	}

	public double getSV() {
		return standard;
	}
}

public class BlackBoxTester {
	public static void main(String[] args) {
		/**
		 * Launch GUI application.
		 */
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BlackBoxSystem window = new BlackBoxSystem();
						// window.frame.setVisible(true);
											
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
		
	}
}
