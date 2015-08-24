package blackbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RealTimeMonitor extends JPanel implements Observer {
	private CarFacade myCar;
	private Sensors sensor;
	// private SensorsMonitor monitor;
	
	Timer tm;
	boolean timerPause = true;
	private static int PERIOD = 1000;

	private JLabel lblSpeed = new JLabel("Current Speed:");
	private JLabel lblUnitSpeed = new JLabel("MPH");
	private JLabel lblTextSpeed = new JLabel();

	private JLabel lblRPM = new JLabel("Current RPM:");
	private JLabel lblUnitRPM = new JLabel("RPM");
	private JLabel lblTextRPM = new JLabel();

	private JLabel lblTemperature = new JLabel("Current Temperature:");
	private JLabel lblUnitTemperature = new JLabel("Celcius");
	private JLabel lblTextTemperature = new JLabel();

	private JLabel lblOil = new JLabel("Current Oil Level:");
	private JLabel lblUnitOil = new JLabel("Quarts");
	private JLabel lblTextOil = new JLabel();

	private JLabel lblFuel = new JLabel("Current Temperature:");
	private JLabel lblUnitFuel = new JLabel("Gallons");
	private JLabel lblTextFuel = new JLabel();

	private JLabel lblWarning = new JLabel();
	private JButton btnStop = new JButton("Stop");
	private JButton btnStart = new JButton("Restart");

	// Constructor to add the GUI components to the window
	public RealTimeMonitor(CarFacade cf, Sensors s) {
		super();
		myCar = cf;
		sensor = s;
		sensor.addObserver(this);

		setLayout(null);

		add(lblSpeed);

		lblTextSpeed.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextSpeed.setBounds(35, 0, 150, 150);
		lblTextSpeed.setForeground(Color.WHITE);
		lblTextSpeed.setText("0.0");
		add(lblTextSpeed);

		lblUnitSpeed.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUnitSpeed.setBounds(200, 15, 400, 150);
		lblUnitSpeed.setForeground(Color.WHITE);
		add(lblUnitSpeed);

		lblTextRPM.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextRPM.setBounds(35, 130, 300, 150);
		lblTextRPM.setForeground(Color.WHITE);
		lblTextRPM.setText("0.0");
		add(lblTextRPM);

		lblUnitRPM.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUnitRPM.setBounds(200, 145, 300, 150);
		lblUnitRPM.setForeground(Color.WHITE);
		add(lblUnitRPM);

		lblTextTemperature.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextTemperature.setBounds(35, 260, 300, 150);
		lblTextTemperature.setForeground(Color.WHITE);
		lblTextTemperature.setText("0.0");
		add(lblTextTemperature);

		lblUnitTemperature.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUnitTemperature.setBounds(175, 275, 400, 150);
		lblUnitTemperature.setForeground(Color.WHITE);
		add(lblUnitTemperature);

		lblTextOil.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextOil.setBounds(330, 130, 300, 150);
		lblTextOil.setForeground(Color.WHITE);
		lblTextOil.setText("0.0");
		add(lblTextOil);

		lblUnitOil.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUnitOil.setBounds(475, 145, 400, 150);
		lblUnitOil.setForeground(Color.WHITE);
		add(lblUnitOil);

		lblTextFuel.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextFuel.setBounds(330, 260, 300, 150);
		lblTextFuel.setForeground(Color.WHITE);
		lblTextFuel.setText("0.0");
		add(lblTextFuel);

		lblUnitFuel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUnitFuel.setBounds(470, 275, 400, 150);
		lblUnitFuel.setForeground(Color.WHITE);
		add(lblUnitFuel);

		btnStop.setBounds(450, 400, 90, 23);
		add(btnStop);

		btnStart.setBounds(350, 400, 90, 23);
		add(btnStart);

		lblWarning.setBounds(350, 0, 300, 150);
		lblWarning.setForeground(Color.WHITE);
//		lblWarning.setText("Starting");
		lblWarning.setFont(new Font("Tahoma", Font.BOLD, 45));
		add(lblWarning);

		// Stop button to stop the real time monitoring function
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				myCar.setCarStopped(true);
				//tm.stop();
				timerPause = true;
				// Thread sleeps to simulate the car slowing-down period
				try {
					Thread.sleep(1000);
				} catch (Exception ex) {
					ex.printStackTrace();

				}
				lblTextSpeed.setText("0.0");
				lblTextRPM.setText("0.0");
				lblTextTemperature.setText("0.0");
				lblTextOil.setText("0.0");
				lblTextFuel.setText("0.0");
				lblWarning.setForeground(Color.WHITE);
				lblWarning.setText("Stopped");

			}
		});

		// Restart button to start the real time monitoring function
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sensor.setStarting(true);
			
				myCar.setCarStopped(false);
				// tm.start();
				timerPause = false;
				
				

			}
		});

		tm = new Timer(PERIOD, timerListener); // Change every second;
		tm.start();
	}

	ActionListener timerListener = new ActionListener() {

		public void actionPerformed(ActionEvent evt) {

			// SensorsMonitor monitor = new SensorsMonitor(sensor);

			// Set digits for decimal numbers
			DecimalFormat one = new DecimalFormat("#0.0");

			if (!timerPause) {

				lblTextSpeed.setText(one.format(sensor.getCarSpeed()));
				lblTextRPM.setText(one.format(sensor.getCarRPM()));
				lblTextTemperature.setText(one.format(sensor.getCarIntAirTemp()));
				lblTextOil.setText(one.format(sensor.getCarOilLevel()));
				lblTextFuel.setText(one.format(sensor.getCarFuelLevel()));

				sensor.ifHealthy(); // Check the car running status: starting or healthy or warning
			}
		}
	};

	public void startRun() {

		// if (tm == null) {
		// tm = new Timer(PERIOD, timerListener); // Change every second;
		// tm.start();
		// }
		myCar.setCarStopped(false);
		timerPause = false;

	}

	public void stopRun() {

		tm.stop();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// g.drawOval(20, 20, 150, 150);
		Image meter = new ImageIcon("image/Meter.jpg").getImage();
		g.drawImage(meter, 5, 5, this);
		g.drawImage(meter, 5, 135, this);
		g.drawImage(meter, 5, 265, this);

		g.drawImage(meter, 300, 5, this);
		g.drawImage(meter, 300, 135, this);
		g.drawImage(meter, 300, 265, this);

	}

	@Override
	public void update(Observable obale, Object ifHealthy) {
		boolean healthy = (Boolean) ifHealthy;
		System.out.println("monitor: " + healthy);
		
		// If car is just starting
		if(sensor.getStarting()) {
			lblWarning.setForeground(Color.WHITE);
			lblWarning.setText("Starting");
			sensor.setStarting(false);
		}
		
		// If car is in healthy condition
		else if (healthy) {
			lblWarning.setForeground(Color.GREEN);
			lblWarning.setText("Healthy");

		}
		// If car is in warning condition
		else {
			lblWarning.setForeground(Color.RED);
			lblWarning.setText("Warning");
		}

	}

}
