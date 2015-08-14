package blackbox;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RealTimeMonitor extends JPanel implements ActionListener {

	private JLabel lblSpeed = new JLabel("Current Speed:");
	private JLabel lblUnit = new JLabel("Mph");
	private JLabel lblTextSpeed = new JLabel();
	private JLabel lblTextPressure = new JLabel();
	private JButton btnStop = new JButton("Stop");
	private JButton btnStart = new JButton("Restart");

	Timer tm = new Timer(1500, this);
	
	// Constructor to add the GUI components to the window
	public RealTimeMonitor() {
		super();
		setLayout(null);
	
		add(lblSpeed);
		
		lblTextSpeed.setFont(new Font("Tahoma", Font.BOLD, 45));
		lblTextSpeed.setBounds(40, 20, 150, 150);
		add(lblTextSpeed);
				
		lblUnit.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblUnit.setBounds(180, 20, 150, 150);
		add(lblUnit);
		
		btnStop.setBounds(418, 11, 89, 23);
		add(btnStop);
		
		btnStart.setBounds(518, 11, 89, 23);
		add(btnStart);
		
		lblTextPressure.setBounds(418, 81, 89, 23);
		add(lblTextPressure);
		tm.start(); 
	}

	public void actionPerformed(ActionEvent e) {

		Car car1 = new Car();
		
		SensorsView view1 = new SensorsView(car1);
		
		car1.setSpeedAVG(40.0);
		car1.setSpeedSTD(20.0);
		
		car1.setSysTires(new Tires(20.0, 40.0, "","",""));
		
		DecimalFormat one = new DecimalFormat("#0.0");
		
		lblTextSpeed.setText(one.format(car1.getSpeed()));
		
		lblTextPressure.setText(one.format(car1.sysTires.getTirePressure()));
		
		// Stop button to stop the real time monitoring function
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm.stop();
			}
		});

		// Restart button to restart the real time monitoring function
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tm.start();
			}
		});
	}
	
	  @Override
	     public void paintComponent(Graphics g) {
	         super.paintComponent(g);   

			g.drawOval(20, 20, 150, 150);
		     
	    }
	  
}