package blackbox;
import  blackbox.CarClasses.Car;
import blackbox.CarClasses.Honda;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.Border;

public class BlackBoxSystem {

	Car car = new Honda();
	CarFacade thisCar = new CarFacade(car);
	
	//OBD2Port obd = new OBD2Port(thisCar);
	OBD2Port obd = thisCar.getObdPort();
	DriverProfile profile = new DriverProfile();
	Sensors s = new Sensors(obd, car, profile);
	RealTimeMonitor realTimeMonitor = new RealTimeMonitor(thisCar, s);
	
	CarFacade digCar = new CarFacade(car) ;
	Car carDig = digCar.getCar();
	OBD2Port obdDig = digCar.getObdPort();
	//OBD2Port obdDig = new OBD2Port(digCar);
	
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

	private JPanel RtmCard; // Card for real-time monitoring
	private JPanel firstCard; // Card for start-engine button

	Connection connection1 = sqliteConnection.dbConnector();
	private JTextField textField;

	TiresCard tc;
	EngineCard ec;

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

		DiagCard = new JPanel();
		DiagCard.setBackground(Color.WHITE);
		DrawGraphics dg = new DrawGraphics();
		DiagCard.add(dg, "Graphics");
		dg.setLayout(null);

		JButton btnDiag = new JButton("Diagnose Fully");
		btnDiag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//digCar = new CarFacade(car);
				carDig = digCar.getCar();
				//obdDig = new OBD2Port(digCar);
				
				FullDiagCard fdc = new FullDiagCard(carDig, obdDig);
				cardPanel.add(fdc, "Full Diagnose");

				TransitionCard tsc = new TransitionCard();
				cardPanel.add(tsc, "Trans");

				Thread thread = new Thread() {

					public void run() {
						try {
							cards.show(cardPanel, "Trans");
							digCar.setCarStopped(false);
							digCar.start();
							Thread.sleep(6000);
							fdc.diagnoseFully();
							digCar.stop();
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

		JButton btnGo = new JButton("Go");
		//dg.add(btnGo);
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
						//digCar = new CarFacade() ;
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
				
						digCar = new CarFacade(car) ;
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

		LogSheetCard = new JPanel();
		LogSheetCard.setBackground(new Color(248, 248, 255));
		
		JComboBox cbSelectbyName = new JComboBox();
		cbSelectbyName.setModel(new DefaultComboBoxModel(new String[] {"View by Users"}));
		cbSelectbyName.setBounds(22, 11, 105, 20);
		LogSheetCard.add(cbSelectbyName);
		
		JComboBox cbSelectedbyStatus = new JComboBox();
		cbSelectedbyStatus.setModel(new DefaultComboBoxModel(new String[] {"View by Report Status"}));
		cbSelectedbyStatus.setBounds(152, 11, 137, 20);
		LogSheetCard.add(cbSelectedbyStatus);
		
		JTable logTable = new JTable();
		logTable.setBounds(21, 42, 590, 139);
		LogSheetCard.add(logTable);
		
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

		thisCar.start();//Start a thread for CarFacade
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
					//thisCar.stop();

					//thisCar = new CarFacade(car);

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
					//	System.out.println(driveStyle);
					//	System.out.println(profile.getStyle());
						
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
	

}