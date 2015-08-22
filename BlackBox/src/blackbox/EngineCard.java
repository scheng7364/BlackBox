package blackbox;
import blackbox.CarClasses.*;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineCard extends JPanel {
	private Car myCar;
//	private Sensors s;
	private OBD2Port obd;
	
	private JLabel pagename, lblType;
	private JLabel type, rpmAvg, rpmStatus;
	
	public EngineCard(Car car, OBD2Port obd2) {
		super();
		myCar = car;
		obd = obd2;
		
		setLayout(null);
		
		pagename = new JLabel("Engine");
		pagename.setBounds(290, 11, 46, 14);
		add(pagename);
		
		lblType = new JLabel("Type");
		lblType.setBounds(24, 81, 88, 14);
		add(lblType);
		
		JLabel type = new JLabel();
		type.setBounds(150, 81, 180, 14);
		type.setText(myCar.sysEngine.getType());
		add(type);
	
		rpmAvg = new JLabel(); 
		rpmAvg.setBounds(380, 81, 100, 40);
		add(rpmAvg);
		
		rpmStatus = new JLabel(); 
		rpmStatus.setBounds(380, 131, 200, 40);
		add(rpmStatus);
		
	}
	
	public void diagnoseEngine()
	{
		MaxMinValues threshold = new MaxMinValues(myCar);
		
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal numbers
		
		double avgRPM = obd.readAvgDoubleData("RPM");
		
		if (avgRPM >= threshold.getMaxRPM()) { rpmStatus.setText("Too High"); }
		else if (avgRPM < threshold.getMinRPM()) { rpmStatus.setText("Low"); }
		else {rpmStatus.setText("Normal");}
		
		rpmAvg.setText(one.format(avgRPM));
	}
	
}
