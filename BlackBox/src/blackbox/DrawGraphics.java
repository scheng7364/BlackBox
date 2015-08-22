package blackbox;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawGraphics extends JPanel {

	private JLabel lblPart;
	private JComboBox selection;
	private JPanel panel = new JPanel();
	private int x, y;
	
	public DrawGraphics() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(620, 400));
		setLayout(null);
		
		lblPart = new JLabel("");
		lblPart.setForeground(Color.BLUE);
		lblPart.setBounds(10, 20, 150, 150);
		add(lblPart);

		selection = new JComboBox();
		selection.setModel(new DefaultComboBoxModel(new String[] { "Choose the part", "Engine", "Tires" }));
		selection.setBounds(497, 35, 123, 23);
		add(selection);
		
		addMouseMotionListener(new MouseEventAdapterA());
		addMouseListener(new MouseEventAdapterA());
	}

	class MouseEventAdapterA extends MouseAdapter {
		public void mouseMoved(MouseEvent e) {

			x = e.getX();
			y = e.getY();

			if ((x >= 235 && x <= 300) && (y >= 280 && y <= 360) || (x >= 465 && x <= 515) && (y >= 210 && y <= 280)) {
				lblPart.setText("Tires");
			
			} else if ((x >= 130 && x <= 250) && (y >= 215 && y <= 280)) {
				lblPart.setText("Engine");
				
			} else if ((x >= 90 && x <= 155) && (y >= 280 && y <= 315)) {
				lblPart.setText("Temperature");
			}

			else {
				lblPart.setText("Choose the part");
			}

			repaint();

		}
		
		public void mouseClicked(MouseEvent e) {
			String text = new String(lblPart.getText());
			
			if ((x >= 235 && x <= 300) && (y >= 280 && y <= 360) || (x >= 465 && x <= 515) && (y >= 210 && y <= 280)) {
				selection.setSelectedItem(text);
				repaint();

			} else if ((x >= 130 && x <= 250) && (y >= 215 && y <= 280)) {
				selection.setSelectedItem(text);
				
			} else if ((x >= 90 && x <= 155) && (y >= 280 && y <= 315)) {
				selection.setSelectedItem(text);

			} else {
				selection.setSelectedItem(text);

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
	
	public String getComboSelected() {
		return selection.getSelectedItem().toString();
	}

}