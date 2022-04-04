package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CryptoCoin {

	private String coinName;
	private double coinPrice;
	private String strDate;
	private DataFetcher fetcher;
	
	public CryptoCoin(String name) {
		fetcher = new DataFetcher();
		
		coinName = name;
		coinPrice = 0.0;
		strDate= null;
	}
	
	public double getCoinPrice() {
		return coinPrice;
	}
	
	public String getCoinName() {
		return coinName;
	}
	
	public String getDate() {
		return strDate;
	}
	
	public void setDate() {
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		strDate = formatter.format(date);
	}
	
	public void setCoinPrice() {
		coinPrice = fetcher.getPriceForCoin(convertInput(coinName), strDate);
		coinPrice = Math.round( coinPrice * 100.0)/100.0;
	}
	
	private String convertInput(String cName){
		String[][] conversions = {
				{"BTC", "Bitcoin"},{"ETH", "Ethereum"},{"XRP", "XRP"},
				{"ADA", "Cardano"},{"DOGE", "Dogecoin"},{"SOL", "Solana"},{"CAKE", "PancakeSwap"}};
		AvailableCryptoList availableCrypto = AvailableCryptoList.getInstance();
		for(int i = 0; i < conversions.length; i++) {
			if(cName.equals(conversions[i][0])) {
				return availableCrypto.getCryptoID(conversions[i][1]);
			}
		}
		return cName;
	}
	
	
	
}