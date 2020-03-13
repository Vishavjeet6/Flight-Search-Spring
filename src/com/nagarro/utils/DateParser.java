package com.nagarro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateParser {
	
	final static Logger LOG = Logger.getLogger(DateParser.class);
	private static SimpleDateFormat formatter;

	public static Date getFileDate(String flightDate) {
		LOG.info("Parsing date");
		Date date = null;
		try {
			formatter = DateFormat.FILEDATEFORMAT.getDateFormat();
			date = formatter.parse(flightDate);
		}catch(Exception e) {
			LOG.error("Error occured while parsing" + flightDate);
		}
		LOG.info("Parsing of "+ flightDate+" completed successfully");
		return date;
	}
	
	public static Date getUserDate(String flightDate) {
		LOG.info("Parsing date");
		Date date = null;
		try {
			formatter = DateFormat.USERDATEFORMAT.getDateFormat();
			date = formatter.parse(flightDate);
		}catch(Exception e) {
			LOG.error("Error occured while parsing" + flightDate);
		}
		LOG.info("Parsing of "+ flightDate+" completed successfully");
		return date;
	}

}
