package blackbox;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
		lblPart.setForeground(Color.WHITE);
		lblPart.setBounds(10, 50, 150, 150);
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

			if ((x >= 224 && x <= 305) && (y >= 282 && y <= 367) || (x >= 477 && x <= 537) && (y >= 179 && y <= 265)) {
				lblPart.setText("Tires");
			
			} else if ((x >= 257 && x <= 303) && (y >= 211 && y <= 248)) {
				lblPart.setText("Engine");
			}

			else {
				lblPart.setText("Choose the part");
			}

			repaint();

		}
		
		public void mouseClicked(MouseEvent e) {
			String text = new String(lblPart.getText());
			
			if ((x >= 224 && x <= 305) && (y >= 282 && y <= 367) || (x >= 477 && x <= 537) && (y >= 179 && y <= 265)) {
				selection.setSelectedItem(text);

			} else if ((x >= 257 && x <= 303) && (y >= 211 && y <= 248)) {
				selection.setSelectedItem(text);

			} else {
				selection.setSelectedItem(text);

			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image background = new ImageIcon("image/exterior.jpg").getImage();
		g.drawImage(background, 50, 10, this);

		g.setColor(Color.GREEN);
		g.fillOval(x, y, 25, 25);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.YELLOW);
		g2d.setFont(new Font("", Font.BOLD, 13));
		g2d.drawString(lblPart.getText(), x, y);

	}
	
	public String getComboSelected() {
		return selection.getSelectedItem().toString();
	}

}