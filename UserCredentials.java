package cryptoTrader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Date: April 1, 2022
 * This class is responsible for checking the user credential database file, taking in the user's
 * input data, and determining whether the user can log into the system. 
 * This class is part of the proxy design pattern. It acts as the RealSubject class for the design. 
 * @author Michael Mendes
 *
 */
public class UserCredentials {
	private String userName;
	private String password;
	private Boolean validUser = false;
	
	/**
	 * @param userName is the user's input user name 
	 * @param password is the user's input password 
	 */
	public UserCredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * @return returns true if the user is granted access or false if not 
	 */
	public Boolean login() {
		File file = new File("CredentialsDB");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] credentials = line.split(" ");
				if (credentials[0].equals(userName) && credentials[1].equals(password)) {
					validUser = true;
					break;
				}
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Could not find User Credential Database");
			e1.printStackTrace();
		}
		return validUser;
	}
}
