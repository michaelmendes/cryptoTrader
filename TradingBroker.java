package cryptoTrader.utils;
import java.util.ArrayList;


/**
 * Date: April 3, 2022
 * This class is the TradingBroker object which holds information about each 
 * trading broker that is used throughout the runtime of the program. The variables held are
 * the brokers name, the list of coins they are requesting, the strategy they wish to use, 
 * and the tradingBrokerID which is used to track if brokers make multiple trades
 * @author Ethan Borg and Michael Mendes
 *
 */
public class TradingBroker {
	private String name;
	private CryptoCoinList cryptoList;
	private ArrayList<String> strategy;
	private int tradingBrokerID;
	
	
	/**
	 * This constructor is the default constructor for this class which
	 * assigns the class variables particular values. The values are all nullified
	 */
	public TradingBroker() {
		name = null;
		cryptoList = null;
		strategy = new ArrayList<String>();
		tradingBrokerID = 0;
	}
	
	
	/**
	 * This method sets the name of the broker 
	 * @param brokerName is a String holding the name of the broker
	 */
	public void setName(String brokerName) {
		name = brokerName;
	}
	
	
	/**
	 * This method sets the list of cryptos the broker is currently using
	 * @param list is a CryptoCoinList object which holds the coins requested by the broker
	 */
	public void setList(CryptoCoinList list) {
		cryptoList = list;
	}
	
	
	/**
	 * This method sets the stratefy being used by the broker with a String variable
	 * @param newStrategy is a String containing the strategy the broker will use
	 */
	public void setStrategy(String newStrategy) {
		strategy.add(newStrategy);
	}
	
	
	/**
	 * This method is invoked when the strategy used suggest that the coins requested were rather
	 * invalid or no trade should be made by the broker based on the trends of the market
	 */
	public void failedStrategy() {
		strategy.set(strategy.size() - 1, "failed trade");
	}
	
	
	/**
	 * This method sets the id value of the broker with an integer
	 * @param ID is an int that holds the ID value of the broker
	 */
	public void setTradingBrokerID(int ID) {
		tradingBrokerID = ID;
	}
	
	
	/**
	 * This method is invoked when the broker will be used to invoke a trade by
	 * mainUI. An operation object is created to return a trade activity which will be suggested to the broker
	 * @return TradeActivity object that will hold information about the brokers trade
	 */
	public TradeActivity declareInterest() {
		Operation operationObj = new Operation(this);
		TradeActivity activity = operationObj.executeTrade();
		return activity;
	}
	
	
	/**
	 * This method will return the name of the broker
	 * @return A String containing the name of the broker
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * This method will return the list of cryptos being used by the broker
	 * @return CryptoCoinList object holding the coins requested by the broker
	 */
	public CryptoCoinList getCoinList() {
		return cryptoList;
	}
	
	
	/**
	 * This method returns the strategy used by the broker
	 * @return String containing the strategy used by the broker
	 */
	public String getStrategy() {
		return strategy.get(strategy.size() - 1);
	}
	
	
	/**
	 * This method will return the Array list holding all the strategies that the 
	 * broker has used throughout its trading history
	 * @return ArrayList<String> containing a bunch of Strings with the value of strategies previously used by the broker
	 */
	public ArrayList<String> getStrategyList(){
		return strategy;
	}
	
	
	/**
	 * This method returns the trading brokers ID 
	 * @return Int conatining the ID of the broker
	 */
	public int getTradingBrokerID() {
		return tradingBrokerID;
	}
	
	public void update() {
		System.out.println("Broker - Broker Name: " + name + " Coins: " + cryptoList + " Newest Strategy: " + getStrategy());
	}
}
