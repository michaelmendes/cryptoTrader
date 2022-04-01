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
		
		coinPrice = fetcher.getPriceForCoin(convertInput(coinName), strDate);
	}
	
	public void setCoinMarketCap() {
		
		coinMarketCap = fetcher.getMarketCapForCoin(convertInput(coinName), strDate);
	}
	
	public void setCoinVolume() {
		
		coinVolume = fetcher.getVolumeForCoin(convertInput(coinName), strDate);
	}
	
	private String convertInput(String cName){
		String[][] conversions = {
				{"BTC", "Bitcoin"},{"ETH", "Ethereum"},{"USDT", "Tether"},{"BNB", "BNB"},{"USDC", "USD Coin"},{"SOL", "Solana"},{"XRP", "XRP"},{"ADA", "Cardano"},{"LUNA", "Terra"}, {"AVAX", "Avalanche"},
				{"DOT", "Polkadot"},{"DOGE", "Dogecoin"},{"BUSD", "Binance USD"},{"UST", "TerraUSD"},{"SHIB", "Shiba Inu"},{"WBTC", "Wrapped Bitcoin"},{"CRO", "Cronos"},{"MATIC", "Polygon"},{"STETH", "Lido Staked Ether"}, {"DAI", "Dai"},
				{"NEAR", "NEAR Protocol"},{"LTC", "Litecoin"},{"ATOM", "Cosmos Hub"},{"LINK", "Chainlink"},{"TRX", "Tron"},{"BCH", "Bitcoin Cash"},{"FTT", "FTX Token"},{"AXS", "Axie Infinity"},{"ETC", "Ethereum Classic"}, {"ALGO", "Algorand"},
				{"XLM", "Stellar"},{"WAVES", "Waves"},{"LEO", "LEO Token"},{"OKB", "OKB"},{"VET", "VeChain"},{"UNI", "Uniswap"},{"HBAR", "Hedera"},{"FIL", "Filecoin"},{"ICP", "Internet Computer"}, {"EGLD", "Elrond"},
				{"THETA", "Theta Network"},{"MANA", "Decentraland"},{"SAND", "The Sandbox"},{"XMR", "Monero"},{"FTM", "Fantom"},{"RUNE", "THORChain"},{"CETH", "cETH"},{"XTZ", "Tezos"},{"KLAY", "Klaytn"}, {"GRT", "The Graph"},
				{"AAVE", "Aave"},{"EOS", "EOS"},{"MIM", "Magic Internet Money"},{"OSMO", "Osmosis"},{"FRAX", "Frax"},{"ZIL", "Zilliqa"},{"FLOW", "Flow"},{"HNT", "Helium"},{"CAKE", "PancakeSwap"}, {"MIOTA", "IOTA"},
				{"DFI", "DeFiChain"},{"ZEC", "Zcash"},{"APE", "ApeCoin"},{"BTT", "BitTorrent"},{"GALA", "Gala"},{"CDAI", "cDai"},{"ONE", "Harmony"},{"CUSDC", "cUSDC"},{"NEO", "NEO"}, {"TFUEL", "Theta Fuel"},
				{"QNT", "Quant"},{"MKR", "Maker"},{"HBTC", "Huobi BTC"},{"AR", "Arweave"},{"BSV", "Bitcoin SV"},{"XEC", "eCash"},{"KSM", "Kusama"},{"ENJ", "Enjin Coin"},{"CVX", "Convex Finance"}, {"KCS", "KuCoin Token"},
				{"SNX", "Synthetix Network Token"},{"CHZ", "Chiliz"},{"XRD", "Radix"},{"STX", "Stacks"},{"LRC", "Loopring"},{"HT", "Huobi Token"},{"CEL", "Celsius Network"},{"CELO", "Celo"},{"GMT", "STEPN"}, {"TUSD", "TrueUSD"},
				{"DASH", "Dash"},{"NEXO", "Nexo"},{"BAT", "Basic Attention Token"},{"HEART", "Humans.ai"},{"AMP", "Amp"},{"FXS", "Frax Share"},{"JUNO", "JUNO"},{"MINA", "Mina Protocol"},{"BIT", "BitDAO"}, {"LDO", "Lido DAO"}

		};
		AvailableCryptoList availableCrypto = AvailableCryptoList.getInstance();
		for(int i = 0; i < conversions.length; i++) {
			if(cName.equals(conversions[i][0])) {
				return availableCrypto.getCryptoID(conversions[i][1]);
			}
		}
		return cName;
	}
	
	
	
}
