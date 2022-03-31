package cryptoTrader.utils;


import java.util.Arrays;

public class Operation {
		
	private TradingBroker tradingBroker;
	private CryptoCoinList coins;
	private String tradingStrategy;
		
	public Operation(String tradingBroker, int tradingBrokerID, String[] coinList, String tradingStrategy) {
		this.tradingBroker = new TradingBroker(tradingBroker, tradingBrokerID);	
		coins = new CryptoCoinList(coinList);
		this.tradingStrategy = tradingStrategy;
		
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
		CryptoCoin[] list = coins.getCryptoCoinList();
		String[] dataList = {"buy", "100", "bitcoin"};
		CryptoCoin coin = new CryptoCoin(dataList[2]);
		TradeActivity activity = new TradeActivity(tradingBroker, dataList[0], dataList[1], coin, tradingStrategy);
		return activity;
	}
}
