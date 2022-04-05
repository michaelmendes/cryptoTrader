package cryptoTrader.utils;

/**
 * 
 * Date: April 4, 2022
 * @author luke gabert
 * This class creates a TradeActivity object which holds details of an individual trade
 */


public class TradeActivity {

	private TradingBroker tradingBroker;
	private String action, quantity, strategy;
	private CryptoCoin coin;
	
	/**
	 * Public constructor which creates a TradeActivity object
	 * @param tradingBroker: The trading broker performing the trade
	 * @param action: This can be buy, sell, or failed trade, describing the action of the trade
	 * @param quantity: this is the amount of Cryptocoins being bought
	 * @param coin: This is the coin being bought or sold
	 * @param strategy: The trading strategy associated with this trade
	 */
	
	public TradeActivity(TradingBroker tradingBroker, String action, String quantity, CryptoCoin coin, String strategy) {
		this.tradingBroker = tradingBroker;
		this.action = action;
		this.quantity = quantity;
		this.coin = coin;
		this.strategy = strategy;
	}
	
	/**
	 * Constructor which only creates a TradeActivity object for a failed trade 
	 * @param tradingBroker: the broker who attempted the trade
	 * @param strategy: the strategy that the broker attempted to use
	 */
	
	public TradeActivity(TradingBroker tradingBroker, String strategy) {
		this.tradingBroker = tradingBroker;
		this.strategy = strategy;
		this.action = "failed trade";
		this.quantity = null;
		this.coin = new CryptoCoin(null);
	}
	
	/**
	 * Get the trading broker performing the trade
	 * @return the tradingBroker object
	 */
	
	public TradingBroker getTradingBroker() {
		return tradingBroker;
	}
	
	/**
	 * Set the Trading Broker of the trade
	 * @param tradingBroker: the new TradingBroker object
	 */
	
	public void setTradingBroker(TradingBroker tradingBroker) {
		this.tradingBroker = tradingBroker;
	}
	
	/**
	 * Get the action of the trade
	 * @return the action of the trade
	 */
	
	public String getAction() {
		return action;
	}

	/**
	 * Set the action of the trade
	 * @param action: the new action of the trade
	 */
	public void setAction(String action) {
		this.action = action;
	}


	/**
	 * Get the quantity of coin being bought/sold
	 * @return the quantity of coin being bought/sold
	 */
	
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Set the quantity of coin being bought/sold
	 * @param quantity: the new quantity of coin being bought/sold
	 */

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	/**
	 * Get the coin involved in the transaction
	 * @return the coin object involved in the transaction
	 */
	
	public CryptoCoin getCoin() {
		return coin;
	}

	/**
	 * Set the coin involved in the trade activity
	 * @param coin: the new coin involved in the trade
	 */

	public void setCoin(CryptoCoin coin) {
		this.coin = coin;
	}

	/**
	 * Get the trade strategy used in the trade
	 * @return the trade strategy used in the trade
	 */
	
	public String getStrategy() {
		return strategy;
	}
	
	/**
	 * Set the trading strategy being used
	 * @param strategy: the new trading strategy for the trade
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
}