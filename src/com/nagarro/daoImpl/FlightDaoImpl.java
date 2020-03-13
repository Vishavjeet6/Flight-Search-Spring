package com.nagarro.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.dao.FlightDao;
import com.nagarro.dao.TransactionDao;
import com.nagarro.models.FlightModel;

public class FlightDaoImpl implements FlightDao {
	
	final static Logger LOG = Logger.getLogger(FlightDaoImpl.class);
	
	@Autowired
	private TransactionDao transactionDao;

	@Override
	public void addFlight(FlightModel flightModel) {
		LOG.info("Adding new flight " + flightModel.getFlightId());
		transactionDao.begin();
		transactionDao.getSession().save(flightModel);
		transactionDao.commit();
		transactionDao.close();
		LOG.info(flightModel.getFlightId() + " Successfully added");
	}

	@Override
	public List<FlightModel> getFlights(String depLocation, String arrLocation, Date flightDate, String seatClass) {
		LOG.info("Searching for flight according to user input");
		transactionDao.begin();
		
		CriteriaBuilder builder = transactionDao.getSession().getCriteriaBuilder();
		CriteriaQuery<FlightModel> criteria = builder.createQuery(FlightModel.class);
		Root<FlightModel> root = criteria.from(FlightModel.class);
	
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(depLocation != null) {
			predicates.add(builder.equal(root.get("depLocation"), depLocation));
		}
		if(arrLocation != null) {
			predicates.add(builder.equal(root.get("arrLocation"), arrLocation));
		}
		if(flightDate != null) {
			Path<Date> flightDatePath = root.get("flightDate");
			predicates.add(builder.greaterThanOrEqualTo(flightDatePath, flightDate));
		}
		if(seatClass != null) {
			ParameterExpression<String> expression = builder.parameter(String.class, "seatClass");
			predicates.add(builder.like(root.<String>get("seatClass"),"%" + seatClass + "%"));
		}
		predicates.add(builder.equal(root.get("seatAvailability"), 'Y'));

		criteria.select(root).where(predicates.toArray(new Predicate[] {}));
		criteria.select(root);
		List<FlightModel> searchResult = transactionDao.getSession().createQuery(criteria).getResultList();
		transactionDao.commit();
		transactionDao.close();
		return searchResult;
	}

	@Override
	public List<String> getDepLocations() {
		LOG.info("Retreiving departure locations");	
		transactionDao.begin();
		CriteriaBuilder builder = transactionDao.getSession().getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<FlightModel> root = criteria.from(FlightModel.class);
		criteria.multiselect(root.get("depLocation")).distinct(true);
		List<String> depLocations = transactionDao.getSession().createQuery(criteria).getResultList();
		return depLocations;
	}

	@Override
	public List<String> getArrLocations() {
		transactionDao.begin();
		CriteriaBuilder builder = transactionDao.getSession().getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<FlightModel> root = criteria.from(FlightModel.class);
		criteria.multiselect(root.get("arrLocation")).distinct(true);
		List<String> arrLocations = transactionDao.getSession().createQuery(criteria).getResultList();
		return arrLocations;
	}

}
