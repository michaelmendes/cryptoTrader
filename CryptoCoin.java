package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CryptoCoin {

	private String coinName;
	private double coinPrice;
	private double coinMarketCap;
	private double coinVolume;
	private String strDate;
	private DataFetcher fetcher;
	
	public CryptoCoin(String name) {
		fetcher = new DataFetcher();
		
		coinName = name;
		coinPrice = 0.0;
		coinMarketCap = 0.0;
		coinVolume = 0.0;
		strDate= null;
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
		
		coinPrice = fetcher.getPriceForCoin(coinName, strDate);
	}
	
	public void setCoinMarketCap() {
		
		coinMarketCap = fetcher.getMarketCapForCoin(coinName, strDate);
	}
	
	public void setCoinVolume() {
		
		coinVolume = fetcher.getVolumeForCoin(coinName, strDate);
	}
	
	
	
}
