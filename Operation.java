package cryptoTrader.utils;


import java.util.Arrays;

/**
 * Date: April 3, 2022
 * This class will hold the operation object which stores each operations broker,
 * coin list, and trading strategy. With these variables the class has a method
 * which executes the trade by recieving the appropriate action made by trade strategy.
 * @author Ethan Borg and Michael Mendes
 * 
 */
public class Operation {
		
	private String brokerName;
	private CryptoCoinList coins;
	private String tradingStrategy;
	private TradingBroker tradingBroker;
		
	
	/**
	 * This constructor method will take in a trading broker object and distribute
	 * its contents into the other variables in the class
	 * @param tradingBroker is a TradingBroker object which stores information about each trading broker
	 */
	public Operation(TradingBroker tradingBroker) {
		this.tradingBroker = tradingBroker;
		this.brokerName = tradingBroker.getName();
		this.tradingStrategy = tradingBroker.getStrategy();
		this.coins = tradingBroker.getCoinList();
		
	}
	
	
	/**
	 * This method returns the value of the trading brokers name
	 * @return brokerName is the name of the broker as a String
	 */
	public String getBrokerName() {
		return brokerName;
	}
	
	/**
	 * This method returns the trading broker object assosiacted with the operation
	 * @return tradingBroker is the object for the trading broker
	 */
	public TradingBroker getTradingBroker() {
		return tradingBroker;
	}
	
	
	/**
	 * This method returns the coin list which holds all the used coins for each particular trade
	 * @return coins is the object for the CyrptoCoinList
	 */
	public CryptoCoinList getCoins() {
		return coins;
	}
	
	
	/**
	 * This method returns the trading strategy used for the operation
	 * @return tradingStrategy is the String for the strategy used 
	 */
	public String getTradingStrategy() {
		return tradingStrategy;
	}
	
	
	/**
	 * This method executes a trade by calling the tradingStrategy class
	 * to obtain the action to take based on the coins price value. This information
	 * is then used to make a tradeActivity object which is used to track each trade
	 * @return the TradeActivity object which holds the information for one trade
	 */
	public TradeActivity executeTrade() {
		TradingStrategy obj = new TradingStrategy(tradingStrategy, coins);
		String data = obj.performTrade();
		System.out.println(data);
		String[] dataList = data.split(" ");
		if(dataList[0].equals("failed")) {
			TradeActivity activity = new TradeActivity(tradingBroker, tradingStrategy);
			return activity;
		}else {
			TradeActivity activity = new TradeActivity(tradingBroker, dataList[0], dataList[1], coins.getCoin(dataList[2]), tradingStrategy);
			return activity;
		}
	}
}