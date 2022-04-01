package cryptoTrader.utils;


public class TradingStrategy {

	private String strategy;	
	private CryptocoinList list;		
	private String[] availableCryptocoins;
	
	public TradingStrategy(String strategy, CryptocoinList list) {
		this.strategy = strategy;
		this.list = list;
		AvailableCryptoList available = new AvailableCryptoList();
		availableCryptocoins = available.getAvailableCryptos();
	}
	
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public String performTrade() {
		if (strategy.equals("Strategy-A")) {	
			if(!list.searchCryptoCoinList("bitcoin") || !contains(availableCryptocoins, "bitcoin")) {
				return "failed transaction";
			}
			else {	
				CryptoCoin coin = list.getCoin("bitcoin");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if(price < 500000) {
					return "buy 100 BTC" + price;
				} 
				else {
					return "failed transaction";
				}
			}
		}
		
		else if (strategy.equals("Strategy-B")) {
			if(!list.searchCryptoCoinList("ethereum") || !contains(availableCryptocoins, "ethereum")) {
				return "failed transaction";
			} 
			else {
				CryptoCoin coin = list.getCoin("ethereum");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 4000) {
					return "sell 50 ETH" + price;
				} 
				else {
					return "failed transaction";
				}
			}
		} 
		
		else if (strategy.equals("Strategy-C")) {
			if(!list.searchCryptoCoinList("dogecoin") || !contains(availableCryptocoins, "dogecoin")) {
				return "failed transaction";
			} 
			else {
				CryptoCoin coin = list.getCoin("Dogecoin");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 1) {
					return "sell 400 DOGE" + price;
				} 
				else {
					return "failed transaction";
				}
			}
		}  
		
		else if (strategy.equals("Strategy-D")) {
			if(!list.searchCryptoCoinList("bitcoin") || !contains(availableCryptocoins, "bitcoin")) {
				return "failed transaction";
			} 
			else {	
				CryptoCoin coin = list.getCoin("bitcoin");
				coin.fetchCoinPrice();
				double price = coin.getCoinPrice();
				if (price > 600000) {
					return "sell 100 BTC" + price;
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