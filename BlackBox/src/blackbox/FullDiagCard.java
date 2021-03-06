/**
 * @(#)FullDiagCard.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox;

import blackbox.CarClasses.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class FullDiagCard extends JPanel {
	private Car thisCar;
	private OBD2Port obd;

	protected JLabel rpmAvg, olAvg, flAvg, tempAvg, tireFLAvg, tireFRAvg, tireRLAvg, tireRRAvg;
	protected JLabel rpmSV, olSV, flSV, tempSV, tireSV;
	protected JLabel rpmStatus, olStatus, flStatus, tempStatus, tireFLStatus, tireFRStatus, tireRLStatus, tireRRStatus;
	protected JLabel lbldate;
	protected JButton save;
	protected JButton saveToPDF;
	
	private String diagDate;
	private int size; // to suit to database;
	private ArrayList<String> list;
	
	// Connection to database
	Connection connection1 = ConnectionSqlite.dbConnector();

	public FullDiagCard(Car car, OBD2Port obd2) {
		super();
		thisCar = car;
		obd = obd2;
		diagDate = this.getDate();
		size = this.getColNum();
		list = new ArrayList<String>(size);
		this.createArrayList();
		setLayout(null);

		JLabel lblPart = new JLabel("Parts");
		lblPart.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPart.setBounds(124, 48, 46, 14);
		add(lblPart);

		JLabel lblAvg = new JLabel("Average Values");
		lblAvg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAvg.setBounds(243, 48, 92, 14);
		add(lblAvg);

		JLabel lblSV = new JLabel("Standard Values");
		lblSV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSV.setBounds(372, 48, 103, 14);
		add(lblSV);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(521, 48, 46, 14);
		add(lblStatus);

		JLabel lblReport = new JLabel("Diagnostic Report");
		lblReport.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReport.setBounds(233, 11, 151, 26);
		add(lblReport);

		JLabel lblEng = new JLabel("Engine");
		lblEng.setBounds(124, 91, 46, 14);
		add(lblEng);

		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblItem.setBounds(23, 48, 46, 14);
		add(lblItem);

		JLabel lblRPM = new JLabel("RPM");
		lblRPM.setBounds(23, 91, 46, 14);
		add(lblRPM);

		JLabel lblOilLvl = new JLabel("Oil Level");
		lblOilLvl.setBounds(23, 131, 70, 14);
		add(lblOilLvl);

		JLabel lblEng_1 = new JLabel("Engine");
		lblEng_1.setBounds(124, 131, 46, 14);
		add(lblEng_1);

		JLabel lblFuelLvl = new JLabel("Fuel Level");
		lblFuelLvl.setBounds(23, 171, 70, 14);
		add(lblFuelLvl);

		JLabel lblFuelSystem_1 = new JLabel("Fuel System");
		lblFuelSystem_1.setBounds(124, 171, 70, 14);
		add(lblFuelSystem_1);

		JLabel lblTemp = new JLabel("Air Temp");
		lblTemp.setBounds(23, 211, 70, 14);
		add(lblTemp);

		JLabel lblCoolingSystem_1 = new JLabel("Cooling System");
		lblCoolingSystem_1.setBounds(124, 211, 120, 14);
		add(lblCoolingSystem_1);

		JLabel lblTirePressures = new JLabel("Tire Pressures");
		lblTirePressures.setBounds(23, 251, 120, 14);
		add(lblTirePressures);

		JLabel lblTires_1 = new JLabel("Tires");
		lblTires_1.setBounds(124, 296, 46, 14);
		add(lblTires_1);

		JLabel lblFrontLeft = new JLabel("Front Left");
		lblFrontLeft.setBounds(23, 276, 70, 14);
		add(lblFrontLeft);

		JLabel lblFrontRight = new JLabel("Front Right");
		lblFrontRight.setBounds(23, 296, 70, 14);
		add(lblFrontRight);

		JLabel lblRearLeft = new JLabel("Rear Left");
		lblRearLeft.setBounds(23, 316, 70, 14);
		add(lblRearLeft);

		JLabel lblRearRight = new JLabel("Rear Right");
		lblRearRight.setBounds(23, 336, 70, 14);
		add(lblRearRight);

		rpmAvg = new JLabel("");
		rpmAvg.setBounds(243, 91, 46, 14);
		add(rpmAvg);

		olAvg = new JLabel("");
		olAvg.setBounds(243, 131, 46, 14);
		add(olAvg);

		flAvg = new JLabel("");
		flAvg.setBounds(243, 171, 46, 14);
		add(flAvg);

		tempAvg = new JLabel("");
		tempAvg.setBounds(243, 211, 46, 14);
		add(tempAvg);

		tireFLAvg = new JLabel("");
		tireFLAvg.setBounds(243, 276, 46, 14);
		add(tireFLAvg);

		tireFRAvg = new JLabel("");
		tireFRAvg.setBounds(243, 296, 46, 14);
		add(tireFRAvg);

		tireRLAvg = new JLabel("");
		tireRLAvg.setBounds(243, 316, 46, 14);
		add(tireRLAvg);

		tireRRAvg = new JLabel("");
		tireRRAvg.setBounds(243, 336, 46, 14);
		add(tireRRAvg);

		rpmSV = new JLabel("");
		rpmSV.setBounds(372, 91, 150, 14);
		add(rpmSV);

		olSV = new JLabel("");
		olSV.setBounds(372, 131, 150, 14);
		add(olSV);

		flSV = new JLabel("");
		flSV.setBounds(372, 171, 150, 14);
		add(flSV);

		tempSV = new JLabel("");
		tempSV.setBounds(372, 211, 150, 14);
		add(tempSV);

		tireSV = new JLabel("");
		tireSV.setBounds(372, 296, 150, 14);
		add(tireSV);

		rpmStatus = new JLabel("");
		rpmStatus.setBounds(521, 91, 120, 14);
		add(rpmStatus);

		olStatus = new JLabel("");
		olStatus.setBounds(521, 131, 120, 14);
		add(olStatus);

		flStatus = new JLabel("");
		flStatus.setBounds(521, 171, 120, 14);
		add(flStatus);

		tempStatus = new JLabel("");
		tempStatus.setBounds(521, 211, 120, 14);
		add(tempStatus);

		tireFLStatus = new JLabel("");
		tireFLStatus.setBounds(521, 276, 120, 14);
		add(tireFLStatus);

		tireFRStatus = new JLabel("");
		tireFRStatus.setBounds(521, 296, 120, 14);
		add(tireFRStatus);

		tireRLStatus = new JLabel("");
		tireRLStatus.setBounds(521, 316, 120, 14);
		add(tireRLStatus);

		tireRRStatus = new JLabel("");
		tireRRStatus.setBounds(521, 336, 120, 14);
		add(tireRRStatus);

		lbldate = new JLabel();
		lbldate.setText(diagDate);
		lbldate.setBounds(23, 11, 100, 20);
		add(lbldate);
		
		save = new JButton("Save");
		save.setBounds(521, 11, 80, 25);
		add(save);
		
		saveToPDF = new JButton("Save To PDF");
		saveToPDF.setBounds(251, 390, 80, 25);
		saveToPDF.setSize(new Dimension(120,30));
		add(saveToPDF);
		saveToPDF.setVisible(false);
		saveToPDF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				java.awt.Image image = getImageFromPanel(FullDiagCard.this);
				String fileName = "DiagnosisReport_"+ lbldate.getText()+ ".pdf";
		        printToPDF(image, fileName);
		        JOptionPane.showMessageDialog(null, "Data Saved to "+ fileName + ".");
			}
		});	
	}

	// Generate a random date for simulation purpose (so the user can retrieve the reports based on dates)
	private String getDate() {
		RandomDate randomDate = new RandomDate(LocalDate.of(2015, 1, 1), LocalDate.of(2015, 8, 27));
		return randomDate.nextDate().toString();
	}

	// Get the number of columns of a data table
	private int getColNum() {
		int colnum = 0;

		try {
			String querycol = "Select * from diagsheet";
			PreparedStatement pst = connection1.prepareStatement(querycol);
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			colnum = rsmd.getColumnCount();
			
			rs.close();
			pst.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		size = colnum;
		System.out.println(size);

		return size;
	}

	public void createArrayList() {

		for (int i = 0; i < size; i++) {
			list.add(i, "0.0");
		}
	}

	// Diagnose the car & generate a full report
	public void diagnoseFully() {
		// Get threshold values
		MaxMinValues threshold = new MaxMinValues(thisCar);
		
		// Set digits for decimal numbers
		DecimalFormat one = new DecimalFormat("#0.0");

		String date = this.getDate();
		double avgRPM = obd.readAvgDoubleData("RPM");
		double avgOil = obd.readAvgDoubleData("OilLevel");
		double avgFuel = obd.readAvgDoubleData("FuelLevel");
		double avgTemp = obd.readAvgDoubleData("IntAirTemp");
		double avgTFL = obd.readAvgDoubleData("TirePressure_LF");
		double avgTFR = obd.readAvgDoubleData("TirePressure_RF");
		double avgTRL = obd.readAvgDoubleData("TirePressure_LR");
		double avgTRR = obd.readAvgDoubleData("TirePressure_RR");

		// Get current values of car
		rpmAvg.setText(one.format(avgRPM));
		olAvg.setText(one.format(avgOil));
		flAvg.setText(one.format(avgFuel));
		tempAvg.setText(one.format(avgTemp));
		tireFLAvg.setText(one.format(avgTFL));
		tireFRAvg.setText(one.format(avgTFR));
		tireRLAvg.setText(one.format(avgTRL));
		tireRRAvg.setText(one.format(avgTRR));

		// Put threshold values onto labels
		rpmSV.setText((one.format(threshold.getMinRPM())) + " - " + (one.format(threshold.getMaxRPM())));
		olSV.setText((one.format(threshold.getMinOilLevelSensor())) + " - "
				+ (one.format(threshold.getMaxOilLevelSensor())));
		flSV.setText("Min: " + (one.format(threshold.getMinFuelLevel())));
		tempSV.setText(
				(one.format(threshold.getMinTemperature())) + " - " + (one.format(threshold.getMaxTemperature())));
		tireSV.setText(
				(one.format(threshold.getMinTirePressure())) + " - " + (one.format(threshold.getMaxTirePressure())));

		// Results of Diagnosing
		if (avgRPM >= threshold.getMaxRPM()) {
			rpmStatus.setText("Too High");
		} else if (avgRPM < threshold.getMinRPM()) {
			rpmStatus.setText("Low");
		} else {
			rpmStatus.setText("Normal");
		}

		if (avgOil >= threshold.getMaxOilLevelSensor()) {
			olStatus.setText("Too High");
		} else if (avgOil < threshold.getMinOilLevelSensor()) {
			olStatus.setText("Low");
		} else {
			olStatus.setText("Normal");
		}

		if (avgFuel < threshold.getMinFuelLevel()) {
			flStatus.setText("Low");
		} else {
			flStatus.setText("Normal");
		}

		if (avgTemp >= threshold.getMaxTemperature()) {
			tempStatus.setText("Too High");
		} else if (avgTemp < threshold.getMinTemperature()) {
			tempStatus.setText("Low");
		} else {
			tempStatus.setText("Normal");
		}

		if (avgTFL >= threshold.getMaxTirePressure()) {
			tireFLStatus.setText("Too High");
		} else if (avgTFL < threshold.getMinTirePressure()) {
			tireFLStatus.setText("Low");
		} else {
			tireFLStatus.setText("Normal");
		}

		if (avgTFR >= threshold.getMaxTirePressure()) {
			tireFRStatus.setText("Too High");
		} else if (avgTFR < threshold.getMinTirePressure()) {
			tireFRStatus.setText("Low");
		} else {
			tireFRStatus.setText("Normal");
		}

		if (avgTRL >= threshold.getMaxTirePressure()) {
			tireRLStatus.setText("Too High");
		} else if (avgTRL < threshold.getMinTirePressure()) {
			tireRLStatus.setText("Low");
		} else {
			tireRLStatus.setText("Normal");
		}

		if (avgTRR >= threshold.getMaxTirePressure()) {
			tireRRStatus.setText("Too High");
		} else if (avgTRR < threshold.getMinTirePressure()) {
			tireRRStatus.setText("Low");
		} else {
			tireRRStatus.setText("Normal");
		}

		String[] cols = { this.diagDate, rpmAvg.getText(), tempAvg.getText(), olAvg.getText(), flAvg.getText(),
				tireFLAvg.getText(), tireFRAvg.getText(), tireRLAvg.getText(), tireRRAvg.getText(),
				tempStatus.getText(), olStatus.getText(), flStatus.getText(), tireFLStatus.getText(),
				tireFRStatus.getText(), tireRLStatus.getText(), tireRRStatus.getText(), rpmStatus.getText(),
				rpmSV.getText(), tempSV.getText(), olSV.getText(), flSV.getText(), tireSV.getText()};
		
		// Save all the values into Arraylist
		for (int i = 0; i < size; i++) {
			list.set(i, cols[i]);
		}

	}

	// Save the generated values to database
	public void savetoDB() {
		// Pass the values to database
		try {
			// Send query to database
			String query = "Insert into diagsheet(date, carRPM, carTemp, carOil, carFuel, carTFL, carTFR, carTRL, carTRR, tempStatus, OilStatus, FuelStatus, TFLStatus, TFRStatus, TRLStatus, TRRStatus, RPMStatus, SVrpm, SVtemp, SVoil, SVfuel, SVTire) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = connection1.prepareStatement(query);

			// Write into database
			for (int i = 0; i < size; i++) {
				String item = list.get(i);
				int index = i + 1;
				pst.setString(index, item);
			}

			pst.executeUpdate();

			pst.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	// Clear the panel for next event
	public void clearFDC() {
		rpmAvg.setText("");
		olAvg.setText("");
		flAvg.setText("");
		tempAvg.setText("");
		tireFLAvg.setText("");
		tireFRAvg.setText("");
		tireRLAvg.setText("");
		tireRRAvg.setText("");
		rpmStatus.setText("");
		olStatus.setText("");
		flStatus.setText("");
		tempStatus.setText("");
		tireFLStatus.setText("");
		tireFRStatus.setText("");
		tireRLStatus.setText("");
		tireRRStatus.setText("");
	}
	
	public java.awt.Image getImageFromPanel(Component component) {

        BufferedImage image = new BufferedImage(component.getWidth(),
                component.getHeight(), BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
	
	// Enable to print the report to PDF
	public void printToPDF(java.awt.Image awtImage, String fileName) {
        try {
            Document d = new Document();
            PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(
                    fileName));
            d.open();

            Image iTextImage = Image.getInstance(writer, awtImage, 1);
            iTextImage.scalePercent(80);
            d.add(iTextImage);

            d.close();

        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
	

}
