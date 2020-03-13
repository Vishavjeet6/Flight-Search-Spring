package com.nagarro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.services.GetLocationsService;
import com.nagarro.services.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GetLocationsService getLocations;
	
	@RequestMapping("/Login")
	public ModelAndView login(@RequestParam String userName, @RequestParam String password) {
		ModelAndView modelView = new ModelAndView();	
		if(!userService.validateUser(userName, password)) {
			modelView.setViewName("index");
			return modelView;
		}
		
		List<String> depLocations = getLocations.getDepLocations();
		List<String> arrLocations = getLocations.getArrLocations();
		
		modelView.addObject("depLocations", depLocations);
		modelView.addObject("arrLocations", arrLocations);
		modelView.addObject("userName", userName);
		modelView.setViewName("home");
		return modelView;
	}
	
	
	@RequestMapping("/Signup")
	public ModelAndView signUp(@RequestParam String userName, @RequestParam String password) {
		ModelAndView modelView = new ModelAndView();
		userService.addUser(userName, password);	
		
		List<String> depLocations = getLocations.getDepLocations();
		List<String> arrLocations = getLocations.getArrLocations();
		
		modelView.addObject("depLocations", depLocations);
		modelView.addObject("arrLocation", arrLocations);	
		modelView.addObject("userName", userName);
		modelView.setViewName("home");
		return modelView;
	}
	
	@RequestMapping("/Logout")
	public ModelAndView logOut() {
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("userName", null);
		modelView.setViewName("index");
		return modelView;
	}
}
