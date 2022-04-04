package cryptoTrader.utils;
/**
 *Date: April 2, 2022 
 * @author David Burnett
 * This class uses the specified strategy which needs to be executed
 */
public class StrategyContext {
	/**
	 * A TradingStrategy interface variable
	 */
	private TradingStrategies tradeStrat;
	
	/**
	 * Public constructor initializing the TradingStrategy interface variable
	 * @param strat: A TradingStrategies interface variable
	 */
	public StrategyContext (TradingStrategies strat) {
		tradeStrat = strat;
	}
	/**
	 * Calls the correct strategy class to carry out the trade
	 * @param list: List of cryptoCoin objects
	 * @return a string which values depends on if the trade was successful or not
	 */
	public String doTrade(CryptoCoinList list) {
		return tradeStrat.executeStrategy(list);
	}
}
