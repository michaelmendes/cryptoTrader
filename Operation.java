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
		TradingStrategy obj = new TradingStrategy(tradingStrategy, coins);
		String data = obj.performTrade();
		System.out.println(data);
		String[] dataList = data.split(" ");
		if(dataList[0].equals("failed")) {
			TradeActivity activity = new TradeActivity(tradingBroker, tradingStrategy);
			return activity;
		}else {
			CryptoCoin coin = new CryptoCoin(dataList[2]);
			coin.fetchCoinPrice();
			TradeActivity activity = new TradeActivity(tradingBroker, dataList[0], dataList[1], coin, tradingStrategy);
			return activity;
		}
	}
}
