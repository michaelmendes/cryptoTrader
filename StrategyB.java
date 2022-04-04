package cryptoTrader.utils;


public class StrategyB implements TradingStrategies{
	@Override
	public String executeStrategy(String strategy, CryptoCoinList list) {
		if(list.searchCryptoCoinList("DOGE") && list.getCryptoCoinList().length == 1) {
			CryptoCoin coin = list.getCoin("DOGE");
			double price = coin.getCoinPrice();
			if (price > 0.1) {
				return "buy 2000 DOGE " + price;
			} 
			else {
				return "failed transaction";
			}
		} 
		else {
			return "failed transaction";
		}
	}
}
