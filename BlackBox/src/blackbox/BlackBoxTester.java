package blackbox;

import java.awt.EventQueue;

public class BlackBoxTester {

	/*public static CarFacade thisCar = new CarFacade(); 
	public static Sensors thisSensor = new Sensors();
	public static OBD2Port thisOBD = new OBD2Port(thisCar);
*/
	
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
