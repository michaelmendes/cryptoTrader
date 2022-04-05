package cryptoTrader.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import cryptoTrader.utils.UserCredentials;

/**
 * Date: April 1, 2022
 * This is the UI that will be used to allow the user to login to the MainUI
 * This class implements the proxy design pattern as it acts as a proxy between the MainUI and 
 * 	the UserCredentials class 
 * @author Michael Mendes
 * 
 */
public class LoginUI extends JFrame implements ActionListener {
	
	/**
	 * unique serial version number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the user's input user name
	 */
	private String userName;
	/**
	 * the user's input password
	 */
	private String password;
	/**
	 * the model for the table holding the user's credentials 
	 */
	private DefaultTableModel dtm;
	/**
	 * the table holding the user's credentials 
	 */
	private JTable table;
	
	/**
	 * constructor for the class which generates the login user interface 
	 */
	private LoginUI() {
		
		// set window title 
		super("User Login");
		
		// create login button
		JButton login = new JButton("Login");
		login.setActionCommand("login");
		login.addActionListener(this);
		
		// add login button to panel
		JPanel south = new JPanel();
		south.add(login);
		
		//create table for credentials 
		dtm = new DefaultTableModel(new Object[] { "Username", "Password"}, 1);
		table = new JTable(dtm);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "User Login",
				TitledBorder.CENTER, TitledBorder.TOP));
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);
		
		// add login table to panel
		JPanel east = new JPanel();
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(scrollPane);
		
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(east, BorderLayout.EAST);
			
	}
	
	/**
	 * if user credentials are entered correctly then the MainUI is opened
	 */
	private void grantAccess() {	
		JFrame mainFrame = MainUI.getInstance();
		mainFrame.setSize(900, 600);
		mainFrame.pack();
		mainFrame.setVisible(true);		
	}
	
	/**
	 * @param args this is where we will launch the system from 
	 * calls the constructor of the class 
	 */
	public static void main(String[] args) {
		JFrame frame = new LoginUI();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);		
	}

	/**
	 * sends the user credentials to the UserCredentials class in order to determine if the 
	 * user may login to the system.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("login".equals(command)) {
			// set the user name and password equal to the user's input 
			userName = (String) dtm.getValueAt(0, 0);
			password = (String) dtm.getValueAt(0, 1);
			// check whether the user credentials are valid 
			UserCredentials attempt = new UserCredentials(userName, password);
			if (attempt.login() == true) {
				grantAccess();
			}
			else {
				// create panel to deal with invalid credentials 
				JPanel center = new JPanel();
				JOptionPane.showMessageDialog(center, "Invalid User Credentials.");
				getContentPane().add(center, BorderLayout.CENTER);
			}
		}
	}
}
