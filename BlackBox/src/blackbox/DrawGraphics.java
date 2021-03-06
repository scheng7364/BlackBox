/**
 * @(#)DrawGraphics.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox;

import javax.swing.*;

import blackbox.CarClasses.Car;
import blackbox.CarClasses.Honda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DrawGraphics extends JPanel {

	private JLabel lblPart;
	private JLabel lblTireMake;
	private JLabel lblTireModel;
	private JLabel lblTireFL;
	private JLabel lblTireFR;
	private JLabel lblTireRL;
	private JLabel lblTireRR;
	private JLabel lblTemperature;	
	private JLabel lblEngineType;
	private JLabel lblHorsePower;
	private JLabel lblRPM;
	private JLabel lblOilLevel;
	private JComboBox selection;
	private JPanel panel = new JPanel();
	private int x, y;
	private JButton btnDiag;
	private Car thisCar;
	
	public void setBtnDiag(JButton btn) {
		btnDiag = btn;
	}
	
	public DrawGraphics(Car car) {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(620, 400));
		setLayout(null);
		
		lblPart = new JLabel("");
		lblPart.setForeground(Color.BLUE);
		lblPart.setBounds(400, 400, 300, 150);
		add(lblPart);
		thisCar = car;
		
		NumberFormat formatter = new DecimalFormat("#0.0"); 
		
//////////////////////////////////tires//////////////////////////////////
		
		lblTireMake = new JLabel("Tire Brand: " + thisCar.getSysTires().getBrandName());
		lblTireMake.setForeground(Color.BLUE);
		lblTireMake.setBounds(5, 0, 150, 150);
		add(lblTireMake);
		
		lblTireModel = new JLabel("Tire Model: " + thisCar.getSysTires().getModelNumber());
		lblTireModel.setForeground(Color.BLUE);
		lblTireModel.setBounds(250, 0, 150, 150);
		add(lblTireModel);
		
		lblTireFL = new JLabel("Front Left Tire: " + 
		formatter.format(thisCar.getSysTires().getTirePressure()));
		lblTireFL.setForeground(Color.BLUE);
		lblTireFL.setBounds(5, 25, 150, 150);
		add(lblTireFL);
		
		lblTireFR = new JLabel("Front Right Tire: " +
		formatter.format(thisCar.getSysTires().getTirePressure()));
		lblTireFR.setForeground(Color.BLUE);
		lblTireFR.setBounds(5, 50, 150, 150);
		add(lblTireFR);
		
		lblTireRL = new JLabel("Rear Left Tire: " +
		formatter.format(thisCar.getSysTires().getTirePressure()));
		lblTireRL.setForeground(Color.BLUE);
		lblTireRL.setBounds(5, 75, 150, 150);
		add(lblTireRL);
		
		lblTireRR = new JLabel("Rear Right Tire: " + 
		formatter.format(thisCar.getSysTires().getTirePressure()));
		lblTireRR.setForeground(Color.BLUE);
		lblTireRR.setBounds(5, 100, 150, 150);
		add(lblTireRR);
		
		tireOff();
		
//////////////////////////////////Temperature//////////////////////////////////
		
		lblTemperature = new JLabel("Temperature: " +
		formatter.format(thisCar.getSysCooling().getTemperature()));
		lblTemperature.setForeground(Color.BLUE);
		lblTemperature.setBounds(5, 0, 150, 150);
		add(lblTemperature);
		
		temperatureOff();

//////////////////////////////////Engine//////////////////////////////////
		
		lblEngineType = new JLabel("Engine Type: " + thisCar.getSysEngine().getType());
		lblEngineType.setForeground(Color.BLUE);
		lblEngineType.setBounds(5, 0, 500, 150);
		add(lblEngineType);
		
		lblHorsePower = new JLabel("Horsepower: " + 
		formatter.format(thisCar.getSysEngine().getHorsePower()));
		lblHorsePower.setForeground(Color.BLUE);
		lblHorsePower.setBounds(5, 25, 150, 150);
		add(lblHorsePower);
		
		lblRPM = new JLabel("Engine RPM: " + 
		formatter.format(thisCar.getSysEngine().getRPM()));
		lblRPM.setForeground(Color.BLUE);
		lblRPM.setBounds(5, 50, 150, 150);
		add(lblRPM);
		
		engineOff();
		
		addMouseMotionListener(new MouseEventAdapterA());
		addMouseListener(new MouseEventAdapterA());
	}

	// Enable components related to tire
	public void tireOn(){
		lblTireFL.setVisible(true);
		lblTireFR.setVisible(true);
		lblTireRL.setVisible(true);
		lblTireRR.setVisible(true);
		lblTireMake.setVisible(true);
		lblTireModel.setVisible(true);
	}
	
	// Disable components related to tire
	public void tireOff(){
		lblTireFL.setVisible(false);
		lblTireFR.setVisible(false);
		lblTireRL.setVisible(false);
		lblTireRR.setVisible(false);
		lblTireMake.setVisible(false);
		lblTireModel.setVisible(false);
	}
	
	// Enable components related to temperature
	public void temperatureOn(){
		lblTemperature.setVisible(true);
	}
	
	// Disable components related to temperature
	public void temperatureOff(){
		lblTemperature.setVisible(false);
	}
	
	// Enable components related to engine
	public void engineOn(){
		lblEngineType.setVisible(true);
		lblHorsePower.setVisible(true);
		lblRPM.setVisible(true);
	}
	
	// Disable components related to engine
	public void engineOff(){
		lblEngineType.setVisible(false);
		lblHorsePower.setVisible(false);
		lblRPM.setVisible(false);
	}
	
	class MouseEventAdapterA extends MouseAdapter {
		public void mouseMoved(MouseEvent e) {
			// Get coordinates of the mouse location 
			x = e.getX();
			y = e.getY();

			if ((x >= 235 && x <= 300) && (y >= 280 && y <= 360) || (x >= 465 && x <= 515) && (y >= 210 && y <= 280)) {
				lblPart.setText("Tires");
			
			} else if ((x >= 130 && x <= 250) && (y >= 215 && y <= 280)) {
				lblPart.setText("Engine");
				
			} else if ((x >= 90 && x <= 155) && (y >= 280 && y <= 315)) {
				lblPart.setText("Temperature");
			} else if ((x >= 220 && x <= 450) && (y >= 117 && y <= 205)) {
				lblPart.setText("Full Diagnosis");
			}

			else {
				lblPart.setText("Select a part");
			}

			repaint();

		}
		
		public void mouseClicked(MouseEvent e) {
			String text = new String(lblPart.getText());
			
			// Enable tire selection
			if ((x >= 235 && x <= 300) && (y >= 280 && y <= 360) || (x >= 465 && x <= 515) && (y >= 210 && y <= 280)) {
				tireOn();
				temperatureOff();
				engineOff();
				repaint();
				
			} // Enable engine selection
			else if ((x >= 130 && x <= 250) && (y >= 215 && y <= 280)) {
				temperatureOff();
				engineOn();
				tireOff();
				
			} // Enable temperature selection 
			else if ((x >= 90 && x <= 155) && (y >= 280 && y <= 315)) {
				temperatureOn();
				engineOff();
				tireOff();

			} // Enable "full diagnose" 
			else if ((x >= 220 && x <= 450) && (y >= 117 && y <= 205)) {
				temperatureOff();
				engineOff();
				tireOff();

				// Simulate the mouse click as button click
				btnDiag.doClick(); 
			
			} else {
				//Do nothing;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Show the full car image
		Image background = new ImageIcon("image/exterior.jpg").getImage();
		g.drawImage(background, 75, 100, this);

		// Show the car part image upon mouse over & mouse clicked
		if ((x >= 235 && x <= 300) && (y >= 280 && y <= 360) || (x >= 465 && x <= 515) && (y >= 210 && y <= 280)) {
			Image tires = new ImageIcon("image/TireImage.jpg").getImage();
			g.drawImage(tires, 75, 100, this);
		} else if ((x >= 130 && x <= 250) && (y >= 215 && y <= 280)) {
			Image engine = new ImageIcon("image/Driveline-Powertrain.jpg").getImage();
			g.drawImage(engine, 75, 100, this);
		} else if ((x >= 90 && x <= 155) && (y >= 280 && y <= 315)) {
			Image engine = new ImageIcon("image/climate-control.jpg").getImage();
			g.drawImage(engine, 75, 100, this);
		} else if ((x >= 220 && x <= 450) && (y >= 117 && y <= 205)) {
			Image engine = new ImageIcon("image/full-car.jpg").getImage();
			g.drawImage(engine, 75, 100, this);
		}
		
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 25, 25);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("", Font.BOLD, 13));
		g2d.drawString(lblPart.getText(), x, y);

	}
	
	// Return the the selected item as string
	public String getComboSelected() {
		return selection.getSelectedItem().toString();
	}

}
