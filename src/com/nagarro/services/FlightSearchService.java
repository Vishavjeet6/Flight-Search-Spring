package com.nagarro.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.FlightDao;
import com.nagarro.models.FlightModel;
import com.nagarro.utils.DateParser;


@Service
public class FlightSearchService {
	
	@Autowired
	private FlightDao flightDao;

	public List<FlightModel> getSearchResult(String depLocation, String arrLocation, String flightDate,
			String seatClass) {
		
		List<FlightModel> searchResult;
		Date parsedFlightDate = DateParser.getUserDate(flightDate);
		searchResult = flightDao.getFlights(depLocation, arrLocation, parsedFlightDate, seatClass);
		return searchResult;
	}

}
