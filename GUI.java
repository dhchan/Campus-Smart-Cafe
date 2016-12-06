package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.core.PGraphics;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cardID_textField;
	private JPasswordField passwordField;
	private JPanel mapDisplayPane;
	private JMenuBar menuBar;
	private VendingMachineMarker mv1;
	private VendingMachineMarker mv2;
	private CafeMarker mc1;
	private CafeMarker mc2;
	private Location v1;
	private Cafe1GUI cafe1Frame;
	private Cafe2GUI cafe2Frame;
	private VM1GUI vm1Frame;
	private VM2GUI vm2Frame;
	private MyMap mymap;
	private boolean v1on = false;
	private boolean v2on = false;
	private boolean c1on = false;
	private boolean c2on = false;
	private JLabel lblCardId_1;
	protected static Card card;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 574);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		CardDatabase cdb = new CardDatabase();
		cdb.read("CardDatabase.txt");

		JPanel loginPane = new JPanel();
		loginPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		loginPane.setBounds(6, 6, 988, 540);
		contentPane.add(loginPane);
		loginPane.setLayout(null);

		JLabel lblWelcomeTo = new JLabel("Welcome to TechTonic University");
		lblWelcomeTo.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblWelcomeTo.setBounds(loginPane.getWidth() / 2 - 458 / 2, 104, 458, 61);
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		loginPane.add(lblWelcomeTo);

		JLabel lblSmartCafeSystem = new JLabel("Smart Cafe System");
		lblSmartCafeSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmartCafeSystem.setFont(new Font("Cambria Math", Font.BOLD, 30));
		lblSmartCafeSystem.setBounds(loginPane.getWidth() / 2 - 458 / 2, 164, 458, 61);
		loginPane.add(lblSmartCafeSystem);

		JLabel lblCardId = new JLabel("Card ID:");
		lblCardId.setBounds(loginPane.getWidth() / 2 - 100, 270, 70, 16);
		loginPane.add(lblCardId);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(394, 325, 70, 16);
		loginPane.add(lblPassword);

		cardID_textField = new JTextField();
		cardID_textField.setBounds(loginPane.getWidth() / 2 - 20, 265, 130, 26);
		loginPane.add(cardID_textField);
		cardID_textField.setColumns(10);
		cardID_textField.setText("");

		passwordField = new JPasswordField();
		passwordField.setBounds(loginPane.getWidth() / 2 - 20, 320, 130, 26);
		loginPane.add(passwordField);

		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pwd;

				if (cardID_textField.getText().trim() != "") {
					if (cardID_textField.getText().trim().matches("[0-9]+")
							&& cdb.getcardDatabase().containsKey(Integer.parseInt(cardID_textField.getText().trim()))) {
						pwd = cdb.getcardDatabase().get(Integer.parseInt(cardID_textField.getText().trim()));

						if (pwd.equals((new String(passwordField.getPassword())))) {
							lblCardId_1.setText("ID Card: " + cardID_textField.getText());
							contentPane.remove(loginPane);
							loginPane.setVisible(false);
							contentPane.add(mapDisplayPane);
							contentPane.add(menuBar);
							contentPane.repaint();
							card = new Card();
							card.read(cardID_textField.getText() + ".txt");

							cafe1Frame = new Cafe1GUI(card);
							cafe1Frame.setVisible(false);
							cafe2Frame = new Cafe2GUI(card);
							cafe2Frame.setVisible(false);
							vm1Frame = new VM1GUI(card);
							vm1Frame.setVisible(false);
							vm2Frame = new VM2GUI(card);
							vm2Frame.setVisible(false);
						} else {
							
							JOptionPane.showMessageDialog(null, "Invalid CardID or Password!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Invalid CardID or Password!");
					}

				}

			}
		});
		btnLogIn.setBounds(450, 380, 117, 29);
		loginPane.add(btnLogIn);
		contentPane.setLayout(null);

		mapDisplayPane = new JPanel();
		mapDisplayPane.setBounds(6, 36, 988, 492);
		mapDisplayPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.add(mapDisplayPane);
		mapDisplayPane.setLayout(null);

		JPanel mapPanel = new JPanel();
		mapPanel.setBounds(206, 6, 776, 480);
		mapDisplayPane.add(mapPanel);
		mymap = new MyMap();
		mapPanel.add(mymap);
		mapPanel.setLayout(null);

		JPanel mapControlPanel = new JPanel();
		mapControlPanel.setBounds(6, 6, 193, 480);
		mapDisplayPane.add(mapControlPanel);
		MapKey mapKey = new MapKey();
		mapControlPanel.add(mapKey);
		mapKey.setBounds(6, 100, 200, 300);
		mapControlPanel.setLayout(null);

		JLabel lblUniv = new JLabel("TechTonic University");
		lblUniv.setHorizontalAlignment(SwingConstants.CENTER);
		lblUniv.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblUniv.setBounds(6, 20, 163, 26);
		mapControlPanel.add(lblUniv);

		JLabel lblSmartCafeSystem_1 = new JLabel("Smart Cafe System");
		lblSmartCafeSystem_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmartCafeSystem_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSmartCafeSystem_1.setBounds(6, 45, 163, 26);
		mapControlPanel.add(lblSmartCafeSystem_1);

		lblCardId_1 = new JLabel("Card ID:");
		lblCardId_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCardId_1.setBounds(6, 72, 161, 16);
		mapControlPanel.add(lblCardId_1);

		menuBar = new JMenuBar();
		menuBar.setBounds(12, 6, 980, 22);
		contentPane.add(menuBar);

		JMenu mnFoodStore = new JMenu("Food Store");
		menuBar.add(mnFoodStore);

		JMenu mnCafe = new JMenu("Cafe");
		mnFoodStore.add(mnCafe);

		JMenuItem mntmCafe1 = new JMenuItem("Cafe 1");
		mntmCafe1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cafe1Frame.contentPane.removeAll();
				cafe1Frame.contentPane.add(cafe1Frame.c1Panel);
				cafe1Frame.setVisible(true);
				cafe1Frame.repaint();
			}
		});
		mnCafe.add(mntmCafe1);

		JMenuItem mntmCafe2 = new JMenuItem("Cafe 2");
		mntmCafe2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cafe2Frame.contentPane.removeAll();
				cafe2Frame.contentPane.add(cafe2Frame.c1Panel);
				cafe2Frame.setVisible(true);
				cafe2Frame.repaint();
			}
		});
		mnCafe.add(mntmCafe2);

		JMenu mnVending = new JMenu("Vending Machine");
		mnFoodStore.add(mnVending);

		JMenuItem mntmVendingMachine1 = new JMenuItem("Vending Machine 1");
		mntmVendingMachine1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm1Frame.contentPane.removeAll();
				vm1Frame.contentPane.add(vm1Frame.c1Panel);
				vm1Frame.setVisible(true);
				vm1Frame.repaint();
			}
		});
		mnVending.add(mntmVendingMachine1);

		JMenuItem mntmVendingMachine2 = new JMenuItem("Vending Machine 2");
		mntmVendingMachine2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vm2Frame.contentPane.removeAll();
				vm2Frame.contentPane.add(vm2Frame.c1Panel);
				vm2Frame.setVisible(true);
				vm2Frame.repaint();
			}
		});
		mnVending.add(mntmVendingMachine2);

		JMenu mnSystem = new JMenu("System");
		menuBar.add(mnSystem);

		JMenuItem mntmLogOff = new JMenuItem("Log off");
		mntmLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPane.setVisible(true);
				JOptionPane.showMessageDialog(null, "Thanks for using Smart Cafe System!");
				contentPane.remove(mapDisplayPane);
				contentPane.remove(menuBar);
				contentPane.add(loginPane);
				cardID_textField.setText("");
				passwordField.setText("");
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		mnSystem.add(mntmLogOff);

	}

	class MyMap extends PApplet {

		private static final long serialVersionUID = 1L;

		public UnfoldingMap myMap;

		public void setup() {

			size(976, 480);
			myMap = new UnfoldingMap(this, 200, 0, 776, 480, new Google.GoogleMapProvider());
			myMap.zoomToLevel(16);
			myMap.panTo(new Location(37.3495814, -121.9394465));
			MapUtils.createDefaultEventDispatcher(this, myMap);
			v1 = new Location(37.3482633, -121.9379641);
			Location v2 = new Location(37.350738, -121.941031);
			Location c1 = new Location(37.3478814, -121.9391222);
			Location c2 = new Location(37.3494082, -121.9426921);
			mv1 = new VendingMachineMarker(v1);
			mv2 = new VendingMachineMarker(v2);
			mc1 = new CafeMarker(c1);
			mc2 = new CafeMarker(c2);
			myMap.addMarker(mv1);
			myMap.addMarker(mv2);
			myMap.addMarker(mc1);
			myMap.addMarker(mc2);
		}

		public void draw() {
			background(10);
			myMap.draw();
			mouseEntered();
			mouseClicked();
			

		}

		public void mouseEntered() {
			if (mouseX + 210 >= mv1.getScreenPosition(myMap).x && mouseX + 190 <= mv1.getScreenPosition(myMap).x
					&& mouseY >= mv1.getScreenPosition(myMap).y - 10 && mouseY <= mv1.getScreenPosition(myMap).y + 10) {
				fill(255, 0, 0);
				ellipse(mv1.getScreenPosition(myMap).x - 200, mv1.getScreenPosition(myMap).y, 25, 25);
			} else if (mouseX + 210 >= mv2.getScreenPosition(myMap).x && mouseX + 190 <= mv2.getScreenPosition(myMap).x
					&& mouseY >= mv2.getScreenPosition(myMap).y - 10 && mouseY <= mv2.getScreenPosition(myMap).y + 10) {
				fill(255, 0, 0);
				ellipse(mv2.getScreenPosition(myMap).x - 200, mv2.getScreenPosition(myMap).y, 25, 25);
			} else if (mouseX + 210 >= mc1.getScreenPosition(myMap).x && mouseX + 190 <= mc1.getScreenPosition(myMap).x
					&& mouseY >= mc1.getScreenPosition(myMap).y - 10 && mouseY <= mc1.getScreenPosition(myMap).y + 10) {
				fill(0, 0, 255);
				ellipse(mc1.getScreenPosition(myMap).x - 200, mc1.getScreenPosition(myMap).y, 25, 25);
			} else if (mouseX + 210 >= mc2.getScreenPosition(myMap).x && mouseX + 190 <= mc2.getScreenPosition(myMap).x
					&& mouseY >= mc2.getScreenPosition(myMap).y - 10 && mouseY <= mc2.getScreenPosition(myMap).y + 10) {
				fill(0, 0, 255);
				ellipse(mc2.getScreenPosition(myMap).x - 200, mc2.getScreenPosition(myMap).y, 25, 25);
			} else {
			}
		}

		public void mouseReleased() {
			if (mouseX + 210 >= mv1.getScreenPosition(myMap).x && mouseX + 190 <= mv1.getScreenPosition(myMap).x
					&& mouseY >= mv1.getScreenPosition(myMap).y - 10 && mouseY <= mv1.getScreenPosition(myMap).y + 10) {
				if (!v1on) {
					v1on = true;
					vm1Frame.contentPane.removeAll();
					vm1Frame.contentPane.add(vm1Frame.c1Panel);
					vm1Frame.setVisible(true);
					vm1Frame.repaint();
				} else {
					v1on = false;
				}
			} else if (mouseX + 210 >= mv2.getScreenPosition(myMap).x && mouseX + 190 <= mv2.getScreenPosition(myMap).x
					&& mouseY >= mv2.getScreenPosition(myMap).y - 10 && mouseY <= mv2.getScreenPosition(myMap).y + 10) {
				if (!v2on) {
					v2on = true;
					vm2Frame.contentPane.removeAll();
					vm2Frame.contentPane.add(vm2Frame.c1Panel);
					vm2Frame.setVisible(true);
					vm2Frame.repaint();
				} else {
					v2on = false;
				}
			} else if (mouseX + 210 >= mc1.getScreenPosition(myMap).x && mouseX + 190 <= mc1.getScreenPosition(myMap).x
					&& mouseY >= mc1.getScreenPosition(myMap).y - 10 && mouseY <= mc1.getScreenPosition(myMap).y + 10) {
				if (!c1on) {
					v1on = true;
					cafe1Frame.contentPane.removeAll();
					cafe1Frame.contentPane.add(cafe1Frame.c1Panel);
					cafe1Frame.setVisible(true);
					cafe1Frame.repaint();

				} else {
					c1on = false;
				}
			} else if (mouseX + 210 >= mc2.getScreenPosition(myMap).x && mouseX + 190 <= mc2.getScreenPosition(myMap).x
					&& mouseY >= mc2.getScreenPosition(myMap).y - 10 && mouseY <= mc2.getScreenPosition(myMap).y + 10) {
				if (!c2on) {
					c2on = true;
					cafe2Frame.contentPane.removeAll();
					cafe2Frame.contentPane.add(cafe2Frame.c1Panel);
					cafe2Frame.setVisible(true);
					cafe2Frame.repaint();
				} else {
					c2on = false;
				}
			} else {
			}
		}

		public MyMap() {
			this.init();

		}

	}

	class MapKey extends PApplet {

		private static final long serialVersionUID = 1L;

		public void setup() {
			size(200, 400);
		}

		public void draw() {
			fill(255, 250, 240);
			rect(6, 6, 175, 369);

			fill(0);
			textAlign(LEFT, CENTER);
			textSize(14);
			text("Food Store", 20, 75);

			fill(0, 0, 200, 100);
			ellipse(30, 225, 20, 20);
			fill(255, 100);
			ellipse(30, 225, 15, 15);

			noStroke();
			fill(200, 0, 0, 100);
			ellipse(30, 150, 20, 20);
			fill(255, 100);
			ellipse(30, 150, 15, 15);

			fill(0, 0, 0);
			text("Cafe", 50, 225);
			text("Vending Machine", 50, 150);

		}

		public MapKey() {
			this.init();
		}
	}

	class CafeMarker extends SimplePointMarker {

		public CafeMarker(Location location) {
			super(location);
		}

		public void draw(PGraphics pg, float x, float y) {
			pg.pushStyle();
			pg.noStroke();
			pg.fill(0, 0, 200, 100);
			pg.ellipse(x, y, 20, 20);
			pg.fill(255, 100);
			pg.ellipse(x, y, 15, 15);
			pg.popStyle();
		}

	}

	class VendingMachineMarker extends SimplePointMarker {

		public VendingMachineMarker(Location location) {
			super(location);
		}

		public void draw(PGraphics pg, float x, float y) {
			pg.pushStyle();
			pg.noStroke();
			pg.fill(200, 0, 0, 100);
			pg.ellipse(x, y, 20, 20);
			pg.fill(255, 100);
			pg.ellipse(x, y, 15, 15);
			pg.popStyle();
		}

	}
}
