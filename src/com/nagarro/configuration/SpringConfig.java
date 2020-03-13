package com.nagarro.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nagarro.dao.FileDao;
import com.nagarro.dao.FlightDao;
import com.nagarro.dao.TransactionDao;
import com.nagarro.dao.UserDao;
import com.nagarro.daoImpl.FileDaoImpl;
import com.nagarro.daoImpl.FlightDaoImpl;
import com.nagarro.daoImpl.TransactionDaoImpl;
import com.nagarro.daoImpl.UserDaoImpl;
import com.nagarro.services.DisplaySortService;
import com.nagarro.services.FlightComparatorService;
import com.nagarro.services.FlightSearchService;
import com.nagarro.services.GetFilesService;
import com.nagarro.services.GetLocationsService;
import com.nagarro.services.UserService;

@Configuration
@EnableScheduling
public class SpringConfig {
	
	@Bean
	public TransactionDao getTransactionDao() {
		return new TransactionDaoImpl();
	}

	@Bean
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}

	@Bean
	public FlightDao getFlightDao() {
		return new FlightDaoImpl();
	}

	@Bean
	public FlightSearchService getFlightSearch() {
		return new FlightSearchService();
	}

	@Bean
	public FileDao getFlightFileDao() {
		return new FileDaoImpl();
	}
	
//	
	@Bean
	public GetFilesService getGetFilesService() {
		return new GetFilesService();
	}

//
	@Bean
	public GetLocationsService getGetLocationsService() {
		return new GetLocationsService();
	}
	
	@Bean
	public DisplaySortService getOutputPreferance() {
		return new DisplaySortService();
	}
	
	@Bean
	public FlightComparatorService getFlightComparator() {
		return new FlightComparatorService();
	}
	
	@Bean
	public UserService getUserService() {
		return new UserService();
	}
}
