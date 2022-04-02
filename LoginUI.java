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

public class LoginUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private DefaultTableModel dtm;
	private JTable table;
	
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
	
	private void grantAccess() {	
		JFrame mainFrame = MainUI.getInstance();
		mainFrame.setSize(900, 600);
		mainFrame.pack();
		mainFrame.setVisible(true);		
	}
	
	public static void main(String[] args) {
		JFrame frame = new LoginUI();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);		
	}

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