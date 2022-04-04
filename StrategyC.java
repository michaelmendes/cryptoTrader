package cryptoTrader.utils;


public class StrategyC implements TradingStrategies{
	@Override
	public String executeStrategy(String strategy, CryptoCoinList list) {
		if(list.searchCryptoCoinList("ADA") && list.getCryptoCoinList().length == 1) {
			CryptoCoin coin = list.getCoin("ADA");
			double price = coin.getCoinPrice();
			if (price > 1) {
				double quant = 500 / price;
				return "buy " +  Math.round(quant * 100.0)/100 + " ADA " + price;
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
