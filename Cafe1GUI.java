package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import processing.core.PApplet;

public class Cafe1GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JPanel c1Panel;
	private JTable menuTable;
	private JTable shoppingCartTable;
	private JComboBox<String> quantity_comboBox;
	private DefaultTableModel modelMenu;
	private DefaultTableModel modelSC;
	private JLabel lblTotal;
	private JTextField totalCost_textField;
	protected static Card card1;
	private JTextField totalfund_textField;
	private JTextField todayExpense_textField;
	private JTextField addFund_textField;
	private JTextField calorieLimit_textField;
	private JTextField caloriesLeftToday_textField;
	private JTextField cardID_textField;
	private double todayExpense;
	protected Cafe cafe1 = new Cafe();
	private JTextPane display_textPane;
	private float cal_suminsc;
	private JPanel dietaryProfileDisplayPanel;
	private JPanel expenseProfileDisplayPanel;
	private int[][] colorList;
	private JTextField availableFund_textField;
	private Double monthlyExpenseSum = (double) 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cafe1GUI frame = new Cafe1GUI(card1);
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
	public Cafe1GUI(Card card) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		card1 = card;
		cafe1.read("CAFE1.txt");


		c1Panel = new JPanel();
		c1Panel.setForeground(Color.BLACK);
		c1Panel.setBackground(new Color(250, 250, 250));
		c1Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		c1Panel.setBounds(6, 6, 788, 466);
		contentPane.add(c1Panel);
		c1Panel.setLayout(null);

		JLabel lblCafe1 = new JLabel("Cafe 1");
		lblCafe1.setForeground(new Color(0, 80, 204));
		lblCafe1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblCafe1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCafe1.setBounds(6, 6, 80, 34);
		c1Panel.add(lblCafe1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(553, 38, 229, 422);
		c1Panel.add(tabbedPane);

		JPanel expensePanel1 = new JPanel();
		tabbedPane.addTab("Expense", null, expensePanel1, null);
		expensePanel1.setLayout(null);

		JButton btnView = new JButton("View Monthly Expense ");
		btnView.setBounds(17, 315, 162, 43);
		expensePanel1.add(btnView);

		JButton btnAddFund = new JButton("Add Fund");
		btnAddFund.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnAddFund.setBackground(Color.BLUE);
		btnAddFund.setForeground(Color.BLUE);
		btnAddFund.setBounds(17, 260, 162, 43);
		expensePanel1.add(btnAddFund);

		for (int i = 0; i < card1.getExpenseProfile().getFundsSpentMonthly().size(); i++) {
			monthlyExpenseSum += card1.getExpenseProfile().getFundsSpentMonthly().get(i);
		}
		monthlyExpenseSum += card1.getExpenseProfile().getFundsSpentToday();
		monthlyExpenseSum += card1.getExpenseProfile().getTotalFunds();
		monthlyExpenseSum = (double) monthlyExpenseSum.intValue();

		JLabel lblTotalFunds = new JLabel("Total Funds:");
		lblTotalFunds.setBounds(17, 10, 130, 20);
		expensePanel1.add(lblTotalFunds);

		totalfund_textField = new JTextField();
		totalfund_textField.setEditable(false);
		totalfund_textField.setBounds(17, 32, 162, 36);
		expensePanel1.add(totalfund_textField);
		totalfund_textField.setColumns(10);
		totalfund_textField.setText(Double.toString(monthlyExpenseSum));

		JLabel lblNewLabel = new JLabel("Today's Expense:");
		lblNewLabel.setBounds(17, 125, 130, 20);
		expensePanel1.add(lblNewLabel);

		todayExpense_textField = new JTextField();
		todayExpense_textField.setEditable(false);
		todayExpense_textField.setBounds(17, 145, 162, 36);
		expensePanel1.add(todayExpense_textField);
		todayExpense_textField.setColumns(10);
		todayExpense_textField.setText(Double.toString(card1.getExpenseProfile().getFundsSpentToday()));

		JLabel lblAvailableFunds = new JLabel("Available Funds:");
		lblAvailableFunds.setBounds(17, 70, 130, 20);
		expensePanel1.add(lblAvailableFunds);

		availableFund_textField = new JTextField();
		availableFund_textField.setBounds(17, 90, 162, 36);
		expensePanel1.add(availableFund_textField);
		availableFund_textField.setColumns(10);
		availableFund_textField.setText(Double.toString(card1.getExpenseProfile().getTotalFunds()));

		JLabel addFund_lbl = new JLabel("Add Funds:");
		addFund_lbl.setBounds(17, 185, 130, 20);
		expensePanel1.add(addFund_lbl);

		addFund_textField = new JTextField();
		addFund_textField.setColumns(10);
		addFund_textField.setBounds(17, 210, 162, 36);
		expensePanel1.add(addFund_textField);

		JPanel dietaryPanel1 = new JPanel();
		tabbedPane.addTab("Dietary", null, dietaryPanel1, null);
		dietaryPanel1.setLayout(null);

		JButton btnView_1 = new JButton("View Dietary Profile");
		btnView_1.setBounds(35, 284, 150, 40);
		dietaryPanel1.add(btnView_1);

		JButton btnUpdate_1 = new JButton("Update Dietary Profile");
		btnUpdate_1.setForeground(Color.BLUE);
		btnUpdate_1.setBackground(Color.BLUE);
		btnUpdate_1.setBounds(35, 325, 150, 40);
		dietaryPanel1.add(btnUpdate_1);

		JPanel caloriePanel = new JPanel();
		caloriePanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Calories",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		caloriePanel.setBounds(6, 6, 196, 130);
		dietaryPanel1.add(caloriePanel);
		caloriePanel.setLayout(null);

		JLabel lblCalorieLimit = new JLabel("Calorie Limit:");
		lblCalorieLimit.setBounds(10, 24, 90, 16);
		caloriePanel.add(lblCalorieLimit);

		calorieLimit_textField = new JTextField();
		calorieLimit_textField.setBounds(10, 44, 130, 26);
		caloriePanel.add(calorieLimit_textField);
		calorieLimit_textField.setColumns(10);
		calorieLimit_textField.setEnabled(false);

		JLabel caloriesLeftToday_label = new JLabel("Calorie Left Today:");
		caloriesLeftToday_label.setBounds(10, 75, 170, 16);
		caloriePanel.add(caloriesLeftToday_label);

		caloriesLeftToday_textField = new JTextField();
		caloriesLeftToday_textField.setEditable(false);
		caloriesLeftToday_textField.setColumns(10);
		caloriesLeftToday_textField.setBounds(10, 95, 130, 26);
		caloriePanel.add(caloriesLeftToday_textField);

		JPanel allergyPanel = new JPanel();
		allergyPanel.setBorder(new TitledBorder(null, "Allergies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		allergyPanel.setBounds(6, 142, 196, 142);
		dietaryPanel1.add(allergyPanel);
		allergyPanel.setLayout(null);
		allergyPanel.setEnabled(false);

		JCheckBox chckbxDairy = new JCheckBox("dairy");
		chckbxDairy.setBounds(6, 19, 85, 23);
		allergyPanel.add(chckbxDairy);
		chckbxDairy.setEnabled(false);

		JCheckBox chckbxEggs = new JCheckBox("eggs");
		chckbxEggs.setBounds(100, 19, 85, 23);
		allergyPanel.add(chckbxEggs);
		chckbxEggs.setEnabled(false);

		JCheckBox chckbxFish = new JCheckBox("fish");
		chckbxFish.setBounds(6, 47, 85, 23);
		allergyPanel.add(chckbxFish);
		chckbxFish.setEnabled(false);

		JCheckBox chckbxPeanuts = new JCheckBox("peanuts");
		chckbxPeanuts.setBounds(100, 47, 85, 23);
		allergyPanel.add(chckbxPeanuts);
		chckbxPeanuts.setEnabled(false);

		JCheckBox chckbxShellfish = new JCheckBox("shellfish");
		chckbxShellfish.setBounds(6, 75, 90, 23);
		allergyPanel.add(chckbxShellfish);
		chckbxShellfish.setEnabled(false);

		JCheckBox chckbxSoy = new JCheckBox("soy");
		chckbxSoy.setBounds(100, 75, 85, 23);
		allergyPanel.add(chckbxSoy);
		chckbxSoy.setEnabled(false);

		JCheckBox chckbxTreenut = new JCheckBox("treenut");
		chckbxTreenut.setBounds(6, 99, 90, 23);
		allergyPanel.add(chckbxTreenut);
		chckbxTreenut.setEnabled(false);

		JCheckBox chckbxWheat = new JCheckBox("wheat");
		chckbxWheat.setBounds(100, 99, 85, 23);
		allergyPanel.add(chckbxWheat);
		chckbxWheat.setEnabled(false);

		JScrollPane display_scrollPane = new JScrollPane();
		display_scrollPane.setBounds(250, 72, 303, 186);
		c1Panel.add(display_scrollPane);

		display_textPane = new JTextPane();
		display_scrollPane.setViewportView(display_textPane);

		JLabel lblDisplay = new JLabel("Display");
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplay.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblDisplay.setBounds(360, 51, 90, 16);
		c1Panel.add(lblDisplay);

		dietaryProfileDisplayPanel = new JPanel();
		dietaryProfileDisplayPanel.setBounds(250, 256, 303, 195);
		c1Panel.add(dietaryProfileDisplayPanel);
		DietaryProfileDisplay dietaryProfile = new DietaryProfileDisplay();
		dietaryProfileDisplayPanel.add(dietaryProfile);
		dietaryProfile.setBounds(255, 261, 293, 185);
		dietaryProfile.setVisible(false);
		dietaryProfileDisplayPanel.setVisible(false);

		expenseProfileDisplayPanel = new JPanel();
		expenseProfileDisplayPanel.setBounds(250, 256, 303, 195);
		c1Panel.add(expenseProfileDisplayPanel);
		ExpenseProfileDisplay expenseProfile = new ExpenseProfileDisplay();
		expenseProfileDisplayPanel.add(expenseProfile);
		expenseProfile.setBounds(255, 261, 293, 185);
		expenseProfile.setVisible(false);
		expenseProfileDisplayPanel.setVisible(false);

		JScrollPane menu_scrollPane = new JScrollPane();
		menu_scrollPane.setBounds(20, 75, 220, 113);
		c1Panel.add(menu_scrollPane);
		modelMenu = new DefaultTableModel();
		modelMenu.addColumn("Food Item");
		modelMenu.addColumn("Price");
		menuTable = new JTable(modelMenu);
		menu_scrollPane.setViewportView(menuTable);
		menuTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuTable.setPreferredScrollableViewportSize(getMaximumSize());
		menuTable.setFillsViewportHeight(true);
		menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < cafe1.getMenu().size(); i++) {
			String name = cafe1.getMenu().get(i).getName();
			double price = cafe1.getMenu().get(i).getPrice();
			modelMenu.addRow(new Object[] { name, price });
		}

		JButton btnAddToCart = new JButton("Add to Cart");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantity_new = 0;
				Map<String, Integer> scMap = new HashMap<>();
				if (menuTable.getSelectedRowCount() != 0) {
					if (modelSC.getRowCount() == 0) {
						modelSC.addRow(new Object[] { (String) menuTable.getValueAt(menuTable.getSelectedRow(), 0),
								Integer.parseInt(quantity_comboBox.getSelectedItem().toString()) });
						scMap.put((String) menuTable.getValueAt(menuTable.getSelectedRow(), 0),
								Integer.parseInt(quantity_comboBox.getSelectedItem().toString()));
						quantity_comboBox.setSelectedIndex(0);

					} else {
						for (int i = 0; i < modelSC.getRowCount(); i++) {
							scMap.put((String) modelSC.getValueAt(i, 0), (Integer) modelSC.getValueAt(i, 1));
						}
						if (scMap.containsKey((String) menuTable.getValueAt(menuTable.getSelectedRow(), 0))) {
							quantity_new = Integer.parseInt(quantity_comboBox.getSelectedItem().toString())
									+ scMap.get((String) menuTable.getValueAt(menuTable.getSelectedRow(), 0));

							for (int j = 0; j < modelSC.getRowCount(); j++) {
								if (((String) modelSC.getValueAt(j, 0))
										.equals((String) menuTable.getValueAt(menuTable.getSelectedRow(), 0))) {
									modelSC.setValueAt(quantity_new, j, 1);

								}
								quantity_comboBox.setSelectedIndex(0);
							}
						} else {
							modelSC.addRow(new Object[] { (String) menuTable.getValueAt(menuTable.getSelectedRow(), 0),
									Integer.parseInt(quantity_comboBox.getSelectedItem().toString()) });
							quantity_comboBox.setSelectedIndex(0);
						}
					}
				}

			}
		});
		btnAddToCart.setBounds(122, 200, 100, 29);
		c1Panel.add(btnAddToCart);

		quantity_comboBox = new JComboBox<>();
		quantity_comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
		quantity_comboBox.setSelectedIndex(0);
		quantity_comboBox.setBounds(25, 200, 80, 27);
		c1Panel.add(quantity_comboBox);

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblMenu.setBounds(20, 52, 61, 16);
		c1Panel.add(lblMenu);

		JLabel lblShoppingCart = new JLabel("Shopping Cart");
		lblShoppingCart.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblShoppingCart.setBounds(20, 248, 130, 16);
		c1Panel.add(lblShoppingCart);

		JScrollPane shoppingCart_Sc = new JScrollPane();
		shoppingCart_Sc.setBounds(20, 276, 220, 101);
		c1Panel.add(shoppingCart_Sc);
		modelSC = new DefaultTableModel();
		modelSC.addColumn("Food Item");
		modelSC.addColumn("Quantity");
		shoppingCartTable = new JTable(modelSC);
		shoppingCart_Sc.setViewportView(shoppingCartTable);
		modelSC.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				float sum = 0;
				cal_suminsc = 0;
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < modelSC.getRowCount(); i++) {
					String foodName = (String) shoppingCartTable.getValueAt(i, 0);
					for (int j = 0; j < cafe1.getMenu().size(); j++) {
						if (cafe1.getMenu().get(j).getName().equals(foodName)) {
							sum = (float) (sum + cafe1.getMenu().get(j).getPrice()
									* Float.parseFloat(shoppingCartTable.getValueAt(i, 1).toString()));
							cal_suminsc = cal_suminsc + cafe1.getMenu().get(j).getCalories()
									* Float.parseFloat(shoppingCartTable.getValueAt(i, 1).toString());
						}
					}
				}
				totalCost_textField.setText(Float.toString(sum));

				for (int m = 0; m < modelSC.getRowCount(); m++) {
					String foodName = (String) shoppingCartTable.getValueAt(m, 0);
					for (int n = 0; n < cafe1.getMenu().size(); n++) {
						if (cafe1.getMenu().get(n).getName().equals(foodName)) {
							if (cafe1.getMenu().get(n).getAllergyList().getDairy()
									&& card1.getDietaryProfile().getAllergyList().getDairy()) {
								sb.append("Warning: You Are Allergic to dairy in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getEggs()
									&& card1.getDietaryProfile().getAllergyList().getEggs()) {
								sb.append("Warning: You Are Allergic to eggs in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getFish()
									&& card1.getDietaryProfile().getAllergyList().getFish()) {
								sb.append("Warning: You Are Allergic to fish in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getPeanuts()
									&& card1.getDietaryProfile().getAllergyList().getPeanuts()) {
								sb.append("Warning: You Are Allergic to peanuts in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getShellFish()
									&& card1.getDietaryProfile().getAllergyList().getShellFish()) {
								sb.append("Warning: You Are Allergic to shellfish in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getSoy()
									&& card1.getDietaryProfile().getAllergyList().getSoy()) {
								sb.append("Warning: You Are Allergic to soy in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getTreeNuts()
									&& card1.getDietaryProfile().getAllergyList().getTreeNuts()) {
								sb.append("Warning: You Are Allergic to treenuts in " + foodName + "! \n");
							}
							if (cafe1.getMenu().get(n).getAllergyList().getWheat()
									&& card1.getDietaryProfile().getAllergyList().getWheat()) {
								sb.append("Warning: You Are Allergic to wheat in " + foodName + "! \n");
							}
						}
					}

				}
				if (shoppingCartTable.getRowCount() != 0) {
					sb.append("\n\n");
					sb.append("Your Calories Left is " + card1.getDietaryProfile().getCaloriesLeft() + ". \n");
					sb.append("The Calories for This Purchase is " + cal_suminsc + ". \n\n\n");
					if (card1.getDietaryProfile().getCaloriesLeft() < cal_suminsc) {
						sb.append("Warning: You Will Exceed Your Daily Calories Limit! \n");
					}
				}

				display_textPane.setText(sb.toString());
			}
		});

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shoppingCartTable.getRowCount() > 0 && shoppingCartTable.getSelectedColumnCount() > 0) {
					modelSC.removeRow(shoppingCartTable.getSelectedRow());
				}
			}
		});
		btnRemove.setBounds(20, 387, 110, 29);
		c1Panel.add(btnRemove);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (shoppingCartTable.getRowCount() > 0) {
					for (int i = 0; i < shoppingCartTable.getRowCount(); i++) {
						modelSC.removeRow(i);
					}
				}
			}
		});
		btnClear.setBounds(130, 387, 110, 29);
		c1Panel.add(btnClear);

		JButton btnBuy = new JButton("Buy");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shoppingCartTable.getRowCount() > 0 && !totalCost_textField.getText().equals("0.0")) {
					if (card1.getExpenseProfile().getTotalFunds() >= Double
							.parseDouble(totalCost_textField.getText())) {
						int reply = JOptionPane.showConfirmDialog(null, "Confirm Purchasing the Food Items?", null,
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							// Subtract the expense and reset the total fund.
							card1.getExpenseProfile().setTotalFunds(card1.getExpenseProfile().getTotalFunds()
									- Double.parseDouble(totalCost_textField.getText()));
							availableFund_textField.setText(Double.toString(card1.getExpenseProfile().getTotalFunds()));
							totalfund_textField.setText(Double.toString(monthlyExpenseSum));
							// updating todays expense
							todayExpense = card1.getExpenseProfile().getFundsSpentToday();
							todayExpense += Double.parseDouble(totalCost_textField.getText());
							todayExpense_textField.setText(Double.toString(todayExpense));
							card1.getExpenseProfile().setFundsSpentToday(todayExpense);
							// updating dietary profile
							card1.getDietaryProfile().setCaloriesConsumedToday(
									card1.getDietaryProfile().getCaloriesConsumedToday() + Math.round(cal_suminsc));
							caloriesLeftToday_textField
									.setText(Integer.toString(card1.getDietaryProfile().getCaloriesLeft()));

							// save the data to card after purchasing!
							card1.save();

							// Clear Up Shopping Cart!
							while (shoppingCartTable.getRowCount() > 0) {
								for (int i = 0; i < shoppingCartTable.getRowCount(); i++) {
									modelSC.removeRow(i);
								}
							}
							
							DateFormat format = new SimpleDateFormat("MM/dd HH:mm");
							int num = new Random().nextInt(2) + 1;
							long time = new Date().getTime() + (new Random().nextInt(10) + 1) * 60 * 1000;
							Date date = new Date(time);
							display_textPane.setText("Please go to Cafe " + num + " to pick up or dine in after "
									+ format.format(date) + ".");
						} else {
						}
					} else {
						JOptionPane.showMessageDialog(null, "You Don't Have Sufficient Fund!");
						display_textPane.setText("You Don't Have Sufficient Fund!");
					}
				}
			}
		});
		btnBuy.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnBuy.setForeground(Color.BLUE);
		btnBuy.setBackground(Color.BLUE);
		btnBuy.setBounds(130, 418, 110, 42);
		c1Panel.add(btnBuy);

		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(10, 427, 40, 26);
		c1Panel.add(lblTotal);

		totalCost_textField = new JTextField();
		totalCost_textField.setEditable(false);
		totalCost_textField.setBounds(46, 427, 80, 26);
		c1Panel.add(totalCost_textField);
		totalCost_textField.setColumns(10);
		totalCost_textField.setText("0.0");

		cardID_textField = new JTextField();
		cardID_textField.setEditable(false);
		cardID_textField.setBounds(641, 6, 90, 26);
		c1Panel.add(cardID_textField);
		cardID_textField.setText(Integer.toString(card1.getId()));

		JLabel lblCardId = new JLabel("Card ID:");
		lblCardId.setBounds(580, 10, 61, 16);
		c1Panel.add(lblCardId);

		btnAddFund.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addFund_textField.getText().matches("[1-9][0-9]*\\.[0-9][0-9]")
						|| addFund_textField.getText().matches("[1-9][0-9]*\\.[0-9]")
						|| addFund_textField.getText().matches("[1-9][0-9]*")) {
					int reply = JOptionPane.showConfirmDialog(null, "Confirm Adding Fund to the Expense Profile?", null,
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						card1.getExpenseProfile().setTotalFunds(Double.parseDouble(addFund_textField.getText())
								+ card1.getExpenseProfile().getTotalFunds());
						monthlyExpenseSum += Double.parseDouble(addFund_textField.getText());
						availableFund_textField.setText(Double.toString(card1.getExpenseProfile().getTotalFunds()));
						totalfund_textField.setText(Double.toString(monthlyExpenseSum));
						card1.save();
						addFund_textField.setText("0.0");
						display_textPane.setText("Expense Profile Successfully Updated!");
					}
				} else {
					display_textPane.setText("Invalid Input for Addfund. Please Reenter!");
				}
			}
		});

		btnView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calorieLimit_textField.setEnabled(true);
				calorieLimit_textField.setText(Integer.toString(card1.getDietaryProfile().getCalorieLimit()));
				caloriesLeftToday_textField.setText(Integer.toString(card1.getDietaryProfile().getCaloriesLeft()));
				chckbxDairy.setEnabled(true);
				chckbxEggs.setEnabled(true);
				chckbxFish.setEnabled(true);
				chckbxPeanuts.setEnabled(true);
				chckbxShellfish.setEnabled(true);
				chckbxSoy.setEnabled(true);
				chckbxTreenut.setEnabled(true);
				chckbxWheat.setEnabled(true);
				chckbxDairy.setSelected(card1.getDietaryProfile().getAllergyList().getDairy());
				chckbxEggs.setSelected(card1.getDietaryProfile().getAllergyList().getEggs());
				chckbxFish.setSelected(card1.getDietaryProfile().getAllergyList().getFish());
				chckbxPeanuts.setSelected(card1.getDietaryProfile().getAllergyList().getPeanuts());
				chckbxShellfish.setSelected(card1.getDietaryProfile().getAllergyList().getShellFish());
				chckbxSoy.setSelected(card1.getDietaryProfile().getAllergyList().getSoy());
				chckbxTreenut.setSelected(card1.getDietaryProfile().getAllergyList().getTreeNuts());
				chckbxWheat.setSelected(card1.getDietaryProfile().getAllergyList().getWheat());

				expenseProfile.setVisible(false);
				expenseProfileDisplayPanel.setVisible(false);
				dietaryProfileDisplayPanel.setVisible(true);
				dietaryProfile.setVisible(true);
				dietaryProfileDisplayPanel.repaint();
			}
		});

		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (calorieLimit_textField.isEnabled() && chckbxDairy.isEnabled() && chckbxEggs.isEnabled()
						&& chckbxFish.isEnabled() && chckbxPeanuts.isEnabled() && chckbxShellfish.isEnabled()
						&& chckbxSoy.isEnabled() && chckbxTreenut.isEnabled() && chckbxWheat.isEnabled()) {
					int reply = JOptionPane.showConfirmDialog(null, "Confirm Updating Dietary Profile?", null,
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						card1.getDietaryProfile().setCalorieLimit(Integer.parseInt(calorieLimit_textField.getText()));
						caloriesLeftToday_textField
								.setText(Integer.toString(card1.getDietaryProfile().getCaloriesLeft()));
						card1.getDietaryProfile().getAllergyList().setDairy(chckbxDairy.isSelected());
						card1.getDietaryProfile().getAllergyList().setEggs(chckbxEggs.isSelected());
						card1.getDietaryProfile().getAllergyList().setFish(chckbxFish.isSelected());
						card1.getDietaryProfile().getAllergyList().setPeanuts(chckbxPeanuts.isSelected());
						card1.getDietaryProfile().getAllergyList().setShellFish(chckbxShellfish.isSelected());
						card1.getDietaryProfile().getAllergyList().setSoy(chckbxSoy.isSelected());
						card1.getDietaryProfile().getAllergyList().setTreeNuts(chckbxTreenut.isSelected());
						card1.getDietaryProfile().getAllergyList().setWheat(chckbxWheat.isSelected());
						card1.save();
						display_textPane.setText("Dietary Profile Successfully Updated!");
					} else {
					}
				}
			}
		});

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dietaryProfileDisplayPanel.setVisible(false);
				dietaryProfile.setVisible(false);
				expenseProfile.setVisible(true);
				expenseProfileDisplayPanel.setVisible(true);
				expenseProfileDisplayPanel.repaint();
			}
		});

	}

	class DietaryProfileDisplay extends PApplet {

		private static final long serialVersionUID = 1L;

		public void setup() {
			size(297, 189);
			background(255);
			fill(200, 200, 30, 50);

		}

		public void draw() {
			fill(255, 250, 200);
			background(255);

			fill(0);
			textAlign(LEFT, CENTER);
			textSize(14);
			text("Calories Consumed Monthly: ", 10, 10);

			ArrayList<Integer> dietaryProfileData = card1.getDietaryProfile().getCaloriesConsumedMonthly();

			float maximum_y = 0;
			for (int i = 0; i < dietaryProfileData.size(); i++) {
				if (dietaryProfileData.get(i) > maximum_y) {
					maximum_y = dietaryProfileData.get(i);
				}
			}
			while (maximum_y < card1.getDietaryProfile().getCaloriesConsumedToday()) {
				maximum_y = card1.getDietaryProfile().getCaloriesConsumedToday();
			}

			maximum_y = (float) (maximum_y * 1.3);
			float arg1;
			float arg2;
			float arg3;
			float arg4;

			for (int i = 0; i < dietaryProfileData.size(); i++) {
				fill(0);
				textAlign(LEFT, CENTER);
				textSize(11);
				text(i + 1, (float) (287 / (dietaryProfileData.size() + 1) / 2 - 5
						+ i * 287 / (dietaryProfileData.size() + 1)), (float) 182);
				text(dietaryProfileData.get(i), (float) (i * 287 / (dietaryProfileData.size() + 1)),
						(maximum_y - dietaryProfileData.get(i)) * 180 / maximum_y - 15);
			}
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(11);
			text("Today", (float) (dietaryProfileData.size()) * 287 / (dietaryProfileData.size() + 1), (float) 182);
			text(card1.getDietaryProfile().getCaloriesConsumedToday(),
					(float) (dietaryProfileData.size() * 287 / (dietaryProfileData.size() + 1)),
					(maximum_y - card1.getDietaryProfile().getCaloriesConsumedToday()) * 180 / maximum_y - 15);

			for (int i = 0; i < dietaryProfileData.size(); i++) {
				fill(255, 250, 200);
				stroke(204, 102, 0);
				arg1 = (float) (i * 287 / (dietaryProfileData.size() + 1));
				arg2 = (float) ((maximum_y - dietaryProfileData.get(i)) * 180 / maximum_y - 5);
				arg3 = (float) ((287 / (dietaryProfileData.size() + 1)) * 0.9);
				arg4 = (float) ((dietaryProfileData.get(i)) / maximum_y * 180.0);
				rect(arg1, arg2, arg3, arg4);
			}

			fill(255, 250, 200);
			stroke(204, 102, 0);
			rect((float) (dietaryProfileData.size() * 287 / (dietaryProfileData.size() + 1)),
					(float) ((maximum_y - card1.getDietaryProfile().getCaloriesConsumedToday()) * 180 / maximum_y - 5),
					(float) ((287 / (dietaryProfileData.size() + 1)) * 0.9),
					(float) (card1.getDietaryProfile().getCaloriesConsumedToday() / maximum_y * 180.0));

			fill(255, 0, 0);
			strokeWeight((float) 1.5);
			line(0, Math.round((maximum_y - card1.getDietaryProfile().getCalorieLimit()) / maximum_y * 180.0), 297,
					Math.round((maximum_y - card1.getDietaryProfile().getCalorieLimit()) / maximum_y * 180.0));
			text("Daily Calories Limit", 6,
					Math.round((maximum_y - card1.getDietaryProfile().getCalorieLimit()) / maximum_y * 180.0) - 10);

		}

		public DietaryProfileDisplay() {
			this.init();
		}
	}

	class ExpenseProfileDisplay extends PApplet {

		private static final long serialVersionUID = 1L;

		public void setup() {
			size(297, 189);
			background(255);
			fill(200, 200, 30, 50);

			colorList = new int[card1.getExpenseProfile().getFundsSpentMonthly().size() + 1][3];
			for (int i = 0; i < (card1.getExpenseProfile().getFundsSpentMonthly().size() + 1); i++) {
				for (int j = 0; j < 3; j++) {
					colorList[i][j] = (int) random(255);
				}
			}

		}

		public void draw() {
			fill(255, 250, 200);
			background(255);

			fill(0);
			textAlign(LEFT, CENTER);
			textSize(14);
			text("Money Spent Monthly: ", 10, 10);
			pieChart(150);
			pieLegend();

		}

		public void pieChart(float diameter) {
			float lastAng = 0;
			ArrayList<Double> expenseProfileData = card1.getExpenseProfile().getFundsSpentMonthly();

			for (int i = 0; i < expenseProfileData.size(); i++) {
				fill(colorList[i][0], colorList[i][1], colorList[i][2]);
				arc(210, 100, diameter, diameter, lastAng, lastAng + radians(
						(float) (expenseProfileData.get(i) * 360 / Float.parseFloat(totalfund_textField.getText()))));
				lastAng += radians(
						(float) (expenseProfileData.get(i) * 360 / Float.parseFloat(totalfund_textField.getText())));
			}

			fill(colorList[expenseProfileData.size()][0], colorList[expenseProfileData.size()][1],
					colorList[expenseProfileData.size()][2]);
			arc(210, 100, diameter, diameter, lastAng,
					lastAng + radians((float) (card1.getExpenseProfile().getFundsSpentToday() * 360
							/ Float.parseFloat(totalfund_textField.getText()))));
			lastAng += radians((float) (card1.getExpenseProfile().getFundsSpentToday() * 360
					/ Float.parseFloat(totalfund_textField.getText())));

			fill(200);
			arc(210, 100, diameter, diameter, lastAng,
					lastAng + radians((float) (card1.getExpenseProfile().getTotalFunds() * 360
							/ Float.parseFloat(totalfund_textField.getText()))));
			lastAng += radians((float) (card1.getExpenseProfile().getFundsSpentToday() * 360
					/ Float.parseFloat(totalfund_textField.getText())));

		}

		public void pieLegend() {
			ArrayList<Double> expenseProfileData = card1.getExpenseProfile().getFundsSpentMonthly();
			ArrayList<MyLoc> locList = new ArrayList<>();
			for (int i = 0; i < expenseProfileData.size(); i = i + 1) {
				if (Math.floor(i % 2) == 0) {
					locList.add(new MyLoc(10, 15 + (i / 2) * 15 + 10));
				} else {
					locList.add(new MyLoc(60, 15 + (i / 2) * 15 + 10));
				}
			}
			if (Math.floor(expenseProfileData.size() % 2) == 0) {
				locList.add(new MyLoc(10, (int) (15 + Math.floor((expenseProfileData.size()) / 2) * 15 + 10)));
				locList.add(new MyLoc(60, 15 + (expenseProfileData.size()) / 2 * 15 + 10));
			} else {
				locList.add(new MyLoc(60, (int) (15 + Math.floor((expenseProfileData.size()) / 2) * 15 + 10)));
				locList.add(new MyLoc(10, 15 + (expenseProfileData.size() + 1) / 2 * 15 + 10));
			}

			ArrayList<MyLoc> nameList = new ArrayList<>();
			for (int i = 0; i < expenseProfileData.size(); i = i + 1) {
				if (Math.floor(i % 2) == 0) {
					nameList.add(new MyLoc(25, 15 + (i / 2) * 15 + 15));
				} else {
					nameList.add(new MyLoc(75, 15 + (i / 2) * 15 + 15));
				}
			}
			if (Math.floor(expenseProfileData.size() % 2) == 0) {
				nameList.add(new MyLoc(25, (int) (15 + Math.floor((expenseProfileData.size()) / 2) * 15 + 15)));
				nameList.add(new MyLoc(75, (int) (15 + Math.floor((expenseProfileData.size()) / 2) * 15 + 15)));
			} else {
				nameList.add(new MyLoc(75, (int) (15 + Math.floor((expenseProfileData.size()) / 2) * 15 + 15)));
				nameList.add(new MyLoc(25, (int) (15 + Math.floor((expenseProfileData.size() + 1) / 2) * 15 + 15)));

			}

			for (int i = 0; i < expenseProfileData.size(); i = i + 1) {
				fill(colorList[i][0], colorList[i][1], colorList[i][2]);
				rect(locList.get(i).x, locList.get(i).y, 10, 10);
				fill(0);
				textAlign(LEFT, CENTER);
				textSize(11);
				text(i + 1, nameList.get(i).x, nameList.get(i).y);
			}

			fill(colorList[expenseProfileData.size()][0], colorList[expenseProfileData.size()][1],
					colorList[expenseProfileData.size()][2]);
			rect(locList.get(expenseProfileData.size()).x, locList.get(expenseProfileData.size()).y, 10, 10);
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(11);
			text("Today", nameList.get(expenseProfileData.size()).x, nameList.get(expenseProfileData.size()).y);
			fill(200);
			rect(locList.get(expenseProfileData.size() + 1).x, locList.get(expenseProfileData.size() + 1).y, 10, 10);
			fill(0);
			textAlign(LEFT, CENTER);
			textSize(11);
			text("Unused", nameList.get(expenseProfileData.size() + 1).x,
					nameList.get(expenseProfileData.size() + 1).y);

		}

		public ExpenseProfileDisplay() {
			this.init();
		}
	}

	class MyLoc {
		protected int x;
		protected int y;

		public MyLoc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
