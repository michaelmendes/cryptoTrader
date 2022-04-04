package cryptoTrader.utils;

/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This class implements the TradingStrategies interface and attempts to execute strategy B
 */
public class StrategyB implements TradingStrategies{
	/**
	 * Checks the necessary data to determine if the trade can be made
	 * @param list: a list of crypto coin objects the user entered
	 * @return a string containing the action, either buy or sell, the quantity, the abbreviation of the coin and the price of the coin 
	 * @return a string containing failed transaction if the strategy cannot be executed
	 */
	@Override
	public String executeStrategy(CryptoCoinList list) {
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
}
