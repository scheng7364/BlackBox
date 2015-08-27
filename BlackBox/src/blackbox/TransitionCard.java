/**
 * @(#)TansitionCard.java
 * 
 * This class is used for transition while 
 * waiting for reports
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TransitionCard extends JPanel implements ActionListener {

	int x = 200, speed = 10;
	Timer tm = new Timer(50, this);
	
	public TransitionCard() {
		super();
		setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.fillOval(x, 200, 10, 10);
		
		g.setColor(Color.ORANGE);
		g.fillOval(x + 20, 200, 10, 10);
		
		g.setColor(Color.YELLOW);
		g.fillOval(x + 40, 200, 10, 10);
		tm.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (x < 200 || x > 400) {
			speed = -speed;
		}

		x = x + speed;
		repaint();
	}
}
