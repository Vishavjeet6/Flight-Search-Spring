package com.nagarro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dao.FlightDao;


@Service
public class GetLocationsService {
	
	@Autowired
	private FlightDao  flightDao;

	public List<String> getDepLocations() {
		List<String> depLocations;
		depLocations = flightDao.getDepLocations();
		return depLocations;
	}

	public List<String> getArrLocations() {
		List<String> arrLocations;
		arrLocations = flightDao.getArrLocations();
		return arrLocations;
	}

}
