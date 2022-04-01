package cryptoTrader.utils;
import java.util.ArrayList;

public class TradingBroker {
	private String name;
	private CryptoCoinList cryptoList;
	private ArrayList<String> strategy;
	private int tradingBrokerID;
	
	public TradingBroker() {
		name = null;
		cryptoList = null;
		strategy = new ArrayList<String>();
		tradingBrokerID = 0;
	}
	
	public void setName(String brokerName) {
		name = brokerName;
	}
	
	public void setList(CryptoCoinList list) {
		cryptoList = list;
	}
	
	public void setStrategy(String newStrategy) {
		strategy.add(newStrategy);
	}
	
	public void failedStrategy() {
		strategy.set(strategy.size() - 1, "failed trade");
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
		return strategy.get(strategy.size() - 1);
	}
	
	public ArrayList<String> getStrategyList(){
		return strategy;
	}
	
	public int getTradingBrokerID() {
		return tradingBrokerID;
	}
}
