package blackbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RealTimeMonitor extends JPanel {

	private BlackBoxSystem bt;
	private CarFacade myCar = bt.thisCar;
	private Sensors sensor = bt.thisSensor;

	private SensorsView view1 = new SensorsView(sensor);

	Timer tm;
	private static int PERIOD = 1000;

	/*
	 * public static CarFacade myCar = new CarFacade();; public static Sensors
	 * sensor = new Sensors();
	 */
	private JLabel lblSpeed = new JLabel("Current Speed:");
	private JLabel lblUnit = new JLabel("Mph");
	private JLabel lblTextSpeed = new JLabel();
	private JLabel lblWarning = new JLabel();
	private JButton btnStop = new JButton("Stop");
	private JButton btnStart = new JButton("Restart");

	// Constructor to add the GUI components to the window
	public RealTimeMonitor() {
		super();
		setLayout(null);

		add(lblSpeed);

		lblTextSpeed.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextSpeed.setBounds(40, 20, 150, 150);
		lblTextSpeed.setText("0.0");
		add(lblTextSpeed);

		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblUnit.setBounds(180, 20, 150, 150);
		add(lblUnit);

		btnStop.setBounds(518, 11, 89, 23);
		add(btnStop);

		btnStart.setBounds(418, 11, 89, 23);
		add(btnStart);

		lblWarning.setBounds(400, 119, 100, 14);
		add(lblWarning);

		// Stop button to stop the real time monitoring function
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bt.thisCar.setCarStopped(true);
				tm.stop();

				// Thread sleeps to simulate the car slowing-down period
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();

				}
				lblTextSpeed.setText("0.0");

			}
		});

		// Restart button to start the real time monitoring function
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bt.thisCar.setCarStopped(false);
				tm.start();
			}
		});
	}

	ActionListener timerListener = new ActionListener() {

		public void actionPerformed(ActionEvent evt) {

			// SensorsView view1 = new SensorsView(sensor);

			DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for
															// decimal
															// numbers

			lblTextSpeed.setText(one.format(bt.thisSensor.getCarSpeed()));

			lblWarning.setForeground(Color.GREEN);
			lblWarning.setText("Healthy");

			if (!sensor.ifHealthy()) {
				lblWarning.setForeground(Color.RED);
				lblWarning.setText("Warning");

			}
		}
	};

	public void startRun() {

		if (tm == null) {

			tm = new Timer(PERIOD, timerListener); // Change every second;
			tm.start();
		}
	}

	public void stopRun() {

		tm.stop();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawOval(20, 20, 150, 150);

	}

}
