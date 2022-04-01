package cryptoTrader.utils;

public class TradingBroker {
	private String name;
	private CryptoCoinList cryptoList;
	private String strategy;
	private int tradingBrokerID;
	
	public TradingBroker() {
		name = null;
		cryptoList = null;
		strategy = null;
		tradingBrokerID = 0;
	}
	
	public void setName(String brokerName) {
		name = brokerName;
	}
	
	public void setList(CryptoCoinList list) {
		cryptoList = list;
	}
	
	public void setStrategy(String newStrategy) {
		strategy = newStrategy;
	}
	
	public void setTradingBrokerID(int ID) {
		tradingBrokerID = ID;
	}
	
	public TradeActivity declareInterest() {
		Operation operationObj = new Operation(this);
		TradeActivity activity = operationObj.executeTrade();
		return activity;
	}
	
	public String getName() {
		return name;
	}
	
	public CryptoCoinList getCoinList() {
		return cryptoList;
	}
	
	
	public String getStrategy() {
		return strategy;
	}
	
	public int getTradingBrokerID() {
		return tradingBrokerID;
	}
}
