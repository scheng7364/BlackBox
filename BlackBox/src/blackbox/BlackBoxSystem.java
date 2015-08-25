package blackbox;

import blackbox.CarClasses.Car;
import blackbox.CarClasses.Honda;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class BlackBoxSystem {

	Car car = new Honda();
	CarFacade thisCar = new CarFacade(car);

	// OBD2Port obd = new OBD2Port(thisCar);
	OBD2Port obd = thisCar.getObdPort();
	DriverProfile profile = new DriverProfile();
	Sensors s = new Sensors(obd, car, profile);
	RealTimeMonitor realTimeMonitor = new RealTimeMonitor(thisCar, s);

	CarFacade digCar = new CarFacade(car);
	Car carDig = digCar.getCar();
	OBD2Port obdDig = digCar.getObdPort();
	// OBD2Port obdDig = new OBD2Port(digCar);

	private JFrame guiFrame;
	private CardLayout cards;
	private JPanel buttonPanel;
	private JPanel cardPanel;
	private JPanel DiagCard;
	private JTextField tfUsername;
	private JPasswordField pfPW;
	private JLabel lblUser; // show the user name on buttonPanel after login
	private JLabel lblFirst; // show welcome user after login
	private JLabel lblWelcome;
	private String name; // username to be displayed
	private String driveStyle; // driving styles for different users
	private JLabel lblShowText; // in Log Sheet card;
	private JPanel RtmCard; // Card for real-time monitoring
	private JPanel firstCard; // Card for start-engine button

	private JTextField textField;
	private JTable logTable;

	Connection connection1 = sqliteConnection.dbConnector();

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

		// Combobox - view records by username
		JComboBox<String> cbSelectbyName = new JComboBox<String>();
		String queryName = "select distinct username from logsheet";
		String colName = "username";
		this.fillComboBox(cbSelectbyName, queryName, colName);

		cbSelectbyName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select username as 'User', reportStatus as 'Report Status' from logsheet where username =?";
				String item = (String) cbSelectbyName.getSelectedItem();
				String displaytext = "The Records for " + item + " are: ";

				selectComboBox(query, item, displaytext);
				// Clear the window after selecting a new Category
				// clearPane();
			}
		});
		cbSelectbyName.setBounds(20, 11, 150, 20);
		LogSheetCard.add(cbSelectbyName);

		// ComboBox - view records by report type
		JComboBox<String> cbSelectedbyStatus = new JComboBox<String>();
		String queryStatus = "select distinct reportStatus from logsheet";
		String colStatus = "reportStatus";
		this.fillComboBox(cbSelectedbyStatus, queryStatus, colStatus);

		cbSelectedbyStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select * from logsheet where reportStatus =?";
				String item = (String) cbSelectedbyStatus.getSelectedItem();
				String displaytext = "The " + item + " are: ";

				selectComboBox(query, item, displaytext);
				// Clear the window after selecting a new Category
				// clearPane();
			}
		});
		cbSelectedbyStatus.setBounds(175, 11, 180, 20);
		LogSheetCard.add(cbSelectedbyStatus);

		// ComboBox - view diagnostic history
		JComboBox<String> cbSelectDiag = new JComboBox<String>();
		String queryDiag = "select distinct date from diagsheet";
		String colDiag = "date";
		String diagtable = "diagsheet";
		this.fillComboBox(cbSelectDiag, queryDiag, colDiag);

		cbSelectDiag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "select rowid as 'Record ID', date as 'Diagnostic Date' from diagsheet where date =?";
				String item = (String) cbSelectDiag.getSelectedItem();
				String displaytext = "The Reports dated " + item + " are: ";

				selectComboBox(query, item, displaytext);
				// Clear the window after selecting a new Category
				// clearPane();
			}
		});
		cbSelectDiag.setBounds(365, 11, 180, 20);
		LogSheetCard.add(cbSelectDiag);

		DiagCard = new JPanel();
		DiagCard.setBackground(Color.WHITE);
		DrawGraphics dg = new DrawGraphics();
		DiagCard.add(dg, "Graphics");
		dg.setLayout(null);

		JButton btnDiag = new JButton("Diagnose Fully");
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
							// digCar.start();
							Thread.sleep(5000);
							fdc.diagnoseFully();
							// digCar.stop();
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

		RtmCard = new JPanel();
		RtmCard.setBackground(new Color(230, 230, 250));

		/*JButton btnGo = new JButton("Go");
		// dg.add(btnGo);
		btnGo.setBounds(557, 65, 55, 20);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println(dg.getComboSelected());
				tc = new TiresCard(carDig, obdDig);
				cardPanel.add(tc, "Tires");

				JButton tireTest = new JButton("Check");
				tireTest.setBounds(380, 10, 100, 24);
				tireTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// digCar = new CarFacade() ;
						carDig = digCar.getCar();
						obdDig = digCar.getObdPort();

						tc = new TiresCard(carDig, obdDig);
						cardPanel.add(tc, "Tires");

						TransitionCard tsc = new TransitionCard();
						cardPanel.add(tsc, "Trans");

						Thread thread = new Thread() {

							public void run() {
								try {
									digCar.setCarStopped(false);
									digCar.start();
									cards.show(cardPanel, "Trans");
									Thread.sleep(2000);
									tc.diagnoseTires();
									digCar.stop();
									cards.show(cardPanel, "Tires");

								} catch (InterruptedException ex) {
								}
							}
						};
						thread.start();
					}
				});
				tc.add(tireTest);

				ec = new EngineCard(carDig, obdDig);
				cardPanel.add(ec, "Engine");

				JButton engTest = new JButton("Check");
				engTest.setBounds(380, 10, 100, 24);
				engTest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						digCar = new CarFacade(car);
						carDig = digCar.getCar();
						obdDig = new OBD2Port(digCar);

						ec = new EngineCard(carDig, obdDig);
						cardPanel.add(ec, "Engine");

						TransitionCard tsc = new TransitionCard();
						cardPanel.add(tsc, "Trans");

						Thread thread = new Thread() {

							public void run() {
								try {
									digCar.setCarStopped(false);
									digCar.start();
									cards.show(cardPanel, "Trans");
									Thread.sleep(3000);
									ec.diagnoseEngine();
									digCar.stop();
									cards.show(cardPanel, "Engine");
								} catch (InterruptedException ex) {
								}
							}
						};

						thread.start();
					}
				});
				ec.add(engTest);

				cards.show(cardPanel, dg.getComboSelected());

			}
		});

	*/

		lblShowText = new JLabel();
		lblShowText.setBounds(20, 30, 200, 20);
		LogSheetCard.add(lblShowText);

		JLabel lblTireFR = new JLabel("New label");
		lblTireFR.setBounds(20, 300, 40, 20);
		LogSheetCard.add(lblTireFR);
		/*
		 * JLabel lblTireFL = new JLabel("New label");
		 * 
		 * JLabel lblTireRL = new JLabel("New label");
		 * 
		 * JLabel lblTireRR = new JLabel("New label");
		 * 
		 * JLabel lblOil = new JLabel("New label");
		 * 
		 * JLabel lblRPM = new JLabel("New label");
		 * 
		 * JLabel lblFuel = new JLabel("New label");
		 * 
		 * JLabel lblTemp = new JLabel("New label");
		 */

		logTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(logTable);
		scrollPane.setLocation(20, 50);
		scrollPane.setSize(590, 200);
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

						// Send the GUI components to their corresponding
						// columns in database
						/*
						 * lblTireFL.setText(rs.getString("carTFL"));
						 * lblTireFR.setText(rs.getString("carTFR"));
						 * lblTireRL.setText(rs.getString("carTRL"));
						 * lblTireRR.setText(rs.getString("carTRR"));
						 * lblTemp.setText(rs.getString("carTemp"));
						 * lblOil.setText(rs.getString("carOil"));
						 * lblFuel.setText(rs.getString("carFuel"));
						 * lblRPM.setText(rs.getString("carRPM"));
						 */
					}

					if (rs2.next()) {
						FullDiagCard fdc = new FullDiagCard(car, obd);
						cardPanel.add(fdc, "Full Diagnose Report");
						cards.show(cardPanel, "Full Diagnose Report");

						fdc.lbldate.setText(rs2.getString("date"));
						fdc.rpmAvg.setText(rs2.getString("carRPM"));
						fdc.olAvg.setText(rs2.getString("carOil"));
						fdc.flAvg.setText(rs2.getString("carFuel"));
						fdc.TempAvg.setText(rs2.getString("carTemp"));
						fdc.TireFLAvg.setText(rs2.getString("carTFL"));
						fdc.TireFRAvg.setText(rs2.getString("carTFR"));
						fdc.TireRLAvg.setText(rs2.getString("carTRL"));
						fdc.TireRRAvg.setText(rs2.getString("carTRR"));

						fdc.rpmSV.setText(rs2.getString("SVrpm"));
						fdc.olSV.setText(rs2.getString("SVoil"));
						fdc.flSV.setText(rs2.getString("SVfuel"));
						fdc.TempSV.setText(rs2.getString("SVtemp"));
						fdc.TireSV.setText(rs2.getString("SVtire"));

						fdc.rpmStatus.setText(rs2.getString("RPMStatus"));
						fdc.olStatus.setText(rs2.getString("OilStatus"));
						fdc.flStatus.setText(rs2.getString("FuelStatus"));
						fdc.TempStatus.setText(rs2.getString("TempStatus"));
						fdc.TireFLStatus.setText(rs2.getString("TFLStatus"));
						fdc.TireFRStatus.setText(rs2.getString("TFRStatus"));
						fdc.TireRLStatus.setText(rs2.getString("TRLStatus"));
						fdc.TireRRStatus.setText(rs2.getString("TRRStatus"));

						fdc.save.setVisible(false);

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
		logTable.setForeground(new Color(0, 0, 128));
		logTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		logTable.setShowVerticalLines(false);
		logTable.setShowHorizontalLines(false);
		logTable.setShowGrid(false);
		logTable.setRowSelectionAllowed(true);
		logTable.setBackground(new Color(255, 250, 205));
		logTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		LogSheetCard.add(scrollPane);

		JPanel logPanel = new JPanel();
		logPanel.setBounds(21, 195, 590, 220);
		LogSheetCard.add(logPanel);

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

		cardPanel.add(RtmCard, "Real-Time Monitor");
		RtmCard.setLayout(new BorderLayout(0, 0));

		cardPanel.add(DiagCard, "Diagnose");
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
		Image im = image.getImage();
		Image scaledImage = im.getScaledInstance(startEngine.getWidth(), startEngine.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(scaledImage);
		startEngine.setIcon(icon);
		startEngine.setBackground(new Color(240, 240, 240));
		firstCard.add(startEngine, BorderLayout.CENTER);

		thisCar.start();// Start a thread for CarFacade
		RtmCard.add(realTimeMonitor);
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
			cb.setModel(new DefaultComboBoxModel<String>(new String[] { "View Diagnostic History" }));
			
		} else {
			cb.setModel(new DefaultComboBoxModel<String>(new String[] { "View by " + col }));
		}
		
		ArrayList<String> items = new ArrayList<String>();
		
		try {
			// Send query to database
			PreparedStatement pst = connection1.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			int i = 0;

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
				String day = items.get(j);
				System.out.println(day);
				cb.addItem(day);

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

			int count = 0;
			int count1 = 0;
			if (selectedItem != null) {
				lblShowText.setText(text);
				logTable.setModel(DbUtils.resultSetToTableModel(rs));
				
				while(rs1.next()) {
					count++;
					if (rs1.getString(2).equals("Pass")) {
						count1++;
					}
				}
				
			}
			System.out.println(count);
			System.out.println(count1);
			
			rs.close();
			rs1.close();
			pst.close();
			pst1.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void clearTable(){
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
		//clearTable();		
	}
/*	public int getRows(String table) {
		int count = 0;
		try {

			PreparedStatement pst = connection1.prepareStatement("SELECT COUNT(*) AS rowcount FROM " + table);
			ResultSet rs = pst.executeQuery();
			rs.next();
			count = rs.getInt("rowcount");
			rs.close();

			System.out.println(table + " has " + count + " row(s).");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}*/
}