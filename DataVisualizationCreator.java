package cryptoTrader.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import cryptoTrader.gui.MainUI;

/**
 * Date: April 3, 2022
 * This class is used to display the results of the tradeActivities to each specified broker
 * @author Ethan Borg
 *
 */
public class DataVisualizationCreator {
	
	
	/**
	 * This method calls the other methods of the class with and ArrayList of 
	 * trade activities and ArrayList of trading brokers
	 * @param trade is the ArrayList of all the activites to be presented
	 * @param broker is the ArrayList of all the brokers that have performed trades
	 */
	public void createCharts(ArrayList<TradeActivity> trade, ArrayList<TradingBroker> broker) {
		createTableOutput(trade);
		createBar(broker);
	}

	
	/**
	 * This method is a helper method for the createTableOutput method as it will
	 * take an array and add it as a new row in the 2D array
	 * @param arr is a 2D array containing the data to be displayed in the chart
	 * @param rowNum is the row number that the array will be added to in the 2D array
	 * @param newRow is the array that will be added to the 2D array
	 * @return Object[][] which is the object containing the new 2D array with the 1D array added
	 */
	private Object[][] insertRow(Object[][] arr, int rowNum, Object[] newRow){
		Object[][] newArr = new Object[arr.length + 1][];		
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		newArr[rowNum] = newRow;
		return newArr;
	}
	
	/**
	 * This method creates the table output by creating a 2D array which will contain the data of the chart
	 * @param arr is the ArrayList containing the activities that will be presented in the table
	 */
	private void createTableOutput(ArrayList<TradeActivity> arr) {
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		Object[][] data = new Object[0][7];

		for(int i = 0; i < arr.size(); i++) {
			Object[] newRow = new Object[7];
			newRow[0] = arr.get(i).getTradingBroker().getName();
			newRow[1] = arr.get(i).getStrategy();
			newRow[2] = arr.get(i).getCoin().getCoinName();
			newRow[3] = arr.get(i).getAction();
			newRow[4] = arr.get(i).getQuantity();
			newRow[5] = arr.get(i).getCoin().getCoinPrice();
			newRow[6] = arr.get(i).getCoin().getDate();
			data = insertRow(data, i, newRow);
		}
		
		JTable table = new JTable(data, columnNames);
		//table.setPreferredSize(new Dimension(600, 300));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Trader Actions",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		
	
		
		scrollPane.setPreferredSize(new Dimension(800, 300));
		table.setFillsViewportHeight(true);;
		
		MainUI.getInstance().updateStats(scrollPane);
	}
	
	
	/**
	 * This is the method that will create a bar graph dipicting the 
	 * number of trades that each broker has made and for each strategy that they have used
	 * @param brokers is the ArrayList containing the brokers and the trades that they have made
	 */
	private void createBar(ArrayList<TradingBroker> brokers) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String[] strategies = {"Strategy-A", "Strategy-B", "Strategy-C", "Strategy-D"};
		
		for(int i = 0; i < brokers.size(); i++) {
			int[] trades = new int[4];
			for(int j = 0; j < brokers.get(i).getStrategyList().size(); j++) {
				if(brokers.get(i).getStrategyList().get(j).equals(strategies[0])) {
					trades[0] += 1;
				}else if(brokers.get(i).getStrategyList().get(j).equals(strategies[1])) {
					trades[1] += 1;
				}else if(brokers.get(i).getStrategyList().get(j).equals(strategies[2])) {
					trades[2] += 1;
				}else if(brokers.get(i).getStrategyList().get(j).equals(strategies[3])) {
					trades[3] += 1;
				}
			}
			for(int j = 0; j < 4; j++) {
				if(trades[j] > 0) {
					dataset.setValue(trades[j], brokers.get(i).getName(), strategies[j]);
				}
			}
		}

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(0.1, 20.0));
		plot.setRangeAxis(rangeAxis);

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Actions Performed By Traders So Far", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}

}