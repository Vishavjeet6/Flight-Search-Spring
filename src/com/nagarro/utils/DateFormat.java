package com.nagarro.utils;

import java.text.SimpleDateFormat;

public enum DateFormat {
	USERDATEFORMAT("yyyy-MM-dd"),
	FILEDATEFORMAT("dd-MM-yyyy");
	
	private final String DATEFORMAT;

	DateFormat(final String newValue) {
		DATEFORMAT = newValue;
	}
	public SimpleDateFormat getDateFormat() {
		return new SimpleDateFormat(DATEFORMAT);
	}
}
