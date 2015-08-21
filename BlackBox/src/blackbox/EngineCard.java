package blackbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineCard extends JPanel {
	private Car myCar;
	private Sensors s;
	
	private JLabel pagename, lblType;
	private JLabel type, text1, text2;
	
	public EngineCard(Car car, Sensors sensor) {
		super();
		myCar = car;
		s = sensor;
		
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
	
		text1 = new JLabel(); 
		text1.setBounds(380, 150, 200, 100);
		add(text1);
		
		text2 = new JLabel(); 
		text2.setBounds(380, 170, 200, 100);
		add(text2);
		
	}
	
	public void diagnoseEngine()
	{
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal numbers
		text1.setText(one.format(s.getCarRPM()));
		
		if(s.getCarRPM() >= StandardValues.RPM.getSV()) {
			text2.setText("RPM is too High");
		}
		else
		{
			text2.setText("Normal");
		}
	
		}
	
	public void clearEC()
	{
		text1.setText("");
		text2.setText("");
	}
}
