package com.nagarro.models;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nagarro.utils.DateParser;

@Entity
@Table(name = "Flight_Model")
public class FlightModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flightId", nullable=false)
	private int flightId;
	private String flightNumber;
	private String depLocation;
	private String arrLocation;
//	private Date flightDate;
	private java.sql.Date flightDate;
	private int flightTime;
	private double flightDuration;
	private double fare;
	private char seatAvailability;
	private String seatClass;
	
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepLocation() {
		return depLocation;
	}
	public void setDepLocation(String depLocation) {
		this.depLocation = depLocation;
	}
	public String getArrLocation() {
		return arrLocation;
	}
	public void setArrLocation(String arrLocation) {
		this.arrLocation = arrLocation;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
//		this.flightDate = DateParser.getFileDate(flightDate);
		this.flightDate = new java.sql.Date(DateParser.getFileDate(flightDate).getTime());
	}
//	public void setFlightDate(String flightDate) {
//		Date date = null;
//		try {
//			date = new SimpleDateFormat("dd-MM-yyyy").parse(flightDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		this.flightDate = new java.sql.Date(date.getTime());
//	}
//	public Date getFlightDate() {
//		return flightDate;
//	}
	
	public int getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}
	public double getFlightDuration() {
		return flightDuration;
	}
	public void setFlightDuration(double flightDuration) {
		this.flightDuration = flightDuration;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public char getSeatAvailability() {
		return seatAvailability;
	}
	public void setSeatAvailability(char seatAvailability) {
		this.seatAvailability = seatAvailability;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	
	@Override
	public String toString() {
		String flightDetails = "";
		flightDetails = "[ " 
				+ this.getFlightNumber() + " | "  
				+ this.getDepLocation() + " | "  
				+ this.getArrLocation() + " | "  
				+ this.getFlightDate() + " | "  
				+ this.getFlightTime() + " | "  
				+ this.getFlightDuration() + " | " 
				+ this.getFare() + " | "
				+ this.getSeatAvailability() + " | "  
				+ this.getSeatClass()
				+ " ]";
		return flightDetails;
	}
	
}
