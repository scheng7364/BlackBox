package blackbox;

import java.awt.EventQueue;
import java.util.Random;

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