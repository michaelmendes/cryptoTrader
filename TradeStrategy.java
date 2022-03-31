package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TradeStrategy {

	private String strategy;
	
	private CryptoCoinList list;
	
	private DataFetcher fetcher;
	
	private String strDate;
	
	ArrayList<String> availableCryptocoins;
	
	public TradeStrategy(String strategy, CryptoCoinList list) {
		
		fetcher = new DataFetcher();
		this.strategy = strategy;
		this.list = list;
	
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = formatter.format(date);
		
		this.strDate = strDate;
//		
//		AvailableCryptoList available = new AvailableCryptoList();
//		
//		availableCryptocoins = available.getAvailableCryptos();
//		
	}
	
	
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	public String performTrade() {
	
		if(strategy.equals("Strategy A")) {
			
//			if(!list.search("bitcoin") || !availableCryptocoins.contains("bitcoin")) {
//				return "failed transaction";
//			}else {
//				
//				double price = fetcher.getPriceForCoin("bitcoin", strDate);
//				
//				if(price < 500000) {
//					
//					return "buy 100 BTC";
//				
//				} else return "failed transaction";
//				
//			}
			
		} else if(strategy.equals("Strategy B")) {
			
//			if(!list.search("ethereum") || !availableCryptocoins.contains("ethereum")) {
//				return "failed transaction";
//			
//			} else {
//				
//				double price = fetcher.getPriceForCoin("ethereum", strDate);
//				
//				if (price > 4000) {
//					
//					return "sell 50 ETH";
//				
//				} else return "failed transaction";
//				
//			}
			
		} else if(strategy.equals("Strategy C")) {
			
//			if(!list.search("dogecoin") || !availableCryptocoins.contains("dogecoin")) {
//				return "failed transaction";
//			
//			} else {
//				
//				double price = fetcher.getPriceForCoin("Dogecoin", strDate);
//				
//				if (price > 1) {
//					
//					return "sell 400 DOGE";
//				
//				} else return "failed transaction";
//				
//			}
			 
		}  else if(strategy.equals("Strategy D")) {
			
//			if(!list.search("bitcoin") || !availableCryptocoins.contains("bitcoin")) {
//				return "failed transaction";
//			
//			} else {
//				
//				double price = fetcher.getPriceForCoin("bitcoin", strDate);
//				
//				if (price > 600000) {
//					
//					return "sell 100 BTC";
//				
//				} else return "failed transaction";
//				
//			}
//			
		}
		return "Hello";
		
	}
	
	
}