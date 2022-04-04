package cryptoTrader.utils;

/**
 *Date: April 2, 2022 
 * @author David Burnett, Michael Mendes
 * This class creates an array which contains CryptoCoin objects of each coin the user inputed
 */
public class CryptoCoinList {
	/**
	 * Declares the CryptoCoin array
	 */
	private CryptoCoin[] cryptoCoinList;
	
	/**
	 * Public constructor which initializes the list of crypto coins 
	 * @param list: is a string array containing the names of the coins entered by the user 
	 */
	public CryptoCoinList(String[] list) {
		
		cryptoCoinList = new CryptoCoin[list.length];
		for(int i = 0; i < list.length; i++) {
			CryptoCoin coin = new CryptoCoin(list[i]); 
			cryptoCoinList[i] = coin;
			cryptoCoinList[i].setDate();
			cryptoCoinList[i].setCoinPrice();
		}
	}
	/**
	 * gets the cryptoCoinList
	 * @return the list of crypto coins
	 */
	public CryptoCoin[] getCryptoCoinList() {
		return cryptoCoinList;
	}
	/**
	 * Iterates through the CryptoCoin list looking for the coin with the specified name
	 * @param coinName: The name of the wanted coin
	 * @return The cryptoCoin object in the list with the specified name
	 * @return null if the coin does not exist in the list
	 */
	public CryptoCoin getCoin(String coinName) {
		for (int i=0; i<cryptoCoinList.length; i++) {
			if (cryptoCoinList[i].getCoinName().equals(coinName)) {
				return cryptoCoinList[i];
			}
		}
		return null;
	}
	/**
	 * Searches the cryptocoin list for the coin with the given name
	 * @param coinName: The name of the wanted coin
	 * @return true if the coin is in the list
	 * @return false if the coin is not found in the list
	 */
	public boolean searchCryptoCoinList(String coinName) {
		
		for(int i = 0; i < cryptoCoinList.length; i++) {
			if(cryptoCoinList[i].getCoinName().equals(coinName)) {
				return true;
			}
		}		
		return false;
	}
	/**
	 * Gathers the names of the coins in the CryptoCoin list into a single string 
	 * @return The string of all coin names in the list
	 */
	public String toString() {
		String sList = "";
		for (int i=0; i<cryptoCoinList.length; i++) {
			sList += cryptoCoinList[i].getCoinName() + ",";
		}
		return sList;
	}
}
