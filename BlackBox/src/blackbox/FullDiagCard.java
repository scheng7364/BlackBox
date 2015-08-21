package blackbox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Set Standard Values for Car items/parts
enum StandardValues {
	RPM(5500), // RPM
	OL(85), // Oil Level
	FL(60), // Fuel Level
	TEMP(180), // Air Temperature
	TIRE(40); // Tire Pressure

	private final double standard;

	StandardValues(double standval) {
		this.standard = standval;
	}

	public double getSV() {
		return standard;
	}
}

public class FullDiagCard extends JPanel {
	private BlackBoxSystem bt;
	private Car myCar = bt.thisCar.getCar();
	private Sensors s = bt.thisSensor;

	private JLabel rpmCV, olCV, flCV, TempCV, TireFLCV, TireFRCV, TireRLCV, TireRRCV;
	private JLabel rpmSV, olSV, flSV, TempSV, TireSV;
	private JLabel rpmStatus, olStatus, flStatus, TempStatus, TireFLStatus, TireFRStatus, TireRLStatus, TireRRStatus;

	public FullDiagCard() {
		setLayout(null);

		JLabel lblPart = new JLabel("Parts");
		lblPart.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPart.setBounds(124, 48, 46, 14);
		add(lblPart);

		JLabel lblCV = new JLabel("Current Values");
		lblCV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCV.setBounds(243, 48, 92, 14);
		add(lblCV);

		JLabel lblSV = new JLabel("Standard Values");
		lblSV.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSV.setBounds(392, 48, 103, 14);
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

		rpmCV = new JLabel("");
		rpmCV.setBounds(243, 91, 46, 14);
		add(rpmCV);

		olCV = new JLabel("");
		olCV.setBounds(243, 131, 46, 14);
		add(olCV);

		flCV = new JLabel("");
		flCV.setBounds(243, 171, 46, 14);
		add(flCV);

		TempCV = new JLabel("");
		TempCV.setBounds(243, 211, 46, 14);
		add(TempCV);

		TireFLCV = new JLabel("");
		TireFLCV.setBounds(243, 276, 46, 14);
		add(TireFLCV);

		TireFRCV = new JLabel("");
		TireFRCV.setBounds(243, 296, 46, 14);
		add(TireFRCV);

		TireRLCV = new JLabel("");
		TireRLCV.setBounds(243, 316, 46, 14);
		add(TireRLCV);

		TireRRCV = new JLabel("");
		TireRRCV.setBounds(243, 336, 46, 14);
		add(TireRRCV);

		rpmSV = new JLabel("");
		rpmSV.setBounds(392, 91, 46, 14);
		add(rpmSV);

		olSV = new JLabel("");
		olSV.setBounds(392, 131, 46, 14);
		add(olSV);

		flSV = new JLabel("");
		flSV.setBounds(392, 171, 46, 14);
		add(flSV);

		TempSV = new JLabel("");
		TempSV.setBounds(392, 211, 46, 14);
		add(TempSV);

		TireSV = new JLabel("");
		TireSV.setBounds(392, 296, 46, 14);
		add(TireSV);

		rpmStatus = new JLabel("");
		rpmStatus.setBounds(521, 91, 80, 14);
		add(rpmStatus);

		olStatus = new JLabel("");
		olStatus.setBounds(521, 131, 80, 14);
		add(olStatus);

		flStatus = new JLabel("");
		flStatus.setBounds(521, 171, 80, 14);
		add(flStatus);

		TempStatus = new JLabel("");
		TempStatus.setBounds(521, 211, 80, 14);
		add(TempStatus);

		TireFLStatus = new JLabel("");
		TireFLStatus.setBounds(521, 276, 80, 14);
		add(TireFLStatus);

		TireFRStatus = new JLabel("");
		TireFRStatus.setBounds(521, 296, 80, 14);
		add(TireFRStatus);

		TireRLStatus = new JLabel("");
		TireRLStatus.setBounds(521, 316, 80, 14);
		add(TireRLStatus);

		TireRRStatus = new JLabel("");
		TireRRStatus.setBounds(521, 336, 80, 14);
		add(TireRRStatus);

	}

	public void diagnoseFully() {
		DecimalFormat one = new DecimalFormat("#0.0"); // Set digits for decimal
														// numbers

		double[] tiresarray = new double[4];
		tiresarray = bt.thisSensor.getTiresPressure();

		// Get current values of car
		rpmCV.setText(one.format(bt.thisSensor.getCarRPM()));
		olCV.setText(one.format(bt.thisSensor.getCarOilLevel()));
		flCV.setText(one.format(bt.thisSensor.getCarFuelLevel()));
		TempCV.setText(one.format(bt.thisSensor.getCarIntAirTemp()));
		TireFLCV.setText(one.format(tiresarray[0]));
		TireFRCV.setText(one.format(tiresarray[1]));
		TireRLCV.setText(one.format(tiresarray[2]));
		TireRRCV.setText(one.format(tiresarray[3]));

		// Set threshold values
		rpmSV.setText(one.format(StandardValues.RPM.getSV()));
		olSV.setText(one.format(StandardValues.OL.getSV()));
		flSV.setText(one.format(StandardValues.FL.getSV()));
		TempSV.setText(one.format(StandardValues.TEMP.getSV()));
		TireSV.setText(one.format(StandardValues.TIRE.getSV()));

		// Results of the diagnose
		if (s.getCarRPM() > StandardValues.RPM.getSV()) {
			rpmStatus.setText("Too High");
		}

		if (s.getCarOilLevel() < StandardValues.OL.getSV()) {
			olStatus.setText("Too Low");
		}

		if (s.getCarFuelLevel() < StandardValues.FL.getSV()) {
			flStatus.setText("Low");
		}

		if (s.getCarIntAirTemp() > StandardValues.TEMP.getSV()) {
			TempStatus.setText("Too High");
		}

		if (tiresarray[0] > StandardValues.TIRE.getSV()) {
			TireFLStatus.setText("High");
		} else {
			TireFLStatus.setText("Normal");
		}

		if (tiresarray[1] > StandardValues.TIRE.getSV()) {
			TireFRStatus.setText("High");
		} else {
			TireFRStatus.setText("Normal");
		}

		if (tiresarray[2] > StandardValues.TIRE.getSV()) {
			TireRLStatus.setText("High");
		} else {
			TireRLStatus.setText("Normal");
		}

		if (tiresarray[3] > StandardValues.TIRE.getSV()) {
			TireRRStatus.setText("High");
		} else {
			TireRRStatus.setText("Normal");
		}
	}

	public void clearFDC() {
		rpmCV.setText("");
		olCV.setText("");
		flCV.setText("");
		TempCV.setText("");
		TireFLCV.setText("");
		TireFRCV.setText("");
		TireRLCV.setText("");
		TireRRCV.setText("");
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
