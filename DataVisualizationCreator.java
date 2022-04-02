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
	
	public void createCharts(ArrayList<TradeActivity> trade, ArrayList<TradingBroker> broker) {
		createTableOutput(trade);
		createBar(broker);
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
			newRow[0] = arr.get(i).getTradingBroker().getName();
			newRow[1] = arr.get(i).getStrategy();
			newRow[2] = arr.get(i).getCoin().getCoinName();
			newRow[3] = arr.get(i).getAction();
			newRow[4] = arr.get(i).getQuantity();
			newRow[5] = arr.get(i).getCoin().getCoinPrice();
			arr.get(i).getCoin().setDate();
			newRow[6] = arr.get(i).getCoin().getDate();
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
	
	
	private void createBar(ArrayList<TradingBroker> brokers) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		Those are hard-coded values!!!! 
//		You will have to come up with a proper datastructure to populate the BarChart with live data!
		//ArrayList<TradingBroker> brokerID = new ArrayList<TradingBroker>();
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
//			if(flag) {
//				TradingBroker broker = new TradingBroker();
//				broker.setTradingBrokerID(trade.get(i).getTradingBroker().getTradingBrokerID());
//				broker.setStrategy(trade.get(i).getTradingBroker().getStrategy());
//				broker.setName(trade.get(i).getTradingBroker().getName());
//				brokerID.add(broker);
//				numOfTrades.add(1);
//			}	


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
