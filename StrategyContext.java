package cryptoTrader.utils;

public class StrategyContext {
	private TradingStrategies tradeStrat;
	
	public StrategyContext (TradingStrategies strat) {
		tradeStrat = strat;
	}
	
	public String doTrade(String strategy, CryptoCoinList list) {
		return tradeStrat.executeStrategy(strategy, list);
	}
}
