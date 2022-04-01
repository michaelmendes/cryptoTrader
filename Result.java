package cryptoTrader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Result {
	
	public Result(ArrayList<TradeActivity> activities) {
		storeResults(activities); // store new activities in file 
		ArrayList<TradeActivity> updatedActivities = retrieveLog();
		
		DataVisualizationCreator creator = new DataVisualizationCreator();
		creator.createCharts(updatedActivities);
	}
	
	
	private void storeResults(ArrayList<TradeActivity> activities) {
		try {
			FileWriter writer = new FileWriter("TradeLog");
			Iterator<TradeActivity> iterator = activities.iterator();
			while (iterator.hasNext()) {
				TradeActivity activity = iterator.next();
				String line = activity.getTradingBroker().getName() + " " + activity.getTradingBroker().getCoinList().toString() 
						+ " " + activity.getStrategy() + " " + activity.getAction() + activity.getQuantity() + 
						activity.getCoin().getName() + " " + activity.getCoin().getCoinPrice() + "\n";
				writer.write(line);
			}
			writer.close();
			System.out.println("Successfully wrote to TradeLog.");
			} 
		catch (IOException e) {
			System.out.println("Could not open file.");
			e.printStackTrace();
			}
	}
	
	private ArrayList<TradeActivity> retrieveLog() {
		ArrayList<TradeActivity> updatedActs = new ArrayList<TradeActivity>();
		File file = new File("TradeLog");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] actList = line.split(" ");
				// store data as trade broker object 
				TradingBroker broker = makeBroker(actList[0], actList[1], actList[2]);
				CryptoCoin coin = broker.getCoinList().getCoin(actList[5]);
				TradeActivity activity = new TradeActivity(broker, actList[3], actList[4], coin);
				updatedActs.add(activity);
			}
		} 
		catch (FileNotFoundException e1) {
			System.out.println("Could not find trade log");
			e1.printStackTrace();
		}
		catch (IOException e2) {
			System.out.println("Could not open file.");
			e2.printStackTrace();
			}
		return updatedActs;
	}
	
	private TradingBroker makeBroker(String name, String coins, String strategy) {
		TradingBroker broker = new TradingBroker();
		broker.setName(name);
		// coins are currently formatted as a string separated by a comma 
		String[] list = coins.split(",");
		CryptocoinList cryptoList = new CryptocoinList(list);
		broker.setList(cryptoList);
		broker.setStrategy(strategy);
		return broker;
	}
}
