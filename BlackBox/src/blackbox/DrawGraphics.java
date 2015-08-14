package blackbox;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class DrawGraphics extends JPanel{
	
	DrawGraphics() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(620, 400));
	} 
	 
     @Override
     public void paintComponent(Graphics g) {
         super.paintComponent(g);   

		Image background = new ImageIcon("image/background.jpg").getImage();
		g.drawImage(background, 50, 10, this);
	     
    }
}