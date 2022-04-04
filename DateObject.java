package cryptoTrader.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Date: April 2, 2022
 * @author David Burnett
 * This class creates one instance of the current date to be used to fetch coin prices, uses the singleton design pattern
 */
public class DateObject {
	/**
	 * Static variable of type DateObject
	 */
	private static DateObject dateInstance = null;
	/**
	 * String variable which stores the date
	 */
	public String strDate;
	/**
	 * Private constructor so that the class cannot be instantiated 
	 */
	private DateObject() {
		/**
		 * The following code block is from https://www.javatpoint.com/java-simpledateformat 
		 * which retrieves the current date and formats it as specified
		 */
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		strDate = formatter.format(date);
	}
	/**
	 * Gets the instance of the class
	 * @return the instance of the class
	 */
	public static DateObject getInstance() {
		if(dateInstance == null) {
			dateInstance = new DateObject();
		}
		return dateInstance;
	}
}
