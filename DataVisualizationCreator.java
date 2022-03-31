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

public class DataVisualizationCreator {
	
	public void createCharts(ArrayList<TradeActivity> trade) {
//		createTextualOutput();
		createTableOutput(trade);
//		createTimeSeries();
//		createScatter();
		createBar(trade);
	}

	private void createTextualOutput() {
//		DefaultTableModel dtm = new  DefaultTableModel(new Object[] {"Broker Name", "Ticker List", "Strategy Name"}, 1);
//		JTable table = new JTable(dtm);
//		//table.setPreferredSize(new Dimension(600, 300));
//		dtm.e
//		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
//                "Broker Actions",
//                TitledBorder.CENTER,
//                TitledBorder.TOP));
//		
//	
//		
//		scrollPane.setPreferredSize(new Dimension(800, 300));
//		table.setFillsViewportHeight(true);;
		
//		MainUI.getInstance().updateStats(scrollPane);
	}
	
	private Object[][] insertRow(Object[][] arr, int rowNum, Object[] newRow){
		Object[][] newArr = new Object[arr.length + 1][];		
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		newArr[rowNum] = newRow;
		return newArr;
	}
	
	
	private void createTableOutput(ArrayList<TradeActivity> arr) {
		// Dummy dates for demo purposes. These should come from selection menu
		Object[] columnNames = {"Trader","Strategy","CryptoCoin","Action","Quantity","Price","Date"};
		Object[][] data = new Object[0][7];

		for(int i = 0; i < arr.size(); i++) {
			Object[] newRow = new Object[7];
			newRow[0] = arr.get(i).getTradingBroker().getTradingBroker();
			newRow[1] = arr.get(i).getStrategy();
			newRow[2] = arr.get(i).getCoin().getCoinName();
			newRow[3] = arr.get(i).getAction();
			newRow[4] = arr.get(i).getQuantity().toString();
			newRow[5] = arr.get(i).getCoin().getCoinPrice();
			newRow[6] = "31-March-2022";
			data = insertRow(data, i, newRow);
		}
		
		// Dummy data for demo purposes. These should come from actual fetcher
//		Object [][] data = {
//				{"Trader-1", "Strategy-A", "ETH", "Buy", "500", "150.3","13-January-2022"},
//				{"Trader-2", "Strategy-B", "BTC", "Sell", "200", "50.2","13-January-2022"},
//				{"Trader-3", "Strategy-C", "USDT", "Buy", "1000", "2.59","15-January-2022"},
//				{"Trader-1", "Strategy-A", "USDC", "Buy", "500", "150.3","16-January-2022"},
//				{"Trader-2", "Strategy-B", "ADA", "Sell", "200", "50.2","16-January-2022"},
//				{"Trader-3", "Strategy-C", "SOL", "Buy", "1000", "2.59","17-January-2022"},
//				{"Trader-1", "Strategy-A", "ONE", "Buy", "500", "150.3","17-January-2022"},
//				{"Trader-2", "Strategy-B", "MANA", "Sell", "200", "50.2","17-January-2022"},
//				{"Trader-3", "Strategy-C", "AVAX", "Buy", "1000", "2.59","19-January-2022"},
//				{"Trader-1", "Strategy-A", "LUNA", "Buy", "500", "150.3","19-January-2022"},
//				{"Trader-2", "Strategy-B", "FTM", "Sell", "200", "50.2","19-January-2022"},
//				{"Trader-3", "Strategy-C", "HNT", "Buy", "1000", "2.59","20-January-2022"}
//			};
//	

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

	private void createTimeSeries() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		
		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		//plot.mapDatasetToRangeAxis(2, 2);// 3rd dataset to 3rd y-axis
		
		JFreeChart chart = new JFreeChart("Daily Price Line Chart", new Font("Serif", java.awt.Font.BOLD, 18), plot,
				true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		
		MainUI.getInstance().updateStats(chartPanel);
	}
	
	private void createScatter() {
		TimeSeries series1 = new TimeSeries("Bitcoin - Daily");
		series1.add(new Day(13, 9, 2021), 50368.67);
		series1.add(new Day(14, 9, 2021), 51552.05);
		series1.add(new Day(15, 9, 2021), 47228.30);
		series1.add(new Day(16, 9, 2021), 45263.90);
		series1.add(new Day(17, 9, 2021), 46955.41);
		
		TimeSeries series2 = new TimeSeries("Ethereum - Daily");
		series2.add(new Day(13, 9, 2021), 3912.28);
		series2.add(new Day(14, 9, 2021), 3927.27);
		series2.add(new Day(15, 9, 2021), 3460.48);
		series2.add(new Day(16, 9, 2021), 3486.09);
		series2.add(new Day(17, 9, 2021), 3550.29);

		TimeSeries series3 = new TimeSeries("Cardano - Daily");
		series3.add(new Day(13, 9, 2021), 2.87);
		series3.add(new Day(14, 9, 2021), 2.84);
		series3.add(new Day(15, 9, 2021), 2.41);
		series3.add(new Day(16, 9, 2021), 2.43);
		series3.add(new Day(17, 9, 2021), 2.59);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new LogAxis("Price(USD)"));

		//plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		//plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		
		JFreeChart scatterChart = new JFreeChart("Daily Price Scatter Chart",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(600, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		chartPanel.setBackground(Color.white);
		MainUI.getInstance().updateStats(chartPanel);
	}
	
	private void createBar(ArrayList<TradeActivity> trade) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		ArrayList<TradingBroker> brokerID = new ArrayList<TradingBroker>();
		ArrayList<Integer> numOfTrades = new ArrayList<Integer>();
		for(int i = 0; i < trade.size(); i++) {
			boolean flag = true;
			for(int j = 0; j < brokerID.size(); j++) {
				if(trade.get(i).getTradingBroker().getTradingBrokerID() == brokerID.get(j).getTradingBrokerID()) {
					numOfTrades.set(j, numOfTrades.get(j) + 1);
					j = brokerID.size();
					flag = false;
				}
			}
			if(flag) {
				TradingBroker broker = new TradingBroker(trade.get(i).getTradingBroker().getTradingBroker(), trade.get(i).getTradingBroker().getTradingBrokerID());
				brokerID.add(broker);
				numOfTrades.add(1);
			}	
		}
		for(int i = 0; i < brokerID.size(); i++) {
			dataset.setValue(numOfTrades.get(i), brokerID.get(i).getTradingBroker(), trade.get(i).getTradingBroker().getTradingBroker());
		}

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Strategy");
		plot.setDomainAxis(domainAxis);
		LogAxis rangeAxis = new LogAxis("Actions(Buys or Sells)");
		rangeAxis.setRange(new Range(1.0, 20.0));
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
