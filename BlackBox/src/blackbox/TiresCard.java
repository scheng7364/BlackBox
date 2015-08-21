package blackbox;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TiresCard extends JPanel {
	private Car myCar;
	private Sensors s;
	
	private JLabel pagename, lblBrandname, lblModel, lblServiceDate;
	private JLabel brandname, model, serviceDate, text;
	
	private JLabel tireLF, tireLR, tireRF, tireRR;
	
	public TiresCard(Car car, Sensors sensor) {
		super();
		myCar = car;
		s = sensor;
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
				
		tireLF = new JLabel(); 
		tireLF.setBounds(380, 50, 50, 14);
		add(tireLF);

		tireLR = new JLabel(); 
		tireLR.setBounds(380, 70, 50, 14);
		add(tireLR);

		tireRF = new JLabel(); 
		tireRF.setBounds(380, 90, 50, 14);
		add(tireRF);

		tireRR = new JLabel(); 
		tireRR.setBounds(380, 110, 50, 14);
		add(tireRR);
		
		text = new JLabel(); 
		text.setBounds(380, 150, 200, 100);
		add(text);
	}
	
	public void diagnoseTires()
	{
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal numbers
		
		double[] tiresarray = new double[4];
		tiresarray = s.getTiresPressure();
		
		tireLF.setText(one.format(tiresarray[0]));
		tireLR.setText(one.format(tiresarray[1]));
		tireRF.setText(one.format(tiresarray[2]));
		tireRR.setText(one.format(tiresarray[3]));
		
		text.setText("Normal");
		for(int i = 0; i < 4; i++) {
			if(tiresarray[i] >= StandardValues.TIRE.getSV()) {
				text.setText("High Tire Pressure!");
			}
		}
	}
	
	public void clearTC()
	{
		tireLF.setText("");
		tireLR.setText("");
		tireRF.setText("");
		tireRR.setText("");
		text.setText("");

	}
}