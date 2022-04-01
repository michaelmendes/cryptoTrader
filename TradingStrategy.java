package cryptoTrader.utils;


public class TradingStrategy {

	private String strategy;	
	private CryptoCoinList list;		
	private String[] availableCryptocoins;
	
	public TradingStrategy(String strategy, CryptoCoinList list) {
		this.strategy = strategy;
		this.list = list;
		AvailableCryptoList available = AvailableCryptoList.getInstance();
		availableCryptocoins = available.getAvailableCryptos();
	}
	
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public String performTrade() {
		if (strategy.equals("Strategy-A")) {	
			if(!list.searchCryptoCoinList("BTC") || !contains(availableCryptocoins, "Bitcoin")) {
				return "failed transaction";
			}
			else {	
				CryptoCoin coin = list.getCoin("BTC");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				System.out.println(price);
				if(price < 60000) {
					return "buy 100 BTC " + price;
				} 
				else {
					return "failed transaction";
				}
			}
		}
		
		else if (strategy.equals("Strategy-B")) {
			if(!list.searchCryptoCoinList("ETH") || !contains(availableCryptocoins, "Ethereum")) {
				return "failed transaction";
			} 
			else {
				CryptoCoin coin = list.getCoin("ETH");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 4000) {
					return "sell 50 ETH " + price;
				} 
				else {
					return "failed transaction";
				}
			}
		} 
		
		else if (strategy.equals("Strategy-C")) {
			if(!list.searchCryptoCoinList("DOGE") || !contains(availableCryptocoins, "Dogecoin")) {
				return "failed transaction";
			} 
			else {
				CryptoCoin coin = list.getCoin("DOGE");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 1) {
					return "sell 400 DOGE " + price;
				} 
				else {
					return "failed transaction";
				}
			}
		}  
		
		else if (strategy.equals("Strategy-D")) {
			if(!list.searchCryptoCoinList("BTC") || !contains(availableCryptocoins, "Bitcoin")) {
				return "failed transaction";
			} 
			else {	
				CryptoCoin coin = list.getCoin("BTC");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 50000) {
					return "sell 100 BTC " + price;
				} 
				else {
					return "failed transaction";
				}
			}
		}
		
		else {
			return "invalid strategy";
		}
	}
	
	public boolean contains(String[] list, String key) {
		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(key))
				return true;
		}
		return false;
	}
}