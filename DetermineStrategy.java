package cryptoTrader.utils;
/**
 * Date: April 2, 2022 
 * @author David Burnett
 * This class determines which strategy the broker wants to use
 */
public class DetermineStrategy {
	/**
	 * Determines the desired trading strategy and creates an object of the corresponding concrete class
	 * @param tradingStrategy: The desired trading strategy
	 * @return the new object of the corresponding concrete class
	 */
	public TradingStrategies determineStrat(String tradingStrategy) {
		if(tradingStrategy.equals("Strategy-A")) {
			return new StrategyA();
		}
		else if(tradingStrategy.equals("Strategy-B")) {
			return new StrategyB();
		}
		else if(tradingStrategy.equals("Strategy-C")) {
			return new StrategyC();
		}
		else if(tradingStrategy.equals("Strategy-D")) {
			return new StrategyD();
		}
		return null;
	}
}
