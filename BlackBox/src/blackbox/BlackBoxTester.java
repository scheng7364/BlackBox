package blackbox;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BlackBoxTester {
	
	public static CarFacade thisCar = new CarFacade();
	public static Sensors thisSensor = new Sensors();

	public static void main(String[] args) {
		/**
		 * Launch GUI application.
		 */
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						BlackBoxSystem window = new BlackBoxSystem();
						// window.frame.setVisible(true);
						
						// Car starts upon clicking the welcome page
						// It will automatically switch to Real-Time Monitor
						window.firstCard.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent me) {
								thisCar.setCarStopped(false);
								thisCar.start();
								window.cards.show(window.cardPanel, "Real-Time Monitor");
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}

	}
