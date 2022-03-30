package cryptoTrader.gui;

public class TradingBroker {
	private String tradingBroker;
	private String[] coins;
	private String strategy;
	
	public TradingBroker(String tradingBroker, String [] coins, String strategy) {
		this.tradingBroker = tradingBroker;
		this.coins = coins;
		this.strategy = strategy;
	}
	
	
	public String getTradingBroker() {
		return tradingBroker;
	}
	
	
	public String[] getCoins() {
		return coins;
	}
	
	
	public String getStrategy() {
		return strategy;
	}
}
