package com.nagarro.utils;

public enum UserPreference {
	FIRST_PREFERENCE(1),
	SECOND_PREFERENCE(2);
	
	private final int value;

	UserPreference(final int newValue) {
		value = newValue;
	}
	public int getValue() {
		return value;
	}
}
