package cryptoTrader.utils;
/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This is the interface for TradingStrategies
 */
public interface TradingStrategies {
	/**
	 * This method is implemented by the concrete strategy classes to execute a trade
	 * @param list: A list of crypto coin objects
	 * @return a string which values depends on if the trade was successful or not
	 */
	public String executeStrategy(CryptoCoinList list);
}
