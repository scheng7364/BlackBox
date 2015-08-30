/**
 * @(#)BlackBoxSystem.java
 * 
 * @author Kevin Childs, Shen Cheng, Xiao Xiao
 * @version 1.0
*/

package blackbox;

import blackbox.CarClasses.Car;
import blackbox.CarClasses.Honda;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

public class BlackBoxSystem {

	Car car = new Honda(); // Car Object to be used throughout
	CarFacade thisCar = new CarFacade(car); 

	OBD2Port obd = thisCar.getObdPort();
	DriverProfile profile = new DriverProfile();
	Sensors s = new Sensors(obd, car, profile);
	RealTimeMonitor realTimeMonitor = new RealTimeMonitor(thisCar, s);

	CarFacade digCar = new CarFacade(car);
	Car carDig = digCar.getCar();
	OBD2Port obdDig = digCar.getObdPort();

	private JFrame guiFrame;
	private CardLayout cards;
	private JPanel buttonPanel;
	private JPanel cardPanel;
	private JPanel diagCard;
	private JTextField tfUsername;
	private JPasswordField pfPW;
	private JLabel lblUser; // show the user name on buttonPanel after login
	private JLabel lblFirst; // show welcome user after login
	private JLabel lblWelcome;
	private String name; // username to be displayed
	private String driveStyle; // driving styles for different users
	private JLabel lblShowText; // in Log Sheet card;
	private JPanel rtmCard; // Card for real-time monitoring
	private JPanel firstCard; // Card for start-engine button
	private JLabel total, total_1, pass, rate;
	private JTable logTable;
	private JPanel starpanel, passpanel, reportStatuspanel, piechart;

	// Set up sqlite connection
	Connection connection1 = ConnectionSqlite.dbConnector();

	private double passrate, personpass, persontotal;

	// Pass rate is used to calculate the ratio of "pass" records over total
	// records
	public void setPassRate(double value) {
		this.passrate = value;
	}

	public double getPassRate() {
		return this.passrate;
	}

	// Pass rate for individuals
	public void setPersonPass(double value) {
		this.personpass = value;
	}

	public double getPersonPass() {
		return this.personpass;
	}

	// Individual total records
	public void setPersonTotal(double value) {
		this.persontotal = value;
	}

	public double getPersonTotoal() {
		return this.persontotal;
	}

	private String[] namelist;

	// Get list of distinct usernames from database
	public String[] getNameList() {
		ArrayList<String> items = new ArrayList<String>();
		String[] stringlist = new String[3];

		try {
			// Send query to database
			PreparedStatement pst = connection1.prepareStatement("Select distinct username from logsheet");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String currentname = rs.getString("username");
				// Save the distinct names to arraylist
				items.add(currentname);
			}

			// Copy the list from arraylist to String array
			for (int i = 0; i < items.size(); i++) {
				stringlist[i] = (String) items.get(i);
			}

			namelist = stringlist;
			rs.close();
			pst.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return this.namelist;
	}

	private double[] percentage;

	// Percentage of selected records over total records
	public double[] getPercentage(String pORw) {
		ArrayList<Double> items = new ArrayList<Double>();
		String[] listofname = getNameList();
		double[] listresult = new double[3];

		// Get total records of a table
		double total = getTotalPW("logsheet", pORw);
		double count = 0;

		// Get the number of "pass" or "warning" records for individuals
		for (int i = 0; i < listofname.length; i++) {
			try {
				// Send query to database (get "pass" or "warning" reports for
				// one user
				PreparedStatement pst = connection1.prepareStatement("Select * from logsheet where username = '"
						+ listofname[i] + "' and reportStatus = '" + pORw + "'");
				ResultSet rs = pst.executeQuery();

				while (rs.next()) {

					count++;
				}

				items.add(count);
				count = 0;

				rs.close();
				pst.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		// Calculate the percentage
		for (int i = 0; i < items.size(); i++) {
			double result = (double) items.get(i) / total;
			listresult[i] = result;
		}

		percentage = listresult;
		return this.percentage;
	}

	/**
	 * Create the application.
	 */
	public BlackBoxSystem() {

		JPanel welcomeCard; // Welcome page;
		JPanel LogSheetCard; // Card for rating;

		guiFrame = new JFrame();

		guiFrame.setBounds(100, 100, 719, 510);
		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		guiFrame.setTitle("Welcome to BlackBox");
		guiFrame.setSize(650, 500);

		// This will center the JFrame in the middle of the screen
		guiFrame.setLocationRelativeTo(null);
		guiFrame.getContentPane().setLayout(new BorderLayout());

		// creating a border to highlight the JPanel areas
		Border outline = BorderFactory.createLineBorder(Color.black);

		welcomeCard = new JPanel();
		welcomeCard.setBackground(new Color(255, 255, 204));

		firstCard = new JPanel();
		firstCard.setBackground(new Color(255, 255, 204));

		LogSheetCard = new JPanel();
		LogSheetCard.setBackground(new Color(248, 248, 255));
		LogSheetCard.setLayout(null);

		// Panel for showing rating
		JPanel logpanel = new JPanel();
		logpanel.setVisible(false);
		logpanel.setBackground(Color.white);
		logpanel.setBounds(10, 250, 614, 164);
		LogSheetCard.add(logpanel);
		logpanel.setLayout(null);

		// Combobox - view records by username
		JComboBox<String> cbSelectbyName = new JComboBox<String>();
		String queryName = "select distinct username from logsheet";
		String colName = "username";
		this.fillComboBox(cbSelectbyName, queryName, colName);

		// ComboBox - view records by report type
		JComboBox<String> cbSelectedbyStatus = new JComboBox<String>();
		String queryStatus = "select distinct reportStatus from logsheet";
		String colStatus = "reportStatus";
		this.fillComboBox(cbSelectedbyStatus, queryStatus, colStatus);

		// ComboBox - view diagnostic history
		JComboBox<String> cbSelectDiag = new JComboBox<String>();
		String queryDiag = "select distinct date from diagsheet";
		String colDiag = "date";
		this.fillComboBox(cbSelectDiag, queryDiag, colDiag);

		// Add a button to export JTable to Excel
		JButton exportBtn = new JButton("Export To File");
		exportBtn.setBounds(20, 305, 180, 20);
		exportBtn.setSize(new Dimension(140, 25));
		LogSheetCard.add(exportBtn);
		exportBtn.setVisible(false);
		exportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String statusItem = (String) cbSelectedbyStatus.getSelectedItem();
				String fileName = "StatusReport_" + statusItem + ".xls";
				if (toExcel(logTable, new File(fileName))) {
					JOptionPane.showMessageDialog(null, "Data Exported to " + fileName + "!");
				} else {
					JOptionPane.showMessageDialog(null, "Failed to export logs to " + fileName + "!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Enable the combobox to select the records by names from the database
		cbSelectbyName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select username as 'User', reportStatus as 'Report Status' from logsheet where username =?";
				String item = (String) cbSelectbyName.getSelectedItem();
				String displaytext = "The Records for " + item + " are: ";

				if (cbSelectbyName.getSelectedIndex() != 0) {
					selectComboBox(query, item, displaytext);
					cbSelectedbyStatus.setSelectedIndex(0);
					cbSelectDiag.setSelectedIndex(0);
					logpanel.setVisible(true);
					passpanel.setVisible(false);
					exportBtn.setVisible(false);
				}
			}
		});
		cbSelectbyName.setBounds(20, 11, 150, 20);
		LogSheetCard.add(cbSelectbyName);

		// Enable the combobox to select by report status ("pass" or "warning")
		// from the database
		cbSelectedbyStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select username as 'User', reportStatus as 'Record', carSpeed as 'Speed', carRPM as 'RPM', carTemp as 'Temp', carOil as 'Oil level', carFuel as 'Gas', carTFL as 'Tire FL', carTFR as 'Tier FR', carTRL as 'Tire RL', carTRR as 'Tire RR' from logsheet where reportStatus =?";
				String item = (String) cbSelectedbyStatus.getSelectedItem();
				String displaytext = "The " + item + " Records are: ";

				if (cbSelectedbyStatus.getSelectedIndex() != 0) {
					selectComboBox(query, item, displaytext);
					cbSelectbyName.setSelectedIndex(0);
					cbSelectDiag.setSelectedIndex(0);
					logpanel.setVisible(false);
					passpanel.setVisible(true);
					exportBtn.setVisible(true);
				}
			}
		});
		cbSelectedbyStatus.setBounds(175, 11, 180, 20);
		LogSheetCard.add(cbSelectedbyStatus);

		// Enable the combobox to select historic diagnostic reports by date
		// from the database
		cbSelectDiag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select rowid as 'Record ID', date as 'Diagnostic Date' from diagsheet where date =?";
				String item = (String) cbSelectDiag.getSelectedItem();
				String displaytext = "The Reports dated " + item + " are: " + "                  Click to view Report.";

				if (cbSelectDiag.getSelectedIndex() != 0) {
					selectComboBox(query, item, displaytext);
					cbSelectedbyStatus.setSelectedIndex(0);
					cbSelectbyName.setSelectedIndex(0);
					logpanel.setVisible(false);
					passpanel.setVisible(false);
					exportBtn.setVisible(false);
				}
			}
		});
		cbSelectDiag.setBounds(365, 11, 180, 20);
		LogSheetCard.add(cbSelectDiag);

		// Panel for "Diagnostic"
		diagCard = new JPanel();
		diagCard.setBackground(Color.WHITE);
		DrawGraphics dg = new DrawGraphics(car);
		diagCard.add(dg, "Graphics");
		dg.setLayout(null);

		JButton btnDiag = new JButton("Diagnose Fully");
		dg.setBtnDiag(btnDiag);
		btnDiag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// digCar = new CarFacade(car);
				carDig = digCar.getCar();
				// obdDig = new OBD2Port(digCar);

				FullDiagCard fdc = new FullDiagCard(carDig, obdDig);
				fdc.save.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						fdc.savetoDB();
						JOptionPane.showMessageDialog(null, "Data Saved.");
						updateComboBox(cbSelectDiag, queryDiag, colDiag);
					}
				});
				cardPanel.add(fdc, "Full Diagnose");

				TransitionCard tsc = new TransitionCard();
				cardPanel.add(tsc, "Trans");

				Thread.State digCarState = digCar.getState();
				if (digCarState == Thread.State.NEW) {
					digCar.setCarStopped(false);
					digCar.start();
				}
				Thread thread = new Thread() {

					public void run() {
						try {
							cards.show(cardPanel, "Trans");
							digCar.setCarStopped(false);
							Thread.sleep(5000);
							fdc.diagnoseFully();
							digCar.setCarStopped(true);
							cards.show(cardPanel, "Full Diagnose");
						} catch (InterruptedException ex) {
					}
				}
			};

				thread.start();
			}
		});
		dg.add(btnDiag);
		btnDiag.setBounds(497, 0, 123, 23);

		rtmCard = new JPanel();
		rtmCard.setBackground(new Color(230, 230, 250));

		lblShowText = new JLabel();
		lblShowText.setBounds(20, 30, 500, 20);
		LogSheetCard.add(lblShowText);

		logTable = new JTable() {
			private static final long serialVersionUID = 1L;

			// Override the isCellEditable function to always return false
			public boolean isCellEditable(int row, int column) {
				return false; // Make an non-editable table
			};
		};

		JScrollPane scrollPane = new JScrollPane(logTable);
		scrollPane.setLocation(10, 50);
		scrollPane.setSize(614, 200);
		logTable.setFillsViewportHeight(false);
		scrollPane.setViewportView(logTable);

		logTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				try {
					int row = logTable.getSelectedRow();

					String tableclick1 = (logTable.getModel().getValueAt(row, 0).toString());
					String query1 = "select rowid,* from logsheet where username = '" + tableclick1 + "'";

					String tableclick2 = (logTable.getModel().getValueAt(row, 0).toString());
					String query2 = "select rowid,* from diagsheet where rowid = '" + tableclick2 + "'";

					PreparedStatement pst1 = connection1.prepareStatement(query1);
					ResultSet rs1 = pst1.executeQuery();

					PreparedStatement pst2 = connection1.prepareStatement(query2);
					ResultSet rs2 = pst2.executeQuery();

					if (rs1.next()) {

						name = rs1.getString("username");
					}

					if (rs2.next()) {

						FullDiagCard fdc = new FullDiagCard(car, obd);
						cardPanel.add(fdc, "Full Diagnose Report");
						cards.show(cardPanel, "Full Diagnose Report");

						fdc.lbldate.setText(rs2.getString("date"));
						fdc.rpmAvg.setText(rs2.getString("carRPM"));
						fdc.olAvg.setText(rs2.getString("carOil"));
						fdc.flAvg.setText(rs2.getString("carFuel"));
						fdc.tempAvg.setText(rs2.getString("carTemp"));
						fdc.tireFLAvg.setText(rs2.getString("carTFL"));
						fdc.tireFRAvg.setText(rs2.getString("carTFR"));
						fdc.tireRLAvg.setText(rs2.getString("carTRL"));
						fdc.tireRRAvg.setText(rs2.getString("carTRR"));

						fdc.rpmSV.setText(rs2.getString("SVrpm"));
						fdc.olSV.setText(rs2.getString("SVoil"));
						fdc.flSV.setText(rs2.getString("SVfuel"));
						fdc.tempSV.setText(rs2.getString("SVtemp"));
						fdc.tireSV.setText(rs2.getString("SVtire"));

						fdc.rpmStatus.setText(rs2.getString("RPMStatus"));
						fdc.olStatus.setText(rs2.getString("OilStatus"));
						fdc.flStatus.setText(rs2.getString("FuelStatus"));
						fdc.tempStatus.setText(rs2.getString("TempStatus"));
						fdc.tireFLStatus.setText(rs2.getString("TFLStatus"));
						fdc.tireFRStatus.setText(rs2.getString("TFRStatus"));
						fdc.tireRLStatus.setText(rs2.getString("TRLStatus"));
						fdc.tireRRStatus.setText(rs2.getString("TRRStatus"));

						fdc.save.setVisible(false);
						fdc.saveToPDF.setVisible(true);
					}

					rs1.close();
					rs2.close();
					pst1.close();
					pst2.close();
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Nothing is selected.");
				}
			}

		});
		logTable.setForeground(new Color(0, 0, 0));
		logTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		logTable.setShowVerticalLines(false);
		logTable.setShowHorizontalLines(false);
		logTable.setShowGrid(true);
		logTable.setRowSelectionAllowed(true);
		logTable.setBackground(new Color(255, 250, 205));
		logTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		LogSheetCard.add(scrollPane);

		JLabel lblTotal = new JLabel("Total Records");
		lblTotal.setBounds(10, 11, 85, 22);
		logpanel.add(lblTotal);

		total = new JLabel("");
		total.setBounds(114, 11, 85, 22);
		logpanel.add(total);

		JLabel lblPass = new JLabel("Pass Records");
		lblPass.setBounds(10, 35, 85, 22);
		logpanel.add(lblPass);

		pass = new JLabel("");
		pass.setBounds(114, 35, 85, 22);
		logpanel.add(pass);

		rate = new JLabel("%");
		rate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		rate.setBounds(114, 68, 94, 58);
		logpanel.add(rate);

		JLabel lblRate = new JLabel("Pass Rate");
		lblRate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRate.setBounds(10, 68, 94, 58);
		logpanel.add(lblRate);

		reportStatuspanel = new JPanel() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				double prate = getPassRate();
				double warningrate = (1 - prate);

				double[] showitems = { prate, warningrate };
				String[] legends = { "Pass", "Warning" };
				Color[] colors = { new Color(124, 252, 0), new Color(255, 51, 51) };

				drawPieChart(g, showitems, colors);
				drawLegend(g, legends, colors);
			}

		};
		reportStatuspanel.setBounds(201, 11, 169, 142);
		reportStatuspanel.setBackground(Color.white);
		logpanel.add(reportStatuspanel);

		starpanel = new JPanel() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				double Pass = getPassRate();

				java.awt.Image star = new ImageIcon("image/star.png").getImage();

				Font font = new Font("SansSerif", Font.BOLD, 15);
				g.setFont(font);
				g.setColor(Color.darkGray);

				if (Pass > 0) {
					g.drawString("Performance: ", 0, 20);
					g.drawImage(star, 0, 40, starpanel);
				}

				if (Pass >= 0.25)
					g.drawImage(star, 50, 40, starpanel);
				if (Pass >= 0.5)
					g.drawImage(star, 100, 40, starpanel);
				if (Pass >= 0.75)
					g.drawImage(star, 150, 40, starpanel);

			}

		};
		starpanel.setBounds(398, 11, 206, 142);
		starpanel.setBackground(Color.white);
		logpanel.add(starpanel);

		passpanel = new JPanel();
		passpanel.setBounds(10, 250, 614, 164);
		passpanel.setVisible(false);
		passpanel.setBackground(Color.white);
		passpanel.setLayout(null);
		LogSheetCard.add(passpanel);

		JLabel lblTotal_1 = new JLabel("Total Records");
		lblTotal_1.setBounds(10, 11, 85, 22);
		passpanel.add(lblTotal_1);

		total_1 = new JLabel("");
		total_1.setBounds(114, 11, 85, 22);
		passpanel.add(total_1);

		piechart = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				double[] showitems = getPercentage(cbSelectedbyStatus.getSelectedItem().toString());
				String[] name = getNameList();
				Color[] colors = new Color[name.length];

				System.out.println(name.length);

				for (int i = 0; i < name.length; i++) {
					int red = (int) (Math.random() * 256);
					int green = (int) (Math.random() * 256);
					int blue = (int) (Math.random() * 256);

					colors[i] = new Color(red, green, blue);
				}

				drawPieChart(g, showitems, colors);
				drawLegend(g, name, colors);
			}
		};
		piechart.setBounds(201, 11, 169, 142);
		piechart.setBackground(Color.white);
		passpanel.add(piechart);

		// Create Panels for switching
		cards = new CardLayout();
		cardPanel = new JPanel();
		// set the card layout
		cardPanel.setLayout(cards);
		cards.show(cardPanel, "Welcome");
		cardPanel.add(welcomeCard, "Welcome");
		welcomeCard.setLayout(null);

		lblWelcome = new JLabel("Welcome to BlackBox\u2122 ");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(48, 60, 530, 57);
		lblWelcome.setFont(new Font("AngsanaUPC", Font.BOLD | Font.ITALIC, 53));
		welcomeCard.add(lblWelcome);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(219, 166, 87, 14);
		welcomeCard.add(lblUsername);

		JLabel lblPW = new JLabel("Password");
		lblPW.setBounds(219, 197, 75, 14);
		welcomeCard.add(lblPW);

		tfUsername = new JTextField();
		tfUsername.setBounds(304, 163, 129, 20);
		welcomeCard.add(tfUsername);
		tfUsername.setColumns(10);

		pfPW = new JPasswordField();
		pfPW.setEchoChar('*');
		pfPW.setBounds(304, 194, 129, 20);
		welcomeCard.add(pfPW);

		cardPanel.add(welcomeCard, "Welcome");

		cardPanel.add(rtmCard, "Real-Time Monitor");
		rtmCard.setLayout(new BorderLayout(0, 0));

		cardPanel.add(diagCard, "Diagnose");
		cardPanel.add(LogSheetCard, "Log Sheets");
		cardPanel.add(firstCard, "First Page");
		firstCard.setLayout(new BorderLayout());
		firstCard.setBackground(new Color(240, 240, 240));

		// This panel will contain one button to enable you
		// to switch thro' the cards
		buttonPanel = new JPanel();
		buttonPanel.setBorder(outline);
		buttonPanel.setVisible(false); // Not visible before successful login

		// Add switch buttons for user to choose
		addSwitch("Real-Time Monitor");
		addSwitch("Diagnose");
		addSwitch("Log Sheets");

		// Car starts upon clicking the button
		// It will automatically switch to Real-Time Monitor
		JButton startEngine = new JButton("");
		startEngine.setSize(200, 200);
		ImageIcon image = new ImageIcon("image/EngineStart.png");
		java.awt.Image im = image.getImage();
		java.awt.Image scaledImage = im.getScaledInstance(startEngine.getWidth(), startEngine.getHeight(),
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImage);
		startEngine.setIcon(icon);
		startEngine.setBackground(new Color(240, 240, 240));
		firstCard.add(startEngine, BorderLayout.CENTER);

		thisCar.start();// Start a thread for CarFacade
		rtmCard.add(realTimeMonitor);
		startEngine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				thisCar.startCar();
				cards.show(cardPanel, "Real-Time Monitor");
				s.setStarting(true);

				realTimeMonitor.startRun();
			}

		});

		// Add exit button to close the application
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnExit.setActionCommand("close");
		buttonPanel.add(btnExit);

		// Add logout button for another user
		JButton btnLO = new JButton("Logout");
		btnLO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure to logout?", "Logout",
						JOptionPane.YES_NO_OPTION);

				if (confirmation == 0) {
					cards.show(cardPanel, "Welcome");
					buttonPanel.setVisible(false);
					lblUser.setText("");
					lblFirst.setText("");
					lblWelcome.setText("Login As Another User");

					thisCar.stopCar(); // stop the current thread;
					// thisCar.stop();

					// thisCar = new CarFacade(car);

					thisCar.setCarStopped(true);
					thisCar.resetMap();
				}
			}
		});
		btnLO.setActionCommand("Logout");
		buttonPanel.add(btnLO);

		guiFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);
		guiFrame.getContentPane().add(cardPanel, BorderLayout.CENTER);
		guiFrame.setVisible(true);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String user = tfUsername.getText().trim();
					String pw = pfPW.getText().trim();

					String query = "select username,password,drivestyle from loginInfo where username ='" + user
							+ "' and password = '" + pw + "'";
					PreparedStatement pst = connection1.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					// Username & password are unique in group
					if (rs.next()) {

						name = rs.getString("username");
						JOptionPane.showMessageDialog(null, "Login Successfully");

						driveStyle = rs.getString("drivestyle");

						profile.setStyle(driveStyle);
						profile.setUsername(name);

						// clear the fields for reentering
						clearUserInfo();

						// After login successfully, user can see the button
						// panel
						buttonPanel.setVisible(true);
						lblUser = new JLabel(name);
						buttonPanel.add(lblUser);
						cards.show(cardPanel, "First Page");

						lblFirst = new JLabel("");
						lblFirst.setText("Welcome, " + name + "!");
						lblFirst.setHorizontalAlignment(SwingConstants.CENTER);
						lblFirst.setBounds(48, 60, 530, 57);
						lblFirst.setFont(new Font("AngsanaUPC", Font.BOLD | Font.ITALIC, 53));
						firstCard.add(lblFirst, BorderLayout.NORTH);

					} else {
						JOptionPane.showMessageDialog(null,
								"Username and password are not matached. Please login again");
						// clear the fields for reentering
						clearUserInfo();
					}

					rs.close();
					pst.close();

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnLogin.setBounds(261, 237, 98, 23);
		welcomeCard.add(btnLogin);

		// Button to exit
		JButton button = new JButton("Exit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		button.setActionCommand("close");
		button.setBounds(369, 237, 64, 23);
		welcomeCard.add(button);

	}

	private void addSwitch(String command) {

		JButton btn = new JButton(command);
		btn.setActionCommand(command);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// navigate to the next component
				cards.show(cardPanel, command);
			}
		});

		buttonPanel.add(btn);
		guiFrame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

	}

	private void clearUserInfo() {
		tfUsername.setText("");
		pfPW.setText("");
	}

	public void fillComboBox(JComboBox<String> cb, String query, String col) {
		cb.removeAllItems(); // Clear existing comboBox
		if (col.equals("date")) {
			cb.setModel(new DefaultComboBoxModel<String>(new String[] { "Select Diagnostic History" }));

		} else {
			cb.setModel(new DefaultComboBoxModel<String>(new String[] { "View by " + col }));
		}

		ArrayList<String> items = new ArrayList<String>();

		try {
			// Send query to database
			PreparedStatement pst = connection1.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String currentRow = rs.getString(col);
				items.add(currentRow);

			}

			// Sort the items before adding into combobox
			for (int j = 0; j < items.size() + 1; j++) {
				for (int k = j + 1; k < items.size(); k++) {
					if (items.get(k).compareTo(items.get(j)) < 0) {
						String temp = items.get(j);
						items.set(j, items.get(k));
						items.set(k, temp);
					}
				}
			}

			// Add the sorted items to combobox
			for (int j = 0; j < items.size(); j++) {
				String show = items.get(j);
				System.out.println(show);
				cb.addItem(show);

			}
			rs.close();
			pst.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectComboBox(String query, String selectedItem, String text) {
		try {
			PreparedStatement pst = connection1.prepareStatement(query);
			PreparedStatement pst1 = connection1.prepareStatement(query);
			pst.setString(1, selectedItem);
			pst1.setString(1, selectedItem);
			ResultSet rs = pst.executeQuery();
			ResultSet rs1 = pst1.executeQuery();

			double counttotal = 0;
			double countpass = 0;
			if (selectedItem != null) {
				lblShowText.setText(text);
				logTable.setModel(DbUtils.resultSetToTableModel(rs));

				while (rs1.next()) {
					counttotal++;
					if (rs1.getString(2).equals("Pass")) {
						countpass++;
					}
				}

			}

			DecimalFormat formatter = new DecimalFormat("#0.0");
			total.setText(Double.toString(counttotal));
			total_1.setText(Double.toString(counttotal));
			pass.setText(Double.toString(countpass));

			double ratepass = countpass / counttotal;
			NumberFormat percentFormat = NumberFormat.getPercentInstance();
			percentFormat.setMaximumFractionDigits(1);

			rate.setText(percentFormat.format(ratepass));
			setPassRate(ratepass);

			System.out.println(ratepass);
			starpanel.repaint();

			// System.out.println(counttotal);
			// System.out.println(countpass);

			setPersonPass(countpass);
			setPersonTotal(counttotal);
			reportStatuspanel.repaint();

			piechart.repaint();

			rs.close();
			rs1.close();
			pst.close();
			pst1.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
	}

	public void clearTable() {
		// Clear the table
		DefaultTableModel model = (DefaultTableModel) logTable.getModel();

		while (model.getRowCount() > 0) {
			for (int i = 0; i < model.getRowCount(); i++)
				model.removeRow(i);
		}

	}

	// Update comboBoxes to reflect the latest change
	public void updateComboBox(JComboBox cb, String query, String col) {

		this.fillComboBox(cb, query, col);
		lblShowText.setText("");
		// clearTable();
	}

	public java.awt.Image getImageFromPanel(Component component) {

		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());
		return image;
	}

	public void printToPDF(java.awt.Image awtImage, String fileName) {
		try {
			Document d = new Document();
			PdfWriter writer = PdfWriter.getInstance(d, new FileOutputStream(fileName));
			d.open();

			Image iTextImage = Image.getInstance(writer, awtImage, 1);
			// iTextImage.setAbsolutePosition(50, 50);
			iTextImage.scalePercent(80);
			d.add(iTextImage);

			d.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean toExcel(JTable table, File file) {
		try {
			DefaultTableModel model = (DefaultTableModel) logTable.getModel();
			FileWriter excel = new FileWriter(file);

			for (int i = 0; i < model.getColumnCount(); i++) {
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i, j).toString() + "\t");
				}
				excel.write("\n");
			}

			excel.close();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}

	// Get total "pass" records or total "warning" records
	public int getTotalPW(String table, String pORw) {
		int count = 0;
		try {

			PreparedStatement pst = connection1.prepareStatement("SELECT * FROM " + table);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				if (rs.getString(2).equals(pORw)) {
					count++;
				}
			}
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return count;
	}

	// Draw pie chart
	private void drawPieChart(Graphics g, double[] showitems, Color[] colors) {

		int startAngle = 0;
		int arcAngle = 0;

		for (int i = 0; i < showitems.length; i++) {

			// calculate arc angle for percentage
			arcAngle = (int) Math.round(showitems[i] * 360);
			// System.out.println(showitems[i]);

			// set drawing Color for pie wedge
			g.setColor(colors[i]);

			// draw pie wedge
			g.fillArc(5, 5, 100, 100, startAngle, arcAngle);

			// calculate startAngle for next pie wedge
			startAngle += arcAngle;
		}
	} // end method drawPieChart

	// Draw pie chart legend on given Graphics context
	private void drawLegend(Graphics g, String[] legends, Color[] colors) {

		// create Font for Account name
		Font font = new Font("SansSerif", Font.BOLD, 12);
		g.setFont(font);

		// draw description for each Account
		for (int i = 0; i < legends.length; i++) {
			g.setColor(colors[i]);
			g.fillRect(100, 100 + 15 * i, 10, 10);

			// draw Account name next to color swatch
			g.setColor(Color.black);
			g.drawString(legends[i], 115, 110 + 15 * i);
		}
	} // end method drawLegend

}
