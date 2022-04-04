package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: April 2, 2022
 * @author David Burnett
 * This class stores the information of each crypto coin entered by the user.
 */

public class CryptoCoin {
	/**
	 * The name of the coin
	 */
	private String coinName;
	/**
	 * The Price of the coin in cad
	 */
	private double coinPrice;
	/**
	 * The date the user entered the coin
	 */
	private String strDate;
	/**
	 * An object of the DataFetcher class which will be used to get information on the coin
	 */
	private DataFetcher fetcher;
	
	/**
	 * Public constructor which prepares a CrytpoCoin object
	 * @param name: This is the name of the coin
	 */
	public CryptoCoin(String name) {
		fetcher = new DataFetcher();
		
		coinName = name;
		coinPrice = 0.0;
		strDate = null;
	}
	/**
	 * Gets the price of the coin
	 * @return the price of the coin
	 */
	public double getCoinPrice() {
		return coinPrice;
	}
	/**
	 * Gets the name of the coin
	 * @return the name of the coin
	 */
	public String getCoinName() {
		return coinName;
	}
	/**
	 * Gets the current date
	 * @return the current date
	 */
	public String getDate() {
		return strDate;
	}
	/**
	 * Sets the current date
	 */
	public void setDate() {
		/**
		 * The following code block is from https://www.javatpoint.com/java-simpledateformat which retrieves the current date and formats it as specified
		 */
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		strDate = formatter.format(date);
	}
	/**
	 * Sets the price of the coin, and rounds it to 2 decimal places
	 */
	public void setCoinPrice() {
		coinPrice = fetcher.getPriceForCoin(convertInput(coinName), strDate);
		coinPrice = Math.round( coinPrice * 100.0)/100.0;
	}
	/**
	 * Converts the coin's inputed abbreviation and uses the AvailableCryptoList class to retrieve the coin's cryptoID which is used to obtain the coin's price 
	 * @param cName: the name of the coin
	 * @return the coin's cryptoID 
	 */
	private String convertInput(String cName){
		String[][] conversions = {
				{"BTC", "Bitcoin"},{"ETH", "Ethereum"},{"XRP", "XRP"},
				{"ADA", "Cardano"},{"DOGE", "Dogecoin"},{"SOL", "Solana"},{"CAKE", "PancakeSwap"}};
		
		AvailableCryptoList availableCrypto = AvailableCryptoList.getInstance();
		for(int i = 0; i < conversions.length; i++) {
			if(cName.equals(conversions[i][0])) {
				return availableCrypto.getCryptoID(conversions[i][1]);
			}
		}
		return cName;
	}	
}