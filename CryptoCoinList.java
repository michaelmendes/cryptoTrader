package cryptoTrader.utils;


public class CryptoCoinList {
	
	private CryptoCoin[] cryptoCoinList;
	
	public CryptoCoinList(String[] list) {
		
		cryptoCoinList = new CryptoCoin[list.length];
		for(int i = 0; i < list.length; i++) {
			CryptoCoin coin = new CryptoCoin(list[i]); 
			cryptoCoinList[i] = coin;
			cryptoCoinList[i].setDate();
			cryptoCoinList[i].setCoinPrice();
		}
	}
	
	public CryptoCoin[] getCryptoCoinList() {
		return cryptoCoinList;
	}
	
	public CryptoCoin getCoin(String coinName) {
		for (int i=0; i<cryptoCoinList.length; i++) {
			if (cryptoCoinList[i].getCoinName().equals(coinName)) {
				return cryptoCoinList[i];
			}
		}
		return null;
	}
	
	public boolean searchCryptoCoinList(String coinName) {
		
		for(int i = 0; i < cryptoCoinList.length; i++) {
			if(cryptoCoinList[i].getCoinName().equals(coinName)) {
				return true;
			}
		}		
		return false;
	}
	
	public String toString() {
		String sList = "";
		for (int i=0; i<cryptoCoinList.length; i++) {
			sList += cryptoCoinList[i].getCoinName() + ",";
		}
		return sList;
	}
}
