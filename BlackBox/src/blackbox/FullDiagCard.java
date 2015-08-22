package blackbox;
import blackbox.CarClasses.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FullDiagCard extends JPanel {
	private Car myCar;
//	private Sensors s;
	private OBD2Port obd;
	
	private JLabel rpmAvg, olAvg, flAvg, TempAvg, TireFLAvg, TireFRAvg, TireRLAvg, TireRRAvg;
	private JLabel rpmSV, olSV, flSV, TempSV, TireSV;
	private JLabel rpmStatus, olStatus, flStatus, TempStatus, TireFLStatus, TireFRStatus, TireRLStatus, TireRRStatus;

	public FullDiagCard(Car car, OBD2Port obd2) {
		super();
		myCar = car;
		obd = obd2;
		
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

		JLabel lblReport = new JLabel("Diagnose Report");
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

		TempAvg = new JLabel("");
		TempAvg.setBounds(243, 211, 46, 14);
		add(TempAvg);

		TireFLAvg = new JLabel("");
		TireFLAvg.setBounds(243, 276, 46, 14);
		add(TireFLAvg);

		TireFRAvg = new JLabel("");
		TireFRAvg.setBounds(243, 296, 46, 14);
		add(TireFRAvg);

		TireRLAvg = new JLabel("");
		TireRLAvg.setBounds(243, 316, 46, 14);
		add(TireRLAvg);

		TireRRAvg = new JLabel("");
		TireRRAvg.setBounds(243, 336, 46, 14);
		add(TireRRAvg);

		rpmSV = new JLabel("");
		rpmSV.setBounds(372, 91, 150, 14);
		add(rpmSV);

		olSV = new JLabel("");
		olSV.setBounds(372, 131, 150, 14);
		add(olSV);

		flSV = new JLabel("");
		flSV.setBounds(372, 171, 150, 14);
		add(flSV);

		TempSV = new JLabel("");
		TempSV.setBounds(372, 211, 150, 14);
		add(TempSV);

		TireSV = new JLabel("");
		TireSV.setBounds(372, 296, 150, 14);
		add(TireSV);

		rpmStatus = new JLabel("");
		rpmStatus.setBounds(521, 91, 120, 14);
		add(rpmStatus);

		olStatus = new JLabel("");
		olStatus.setBounds(521, 131, 120, 14);
		add(olStatus);

		flStatus = new JLabel("");
		flStatus.setBounds(521, 171, 120, 14);
		add(flStatus);

		TempStatus = new JLabel("");
		TempStatus.setBounds(521, 211, 120, 14);
		add(TempStatus);

		TireFLStatus = new JLabel("");
		TireFLStatus.setBounds(521, 276, 120, 14);
		add(TireFLStatus);

		TireFRStatus = new JLabel("");
		TireFRStatus.setBounds(521, 296, 120, 14);
		add(TireFRStatus);

		TireRLStatus = new JLabel("");
		TireRLStatus.setBounds(521, 316, 120, 14);
		add(TireRLStatus);

		TireRRStatus = new JLabel("");
		TireRRStatus.setBounds(521, 336, 120, 14);
		add(TireRRStatus);

	}

	public void diagnoseFully() {
		
		MaxMinValues threshold = new MaxMinValues(myCar);
		
		// Set digits for decimal numbers
		DecimalFormat one = new DecimalFormat("#0.0"); 
		
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
		TempAvg.setText(one.format(avgTemp));
		TireFLAvg.setText(one.format(avgTFL));
		TireFRAvg.setText(one.format(avgTFR));
		TireRLAvg.setText(one.format(avgTRL));
		TireRRAvg.setText(one.format(avgTRR));
		
		// Set threshold values
		rpmSV.setText((one.format(threshold.getMinRPM())) + " - " + (one.format(threshold.getMaxRPM())));
		olSV.setText((one.format(threshold.getMinOilLevelSensor())) + " - " + (one.format(threshold.getMaxOilLevelSensor())));
		flSV.setText("Min: " + (one.format(threshold.getMinFuelLevel())));
		TempSV.setText((one.format(threshold.getMinTemperature())) + " - " + (one.format(threshold.getMaxTemperature())));
		TireSV.setText((one.format(threshold.getMinTirePressure())) + " - " + (one.format(threshold.getMaxTirePressure())));

		System.out.println((one.format(threshold.getMinRPM())));
		
		// Results of Diagnosing
		if (avgRPM >= threshold.getMaxRPM()) { rpmStatus.setText("Too High"); }
		else if (avgRPM < threshold.getMinRPM()) { rpmStatus.setText("Low"); }
		else {rpmStatus.setText("Normal");}
		
		if (avgOil >= threshold.getMaxOilLevelSensor()) { olStatus.setText("Too High"); }
		else if (avgOil < threshold.getMinOilLevelSensor()) { olStatus.setText("Low"); }
		else {olStatus.setText("Normal");}

		if (avgFuel < threshold.getMinFuelLevel()) { flStatus.setText("Low"); }
		else {flStatus.setText("Normal");}
		
		if (avgTemp >= threshold.getMaxTemperature()) { TempStatus.setText("Too High"); }
		else if (avgTemp < threshold.getMinTemperature()) { TempStatus.setText("Low"); }
		else {TempStatus.setText("Normal");}
		
		if (avgTFL >= threshold.getMaxTirePressure()) { TireFLStatus.setText("Too High"); }
		else if (avgTFL < threshold.getMinTirePressure()) { TireFLStatus.setText("Low"); }
		else {TireFLStatus.setText("Normal");}
		
		if (avgTFR >= threshold.getMaxTirePressure()) { TireFRStatus.setText("Too High"); }
		else if (avgTFR < threshold.getMinTirePressure()) { TireFRStatus.setText("Low"); }
		else {TireFRStatus.setText("Normal");}
	
		if (avgTRL >= threshold.getMaxTirePressure()) { TireRLStatus.setText("Too High"); }
		else if (avgTRL < threshold.getMinTirePressure()) { TireRLStatus.setText("Low"); }
		else {TireRLStatus.setText("Normal");}

		if (avgTRR >= threshold.getMaxTirePressure()) { TireRRStatus.setText("Too High"); }
		else if (avgTRR < threshold.getMinTirePressure()) { TireRRStatus.setText("Low"); }
		else {TireRRStatus.setText("Normal");}
	}

	public void clearFDC() {
		rpmAvg.setText("");
		olAvg.setText("");
		flAvg.setText("");
		TempAvg.setText("");
		TireFLAvg.setText("");
		TireFRAvg.setText("");
		TireRLAvg.setText("");
		TireRRAvg.setText("");
		rpmStatus.setText("");
		olStatus.setText("");
		flStatus.setText("");
		TempStatus.setText("");
		TireFLStatus.setText("");
		TireFRStatus.setText("");
		TireRLStatus.setText("");
		TireRRStatus.setText("");

	}

}
