package cryptoTrader.utils;

public class StrategyA implements TradingStrategies{
	@Override
	public String executeStrategy(String strategy, CryptoCoinList list) {
		System.out.println("We are here");
		if(list.searchCryptoCoinList("BTC") && list.searchCryptoCoinList("ETH") && list.getCryptoCoinList().length == 2) {
			CryptoCoin bitcoin = list.getCoin("BTC");
			double price = bitcoin.getCoinPrice();
			CryptoCoin etheruem = list.getCoin("ETH");
			double ethPrice = etheruem.getCoinPrice();
			if(price < 58000 && ethPrice > 4000) {
				return "buy 5 BTC " + price;
			} 
			else if(price > 58000 && ethPrice > 4000){
				return "sell 3 BTC " + price;
			}else {
				return "failed transaction";
			}
		}
		else {	
			return "failed transaction";
		}
	}
}
