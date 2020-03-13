package com.nagarro.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.models.FlightModel;
import com.nagarro.utils.UserPreference;

public class DisplaySortService {
	
	final static Logger LOG = Logger.getLogger(DisplaySortService.class); 
	
	@Autowired
	private FlightComparatorService flightComparator;

	public void sortFlights(List<FlightModel> searchResult, int sortBy) {
		LOG.info("Sorting search result");
		Comparator<FlightModel> comparator = null;
		
		if(sortBy == UserPreference.FIRST_PREFERENCE.getValue()) {
			comparator = flightComparator.sortByFare();
		}else if(sortBy == UserPreference.SECOND_PREFERENCE.getValue()) {
			comparator = flightComparator.sortByFareDuration();
		}
		Collections.sort(searchResult, comparator);
	}

}
