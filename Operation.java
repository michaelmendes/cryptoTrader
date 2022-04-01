package cryptoTrader.utils;


import java.util.Arrays;

public class Operation {
		
	private String brokerName;
	private CryptoCoinList coins;
	private String tradingStrategy;
	private TradingBroker tradingBroker;
		
	public Operation(TradingBroker tradingBroker) {
		this.tradingBroker = tradingBroker;
		this.brokerName = tradingBroker.getName();
		this.tradingStrategy = tradingBroker.getStrategy();
		this.coins = tradingBroker.getCoinList();
		
	}
	public String getBrokerName() {
		return brokerName;
	}
	
	
	public TradingBroker getTradingBroker() {
		return tradingBroker;
	}
	
	public CryptoCoinList getCoins() {
		return coins;
	}
	
	public String getTradingStrategy() {
		return tradingStrategy;
	}
	
	
	public TradeActivity executeTrade() {
		TradeStrategy obj = new TradeStrategy(tradingStrategy, coins);
		String data = obj.performTrade();
		//String[] dataList = data.split(" ");
		String[] dataList = {"buy", "100", "bitcoin"};
		CryptoCoin coin = new CryptoCoin(dataList[2]);
		coin.setCoinPrice();
		TradeActivity activity = new TradeActivity(tradingBroker, dataList[0], dataList[1], coin, tradingStrategy);
		return activity;
	}
}
