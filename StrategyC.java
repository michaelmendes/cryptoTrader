package cryptoTrader.utils;

/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This class implements the TradingStrategies interface and attempts to execute strategy C
 */
public class StrategyC implements TradingStrategies{
	/**
	 * Checks the necessary data to determine if the trade can be made
	 * @param list: a list of crypto coin objects the user entered
	 * @return a string containing the action, either buy or sell, the quantity, the abbreviation of the coin and the price of the coin 
	 * @return a string containing failed transaction if the strategy cannot be executed
	 */
	@Override
	public String executeStrategy(CryptoCoinList list) {
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
}
