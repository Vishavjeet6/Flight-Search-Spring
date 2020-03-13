package com.nagarro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.models.FlightModel;
import com.nagarro.services.DisplaySortService;
import com.nagarro.services.FlightSearchService;

@Controller
public class FlightSearchController {
	
	@Autowired
	private FlightSearchService flightSearchService;
	
	@Autowired
	private DisplaySortService displaySortService; 
	
	@RequestMapping("/search")
	public ModelAndView searchFlights(@RequestParam String depLocation, @RequestParam String arrLocation,
			@RequestParam String flightDate, @RequestParam String seatClass,
			@RequestParam String sortFlight) {
		ModelAndView modelView = new ModelAndView();
		
		int sortBy = Integer.parseInt(sortFlight);
		List<FlightModel> searchResult = flightSearchService.getSearchResult(depLocation,
											arrLocation, 
											flightDate, 
											seatClass);
		displaySortService.sortFlights(searchResult, sortBy);
		
		modelView.addObject("result", searchResult);
		modelView.setViewName("result");
		return modelView;
	}
}
