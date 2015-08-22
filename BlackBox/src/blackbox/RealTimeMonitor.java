package blackbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RealTimeMonitor extends JPanel {
	private CarFacade myCar;
	private Sensors sensor;

	Timer tm;
	private static int PERIOD = 1000;

	private JLabel lblSpeed = new JLabel("Current Speed:");
	private JLabel lblUnit = new JLabel("Mph");
	private JLabel lblTextSpeed = new JLabel();
	private JLabel lblWarning = new JLabel();
	private JButton btnStop = new JButton("Stop");
	private JButton btnStart = new JButton("Restart");

	// Constructor to add the GUI components to the window
	public RealTimeMonitor(CarFacade cf, Sensors s) {
		super();
		myCar = cf;
		sensor = s;
		
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

				myCar.setCarStopped(true);
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

				myCar.setCarStopped(false);
				tm.start();
			
			}
		});
	}

	ActionListener timerListener = new ActionListener() {

		public void actionPerformed(ActionEvent evt) {

			SensorsMonitor monitor = new SensorsMonitor(sensor);

			// Set digits for decimal numbers
			DecimalFormat one = new DecimalFormat("#0.0"); 

			lblTextSpeed.setText(one.format(sensor.getCarSpeed()));

			sensor.ifHealthy();

			// If sensor warning is true, RealTimeMonitor will show warning & stop the car
			if (sensor.getWarning()) {
				lblWarning.setForeground(Color.RED);
				lblWarning.setText("Warning");
			
//				myCar.setCarStopped(true);
//				tm.stop();
			}
			// If sensor warning is false, RealTimeMonitor will show healthy & car runs;			
			else { 
				lblWarning.setForeground(Color.GREEN);
				lblWarning.setText("Healthy"); 
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
