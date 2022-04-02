package cryptoTrader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserCredentials {
	private String userName;
	private String password;
	private Boolean validUser = false;
	
	public UserCredentials(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
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