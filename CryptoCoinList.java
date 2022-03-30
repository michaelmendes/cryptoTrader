package cryptoTrader.utils;


public class CryptoCoinList {
	
	private CryptoCoin[] cryptoCoinList;
	
	public CryptoCoinList(String[] list) {
		
		cryptoCoinList = new CryptoCoin[list.length];
		for(int i = 0; i < list.length; i++) {
			CryptoCoin coin = new CryptoCoin(list[i]); 
			cryptoCoinList[i] = coin;
			cryptoCoinList[i].setCoinPrice();
			cryptoCoinList[i].setCoinMarketCap();
			cryptoCoinList[i].setCoinVolume();
		}
	}
	
	public CryptoCoin[] getCryptoCoinList() {
		return cryptoCoinList;
	}
}