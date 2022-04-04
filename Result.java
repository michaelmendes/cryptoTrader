package cryptoTrader.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Date: April 1, 2022
 * After the trades have been executed, this class takes all the information from the executed and 
 * failed trades, adds the data to a TradeLog document, recalls all trades that have occurred in 
 * the current session, and invokes DataVisualizationCreator to generate a table and bar graph. 
 * @author Michael Mendes
 *
 */
public class Result {
	
	/**
	 * This method takes in the list of Trade Activities and brokers that performed trades, stores
	 * the new data in a file, retrieves the log of all trades, and sends all the data to DataVisualizationCreator.
	 * @param activities is the list of TradeActivities that were performed 
	 * @param brokers is the list of trade brokers involved in the trades 
	 */
	public Result(ArrayList<TradeActivity> activities, ArrayList<TradingBroker> brokers) {
		storeResults(activities); // store new activities in file 
		ArrayList<TradeActivity> updatedActivities = retrieveLog();
		
		DataVisualizationCreator creator = new DataVisualizationCreator();
		creator.createCharts(updatedActivities, brokers);
	}
	
	
	/**
	 * This method stores the trade activities in a document. 
	 * @param activities is the list of trade activities 
	 */
	private void storeResults(ArrayList<TradeActivity> activities) {
		try {
			FileWriter file = new FileWriter("TradeLog");
			BufferedWriter writer = new BufferedWriter(file);
			Iterator<TradeActivity> iterator = activities.iterator();
			while (iterator.hasNext()) {
				TradeActivity activity = iterator.next();
				String line = activity.getTradingBroker().getName() + " " + activity.getTradingBroker().getCoinList().toString() 
						+ " " + activity.getStrategy() + " " + activity.getAction() + " " + activity.getQuantity() + " " +
						activity.getCoin().getCoinName() + " " + activity.getCoin().getCoinPrice() + "\n";
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
	
	/**
	 * This method retrieves the trade log of all trades and failed trades that have occured in
	 * the current session.
	 * @return returns the log as an ArrayList
	 */
	private ArrayList<TradeActivity> retrieveLog() {
		ArrayList<TradeActivity> updatedActs = new ArrayList<TradeActivity>();
		File file = new File("TradeLog");
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] activityList = line.split(" ");
				// store data as trade broker object 
				TradingBroker broker = makeBroker(activityList[0], activityList[1], activityList[2]);
				TradeActivity activity;
				if (activityList[3].equals("failed")) {
					activity = new TradeActivity(broker, broker.getStrategy());
				}
				else {
					CryptoCoin coin = broker.getCoinList().getCoin(activityList[5]);
					activity = new TradeActivity(broker, activityList[3], activityList[4], coin, broker.getStrategy());
				}
				
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
	
	/**
	 * This is a helper method to convert the String information from the trade log back into 
	 * a broker object for use in DataVisualizationCreator 
	 * @param name is the name of the broker
	 * @param coins is the list of crypto coins of interest 
	 * @param strategy is the trader's associated strategy 
	 * @return returns the recreated trade broker object 
	 */
	private TradingBroker makeBroker(String name, String coins, String strategy) {
		TradingBroker broker = new TradingBroker();
		broker.setName(name);
		// coins are currently formatted as a string separated by a comma 
		String[] list = coins.split(",");
		CryptoCoinList cryptoList = new CryptoCoinList(list);
		broker.setList(cryptoList);
		broker.setStrategy(strategy);
		return broker;
	}
}