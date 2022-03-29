package cryptoTrader.gui;

import java.util.Arrays;

public class Operation {
		
	private String tradingBroker;
	private String [] coins;
	private String tradingStrategy;
		
	public Operation(String tradingBroker, String [] coins, String tradingStrategy) {
		this.tradingBroker = tradingBroker;	
		this.coins = coins;
		this.tradingStrategy = tradingStrategy;
		
	}
	
	public String getTradingBroker() {
		return tradingBroker;
	}
	
	public String[] getCoins() {
		return coins;
	}
	
	public String tradingStrategy() {
		return tradingStrategy;
	}
	
	
	public void print() {
		System.out.println(tradingBroker + " " + Arrays.toString(coins) + " " + tradingStrategy);
	}
	
}
