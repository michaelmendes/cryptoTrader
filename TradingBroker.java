package cryptoTrader.utils;

public class TradingBroker {
	private String tradingBroker;
	private int tradingBrokerID;
	
	public TradingBroker(String tradingBroker, int tradingBrokerID) {
		this.tradingBroker = tradingBroker;
		this.tradingBrokerID = tradingBrokerID;
	}
	
	
	public String getTradingBroker() {
		return tradingBroker;
	}
	
	
	public int getTradingBrokerID() {
		return tradingBrokerID;
	}
	
	
}
