package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import cryptoTrader.utils.TradeActivity;
import cryptoTrader.utils.CryptoCoinList;
import cryptoTrader.utils.Result;
import cryptoTrader.utils.TradingBroker;

public class MainUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;
	private JPanel stats, chartPanel, tablePanel;
	
	private ArrayList<TradeActivity> activities = new ArrayList<TradeActivity>();
	private ArrayList<TradingBroker> brokers = new ArrayList<TradingBroker>();
	
	// Should be a reference to a separate object in actual implementation
	private List<String> selectedList;

	private JTextArea selectedTickerList;
//	private JTextArea tickerList;
	private JTextArea tickerText;
	private JTextArea BrokerText;
	private JComboBox<String> strategyList;
	private Map<String, List<String>> brokersTickers = new HashMap<>();
	private Map<String, String> brokersStrategies = new HashMap<>();
	private List<String> selectedTickers = new ArrayList<>();
	private String selectedStrategy = "";
	private DefaultTableModel dtm;
	private JTable table;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

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
		// table.setPreferredSize(new Dimension(600, 300));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Trading Client Actions",
				TitledBorder.CENTER, TitledBorder.TOP));
		Vector<String> strategyNames = new Vector<String>();
		strategyNames.add("None");
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

		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		

		JPanel east = new JPanel();

		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));

		east.add(scrollPane);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
		buttons.add(addRow);
		buttons.add(remRow);
		east.add(buttons);
//		east.add(selectedTickerListLabel);
//		east.add(selectedTickersScrollPane);

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
//		getContentPane().add(west, BorderLayout.WEST);
	}

	public void updateStats(JComponent component) {
		stats.add(component);
		stats.revalidate();
	}

	public static void main(String[] args) {
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("refresh".equals(command)) {
			for (int count = 0; count < dtm.getRowCount(); count++){
				// create a new TradingBroker object 
				// set the broker's name
				Object traderObject = dtm.getValueAt(count, 0);
				if (traderObject == null) {
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
						if (coinObject == null) {
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
						
						System.out.println(brokers.get(i).getName() + " " + brokers.get(i).getCoinList().toString() + " " + brokers.get(i).getStrategy());
						// add the result of the trade to the activity log 
						activities.add(activity);
					}
				}
				if(!flag) {
					Object coinObject = dtm.getValueAt(count, 1);
					if (coinObject == null) {
						JOptionPane.showMessageDialog(this, "please fill in cryptocoin list on line " + (count + 1) );
						return;
					}
					CryptoCoinList coinList = new CryptoCoinList(coinObject.toString().split(","));
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
					
					System.out.println(broker.getName() + " " + broker.getCoinList().toString() + " " + broker.getStrategy());
					// add the result of the trade to the activity log 
					brokers.add(broker);
					if(activity.getAction().equals("failed trade")) {
						brokers.get(brokers.size() - 1).failedStrategy();
					}
					activities.add(activity);
				}
			}
			stats.removeAll();
			Result result = new Result(activities, brokers);
			
		} else if ("addTableRow".equals(command)) {
			dtm.addRow(new String[3]);
		} else if ("remTableRow".equals(command)) {
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1)
				dtm.removeRow(selectedRow);
		}
	}
}