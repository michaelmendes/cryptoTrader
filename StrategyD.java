package cryptoTrader.utils;

/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This class implements the TradingStrategies interface and attempts to execute strategy D
 */
public class StrategyD implements TradingStrategies{
	/**
	 * Checks the necessary data to determine if the trade can be made
	 * @param list: a list of crypto coin objects the user entered
	 * @return a string containing the action, either buy or sell, the quantity, the abbreviation of the coin and the price of the coin 
	 * @return a string containing failed transaction if the strategy cannot be executed
	 */
	@Override
	public String executeStrategy(CryptoCoinList list) {
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
}
