package cryptoTrader.utils;
/**
 * Date: April 2, 2022
 * @author David Burnett, Ethan Borg, Luke Gabert
 * This class verifies and carries out the specified trade strategy
 */
public class TradingStrategy {
	/**
	 * Represents the strategy as a string
	 */
	private String strategy;	
	/**
	 * Contains all crypto coin objects in a list 
	 */
	private CryptoCoinList list;		
	
	/**
	 * Public constructor which prepares the instance variables of the class
	 * @param strategy: The strategy the user has chosen
	 * @param list: The list of cryptoCoin objects
	 */
	public TradingStrategy(String strategy, CryptoCoinList list) {
		this.strategy = strategy;
		this.list = list;
	}
	/**
	 * Attempts to execute the specified strategy
	 * @return a string containing the action, either buy or sell, the quantity, the abbreviation of the coin and the price of the coin
	 * @return a string containing failed transaction if the strategy cannot be executed
	 */
	public String performTrade() {
		if (strategy.equals("Strategy-A")) {	
			if(list.searchCryptoCoinList("BTC") && list.searchCryptoCoinList("ETH") && list.getCryptoCoinList().length == 2) {
				CryptoCoin bitcoin = list.getCoin("BTC");
				double price = bitcoin.getCoinPrice();
				CryptoCoin etheruem = list.getCoin("ETH");
				double ethPrice = etheruem.getCoinPrice();
				if(price < 58000 && ethPrice > 4000) {
					return "buy 5 BTC " + price;
				} 
				else if(price > 58000 && ethPrice > 4000){
					return "sell 3 BTC " + price;
				}else {
					return "failed transaction";
				}
			}
			else {	
				return "failed transaction";
			}
		}
		
		else if (strategy.equals("Strategy-B")) {
			if(list.searchCryptoCoinList("DOGE") && list.getCryptoCoinList().length == 1) {
				CryptoCoin coin = list.getCoin("DOGE");
				double price = coin.getCoinPrice();
				if (price > 0.1) {
					return "buy 2000 DOGE " + price;
				} 
				else {
					return "failed transaction";
				}
			} 
			else {
				return "failed transaction";
			}
		} 
		
		else if (strategy.equals("Strategy-C")) {
			if(list.searchCryptoCoinList("ADA") && list.getCryptoCoinList().length == 1) {
				CryptoCoin coin = list.getCoin("ADA");
				double price = coin.getCoinPrice();
				if (price > 1) {
					double quant = 500 / price;
					return "buy " +  Math.round(quant * 100.0)/100 + " ADA " + price;
				} 
				else {
					return "failed transaction";
				}
			} 
			else {
				return "failed transaction";
			}
		}  
		
		else if (strategy.equals("Strategy-D")) {
			if(list.searchCryptoCoinList("CAKE") && list.searchCryptoCoinList("SOL") && list.searchCryptoCoinList("XRP") && list.getCryptoCoinList().length == 3) {
				CryptoCoin cake = list.getCoin("CAKE");
				double price = cake.getCoinPrice();
				CryptoCoin sol = list.getCoin("SOL");
				double solPrice = sol.getCoinPrice();
				CryptoCoin xrp = list.getCoin("XRP");
				double xrpPrice = xrp.getCoinPrice();
				if (price < 15 && solPrice > 150 && xrpPrice > 1) {
					return "buy 100 CAKE " + price;
				}else if(price < 15 && solPrice > 150 && xrpPrice < 1) {
					return "buy 1000 XRP " +xrpPrice;
				}else if(price > 10 && solPrice > 180 && xrpPrice > 0.9) {
					return "sell 100,000 SOL " + solPrice;
				}
				else {
					return "failed transaction";
				}
			} 
			else {	
				return "failed transaction";
			}
		}
		return "failed transaction";
	}
}