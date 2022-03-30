package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CryptoCoin {

	private String coinName;
	private double coinPrice;
	private double coinMarketCap;
	private double coinVolume;
	private DataFetcher fetcher;
	
	public CryptoCoin(String name) {
		fetcher = new DataFetcher();
		
		coinName = name;
		coinPrice = 0.0;
		coinMarketCap = 0.0;
		coinVolume = 0.0;
	}
	
	public double getCoinPrice() {
		return coinPrice;
	}
	
	public double getCoinMarketCap() {
		return coinMarketCap;
	}
	
	public double getCoinVolume() {
		return coinVolume;
	}
	
	public void setCoinPrice() {
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = formatter.format(date);
		
		coinPrice = fetcher.getPriceForCoin(coinName, strDate);
	}
	
	public void setCoinMarketCap() {
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = formatter.format(date);
		
		coinMarketCap = fetcher.getMarketCapForCoin(coinName, strDate);
	}
	
	public void setCoinVolume() {
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate = formatter.format(date);
		
		coinVolume = fetcher.getVolumeForCoin(coinName, strDate);
	}
	
	
	
}
