<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nagarro.models.FlightModel" %>
<!DOCTYPE html>
<html>
	<head>
	<link rel="stylesheet" type="text/css" href="resources/result.css"/>
		<meta charset="ISO-8859-1">
		<title>Flights</title>
		</head>
	<body>
		<c:if test="${result.size() != 0 }">
			<header>
				<div>
					<table width=100%>
						<tr>
							<td>
								<h2 style="text-align:center">
									<c:out value="${result.size()}"></c:out> flight found
								</h2>
							</td>
							<td align="right">
								<form action="Logout">
									<input type="submit" value="Logout"/>
								</form>
							</td>
						</tr>
					</table>
				</div>
			</header>
			<table class="result">
				<tr>
					<th> Flight Number </th>
					<th> Departure Location </th>
					<th> Arrival Location </th>
					<th> Valid Till </th>
					<th> Flight Time </th>
					<th> Flight Duration </th>
					<th> Flight Fare </th>
					<th> Seat Availability </th>
					<th> Seat Class </th>
				</tr>
				<c:forEach items = "${result}" var="flight">
					<tr>
						<td>${flight.getFlightNumber()}</td>
						<td>${flight.getDepLocation()}</td>
	  					<td>${flight.getArrLocation()}</td>
	   					<td>${flight.getFlightDate()}</td>
	  					<td>${flight.getFlightTime()}</td>
	  					<td>${flight.getFlightDuration()}</td>
	  					<td>${flight.getFare()}</td>
	  					<td>${flight.getSeatAvailability()}</td>
	  					<td>${flight.getSeatClass()}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${result.size() == 0}">
			<header>
				<div>
					<table width=100%>
						<tr>
							<td>
								<h2 style="text-align:center">
									No flights found
								</h2>
							</td>
							<td align="right">
								<form action="Logout">
									<input type="submit" value="Logout"/>
								</form>
							</td>
						</tr>
					</table>
				</div>
			</header>
		</c:if>
	</body>
</html>