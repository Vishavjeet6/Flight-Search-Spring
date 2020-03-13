package com.nagarro.services;

import java.util.Comparator;

import org.apache.log4j.Logger;

import com.nagarro.models.FlightModel;

public class FlightComparatorService {
	
	final static Logger LOG = Logger.getLogger(FlightComparatorService.class);

	public Comparator<FlightModel> sortByFare() {
		LOG.info("Creating comparator to sort searh result according to fare");
		return(flight1, flight2) ->{
			return Double.compare(flight1.getFare(), flight2.getFare());
		};
	}

	public Comparator<FlightModel> sortByFareDuration() {
		LOG.info("Creating comparator to sort searh result according to fare and duration");
		return(flight1, flight2) -> {
			int comparison1 =  Double.compare(flight1.getFare(), flight2.getFare());
			if(comparison1 == 0) {
				int comparison2 = Double.compare(flight1.getFlightDuration(), flight2.getFlightDuration());
				return comparison2;
			}
			return comparison1;
		};
	}

}
