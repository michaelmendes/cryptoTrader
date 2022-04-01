package cryptoTrader.utils;

public class TradeActivity {

	private TradingBroker tradingBroker;
	private String action, quantity, strategy;
	private CryptoCoin coin;
	
	public TradeActivity(TradingBroker tradingBroker, String action, String quantity, CryptoCoin coin) {
		this.tradingBroker = tradingBroker;
		this.action = action;
		this.quantity = quantity;
		this.coin = coin;
		strategy = tradingBroker.getStrategy();
	}
	
	public TradingBroker getTradingBroker() {
		return tradingBroker;
	}
	
	public void setTradingBroker(TradingBroker tradingBroker) {
		this.tradingBroker = tradingBroker;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public CryptoCoin getCoin() {
		return coin;
	}
	
	public void setCoin(CryptoCoin coin) {
		this.coin = coin;
	}
	
	public String getStrategy() {
		return strategy;
	}
	
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
}