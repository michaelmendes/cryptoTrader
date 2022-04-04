package cryptoTrader.utils;
/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This class implements the TradingStrategies interface and attempts to execute strategy A
 */
public class StrategyA implements TradingStrategies{
	/**
	 * Checks the necessary data to determine if the trade can be made
	 * @param list: a list of crypto coin objects the user entered
	 * @return a string containing the action, either buy or sell, the quantity, the abbreviation of the coin and the price of the coin 
	 * @return a string containing failed transaction if the strategy cannot be executed
	 */
	@Override
	public String executeStrategy(CryptoCoinList list) {
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
}
