package blackbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngineCard extends JPanel {
	
	private BlackBoxTester bt;
	private Car myCar = bt.thisCar.getCar();
	private Sensors s = bt.thisSensor;
	
	private JLabel pagename, lblType;
	private JLabel type, text1, text2;
	
	public EngineCard() {
		super();
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
		
		JButton td = new JButton("test");
		td.setBounds(380, 10, 100, 24);
		td.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Thread.sleep(1500);
					diagnoseEngine();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		add(td);
	}
	
	public void diagnoseEngine()
	{
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal numbers
		text1.setText(one.format(s.getCarRPM()));
		
		if(s.getCarRPM() >= 5800) {
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
