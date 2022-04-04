package cryptoTrader.utils;

public class DetermineStrategy {
	public TradingStrategies determineStrat(String tradingStrategy) {
		if(tradingStrategy.equals("Strategy-A")) {
			return new StrategyA();
		}
		else if(tradingStrategy.equals("Strategy-A")) {
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
