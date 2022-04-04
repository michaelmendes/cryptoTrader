package cryptoTrader.utils;


public class StrategyD implements TradingStrategies{
	@Override
	public String executeStrategy(String strategy, CryptoCoinList list) {
		if(list.searchCryptoCoinList("CAKE") && list.searchCryptoCoinList("SOL") && list.searchCryptoCoinList("XRP") && list.getCryptoCoinList().length == 3) {
			CryptoCoin cake = list.getCoin("CAKE");
			double price = cake.getCoinPrice();
			CryptoCoin sol = list.getCoin("SOL");
			double solPrice = sol.getCoinPrice();
			CryptoCoin xrp = list.getCoin("XRP");
			double xrpPrice = xrp.getCoinPrice();
			if (price < 15 && solPrice > 150 && xrpPrice > 1) {
				return "buy 100 CAKE " + price;
			}else if(price < 15 && solPrice > 150 && xrpPrice < 1) {
				return "buy 1000 XRP " +xrpPrice;
			}else if(price > 10 && solPrice > 180 && xrpPrice > 0.9) {
				return "sell 100,000 SOL " + solPrice;
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
