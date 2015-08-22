package blackbox;
import blackbox.CarClasses.*;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TiresCard extends JPanel {
	private Car myCar;
//	private Sensors s;
	private OBD2Port obd;
	
	private JLabel pagename, lblBrandname, lblModel, lblServiceDate;
	private JLabel brandname, model, serviceDate, TireFLStatus, TireFRStatus, TireRLStatus, TireRRStatus;
	
	private JLabel TireFLAvg, TireFRAvg, TireRLAvg, TireRRAvg;
	
	public TiresCard(Car car, OBD2Port obd2) {
		super();
		myCar = car;
		obd = obd2;
		setLayout(null);
		
		pagename = new JLabel("Tires");
		pagename.setBounds(290, 11, 46, 14);
		add(pagename);
		
		lblBrandname = new JLabel("Brand");
		lblBrandname.setBounds(24, 81, 88, 14);
		add(lblBrandname);
		
		lblModel = new JLabel("Model");
		lblModel.setBounds(24, 121, 88, 14);
		add(lblModel);
		
		lblServiceDate = new JLabel("Last Service Date");
		lblServiceDate.setBounds(24, 161, 150, 14);
		add(lblServiceDate);
		
		brandname = new JLabel();
		brandname.setBounds(150, 81, 88, 14);
		brandname.setText(myCar.sysTires.getBrandName());
		add(brandname);
		
		model = new JLabel();
		model.setBounds(150, 121, 88, 14);
		model.setText(myCar.sysTires.getModelNumber());
		add(model);
		
		serviceDate = new JLabel(); 
		serviceDate.setBounds(150, 161, 88, 14);
		serviceDate.setText(myCar.sysTires.getServiceDate());
		add(serviceDate);
				
		TireFLAvg = new JLabel(); 
		TireFLAvg.setBounds(380, 50, 50, 14);
		add(TireFLAvg);

		TireFRAvg = new JLabel(); 
		TireFRAvg.setBounds(380, 70, 50, 14);
		add(TireFRAvg);

		TireRLAvg = new JLabel(); 
		TireRLAvg.setBounds(380, 90, 50, 14);
		add(TireRLAvg);

		TireRRAvg = new JLabel(); 
		TireRRAvg.setBounds(380, 110, 50, 14);
		add(TireRRAvg);
		
		TireFLStatus = new JLabel(); 
		TireFLStatus.setBounds(420, 50, 200, 14);
		add(TireFLStatus);
		
		TireFRStatus = new JLabel(); 
		TireFRStatus.setBounds(420, 70, 200, 14);
		add(TireFRStatus);
		
		TireRLStatus = new JLabel(); 
		TireRLStatus.setBounds(420, 90, 200, 14);
		add(TireRLStatus);
		
		TireRRStatus = new JLabel(); 
		TireRRStatus.setBounds(420, 110, 200, 14);
		add(TireRRStatus);

	}
	
	public void diagnoseTires()
	{
		MaxMinValues threshold = new MaxMinValues(myCar);
		
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal numbers
			
		double avgTFL = obd.readAvgDoubleData("TirePressure_LF");
		double avgTFR = obd.readAvgDoubleData("TirePressure_RF");
		double avgTRL = obd.readAvgDoubleData("TirePressure_LR");
		double avgTRR = obd.readAvgDoubleData("TirePressure_RR");
		
		TireFLAvg.setText(one.format(avgTFL));
		TireFRAvg.setText(one.format(avgTFL));
		TireRLAvg.setText(one.format(avgTRL));
		TireRRAvg.setText(one.format(avgTRR));
		
		if (avgTFL >= threshold.getMaxTirePressure()) { TireFLStatus.setText("Too High"); }
		else if (avgTFL < threshold.getMinTirePressure()) { TireFLStatus.setText("Low"); }
		else {TireFLStatus.setText("Normal");}
		
		if (avgTFR >= threshold.getMaxTirePressure()) { TireFRStatus.setText("Too High"); }
		else if (avgTFR < threshold.getMinTirePressure()) { TireFRStatus.setText("Low"); }
		else {TireFRStatus.setText("Normal");}
	
		if (avgTRL >= threshold.getMaxTirePressure()) { TireRLStatus.setText("Too High"); }
		else if (avgTRL < threshold.getMinTirePressure()) { TireRLStatus.setText("Low"); }
		else {TireRLStatus.setText("Normal");}

		if (avgTRR >= threshold.getMaxTirePressure()) { TireRRStatus.setText("Too High"); }
		else if (avgTRR < threshold.getMinTirePressure()) { TireRRStatus.setText("Low"); }
		else {TireRRStatus.setText("Normal");}
		}
	
	public void clearTC()
	{
		TireFLAvg.setText("");
		TireFRAvg.setText("");
		TireRLAvg.setText("");
		TireRRAvg.setText("");
		TireFLStatus.setText("");
		TireFRStatus.setText("");
		TireRLStatus.setText("");
		TireRRStatus.setText("");
	}
}