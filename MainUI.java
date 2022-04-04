package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.utils.TradeActivity;
import cryptoTrader.utils.CryptoCoinList;
import cryptoTrader.utils.DataVisualizationCreator;
import cryptoTrader.utils.Result;
import cryptoTrader.utils.TradingBroker;

/**
 * Data: April 1, 2022
 * This class generates the main user interface for adding brokers and performing trades. 
 * This class implements the Singleton design pattern in order to generate a single object of the type MainUI, so we can 
 * 	have a unique reference point to access the instance variables of this object. 
 * This class implements the proxy design pattern when a new row is created.
 * It also uses the observer design pattern as it notifies the broker and activity arrayList object when changes are made
 * @author Ethan and Michael  
 * 
 */
public class MainUI extends JFrame implements ActionListener {
	
	/**
	 * Unique serial version number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * represents the unique instance of this class 
	 */
	private static MainUI instance;
	/**
	 * represents the panel holding the stats of the performed trades 
	 */
	private JPanel stats;
	/**
	 * the arraylist containing all activities performed or failed
	 */
	private ArrayList<TradeActivity> activities = new ArrayList<TradeActivity>();
	/**
	 * the arraylist containing all input trading brokers 
	 */
	private ArrayList<TradingBroker> brokers = new ArrayList<TradingBroker>();
	/**
	 * the model for the table holding the input broker information 
	 */
	private DefaultTableModel dtm;
	/**
	 * the table containing the broker information 
	 */
	private JTable table;

	/**
	 * @return returns the instance of the MainUI class 
	 */
	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	/**
	 * The private constructor for this class that builds the table 
	 */
	private MainUI() {

		// Set window title
		super("Crypto Trading Tool");

		// Set top bar
		JPanel north = new JPanel();

		JButton trade = new JButton("Perform Trade");
		trade.setActionCommand("refresh");
		trade.addActionListener(this);
		
		// set bottom bar
		JPanel south = new JPanel();
		south.add(trade);

		dtm = new DefaultTableModel(new Object[] { "Trading Client", "Coin List", "Strategy Name" }, 1);
		table = new JTable(dtm);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("Strategy-A");
		strategyNames.add("Strategy-B");
		strategyNames.add("Strategy-C");
		strategyNames.add("Strategy-D");
		TableColumn strategyColumn = table.getColumnModel().getColumn(2);
		JComboBox comboBox = new JComboBox(strategyNames);
		strategyColumn.setCellEditor(new DefaultCellEditor(comboBox));
		JButton addRow = new JButton("Add Row");
		JButton remRow = new JButton("Remove Row");
		addRow.setActionCommand("addTableRow");
		addRow.addActionListener(this);
		remRow.setActionCommand("remTableRow");
		remRow.addActionListener(this);

		scrollPane.setPreferredSize(new Dimension(600, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();

		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));

		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);

		// Set charts region
		JPanel west = new JPanel();
		west.setPreferredSize(new Dimension(1250, 650));
		stats = new JPanel();
		stats.setLayout(new GridLayout(2, 2));

		west.add(stats);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
	}

	/**
	 * This method updates the stats in the table 
	 * @param component represents the stats to be updated 
	 */
	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	/**
	 * @param args used to test the class surpassing the LoginUI
	 */
	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Determines the appropriate action to follow depending on which button was pressed 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		ArrayList<Object> duplicateBroker = new ArrayList<Object>();
		if ("refresh".equals(command)) {
			for(int count = 0; count < dtm.getRowCount(); count++) {
				Object traderObject = dtm.getValueAt(count, 0);
				for(int i = 0; i < duplicateBroker.size(); i++) {
					if(traderObject.equals(duplicateBroker.get(i))) {
						JOptionPane.showMessageDialog(this, "please remove duplicate broker on line " + (count + 1) );
						return;
					}
				}
				duplicateBroker.add(traderObject);
			}
			for (int count = 0; count < dtm.getRowCount(); count++){
				// create a new TradingBroker object 
				// set the broker's name
				Object traderObject = dtm.getValueAt(count, 0);
				if (traderObject == null || traderObject.toString().equals("")) {
					JOptionPane.showMessageDialog(this, "please fill in Trader name on line " + (count + 1) );
					return;
				}
				
				TradingBroker broker = new TradingBroker();
				broker.setName(traderObject.toString());
				boolean flag = false;
				for(int i = 0; i < brokers.size(); i++) {
					if(broker.getName().equals(brokers.get(i).getName())) {
						// set the broker's crypto of interest 
						flag = true;
						Object coinObject = dtm.getValueAt(count, 1);
						if (coinObject == null || coinObject.toString().equals("")) {
							JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
							return;
						}
						CryptoCoinList coinList = new CryptoCoinList(coinObject.toString().split(","));
						brokers.get(i).setList(coinList);
						
						// set the broker's trading strategy 
						Object strategyObject = dtm.getValueAt(count, 2);
						if (strategyObject == null) {
							JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
							return;
						}
						brokers.get(i).setStrategy(strategyObject.toString());
						
						// attempt to perform a trade for this broker
						TradeActivity activity = brokers.get(i).declareInterest();
						if(activity.getAction().equals("failed trade")) {
							brokers.get(i).failedStrategy();
						}
						
						notifyAllBrokers();
						
						// add the result of the trade to the activity log 
						activities.add(activity);
						notifyAllActivities();
					}
				}
				if(!flag) {
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null || coinObject.toString().equals("")) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					CryptoCoinList coinList = new CryptoCoinList(coinObject.toString().replace(" ", "").split(","));
					broker.setList(coinList);
					
					// set the broker's trading strategy 
					Object strategyObject = dtm.getValueAt(count, 2);
					if (strategyObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in strategy name on line " + (count + 1) );
						return;
					}
					broker.setStrategy(strategyObject.toString());
					
					// attempt to perform a trade for this broker
					TradeActivity activity = broker.declareInterest();
					
					// add the result of the trade to the activity log 
					brokers.add(broker);
					notifyAllBrokers();
					
					if(activity.getAction().equals("failed trade")) {
						brokers.get(brokers.size() - 1).failedStrategy();
					}
					activities.add(activity);
					notifyAllActivities();
				}
			}
			stats.removeAll();
			DataVisualizationCreator creator = new DataVisualizationCreator();
			creator.createCharts(activities, brokers);
			//Result result = new Result(activities, brokers);
			
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}
	
	
	/**
	 * This method will loop through all the TradingBroker objects in the brokers
	 * ArrayList and notify each by printing a statement listing all the values
	 * of all the brokers in the TradingBroker ArrayList
	 */
	public void notifyAllBrokers() {
		for(TradingBroker broker : brokers) {
			broker.update();
		}
	}
	
	
	/**
	 * This method will loop through all the TradeActivity objects in the activities
	 * ArrayList and notifies each by printing the statement listing all the values
	 * of all teh activities in the TradeActivity ArrayList
	 */
	public void notifyAllActivities() {
		for(TradeActivity activity : activities) {
			activity.update();
		}
		System.out.println();
	}
}
