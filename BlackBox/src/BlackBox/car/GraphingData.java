package BlackBox.car;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
 
@SuppressWarnings("serial")
public class GraphingData extends JPanel {
    private double[] data;
    final int PAD = 20;
 
    public GraphingData (double [] myData){
    	this.data = myData;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }
 
    private double getMax() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
    
    public void getPlot(double[] myData){
    	JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new GraphingData(myData));
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
 
    public static void main(String[] args) {
    	//for testing within this class
	    } 
	    
}

