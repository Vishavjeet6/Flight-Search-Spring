package com.nagarro.dao;

import java.util.Date;
import java.util.List;

import com.nagarro.models.FlightModel;

public interface FlightDao {
	
	void addFlight(FlightModel flightModel);
	List<FlightModel> getFlights(String depLocation, String arrLocation, Date flightDate, String flightClass);
	List<String> getDepLocations();
	List<String> getArrLocations();
}
