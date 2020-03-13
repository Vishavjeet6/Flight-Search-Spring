<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/home.css"/>
		<meta charset="ISO-8859-1">
		<title>Welcome</title>
	</head>
	<body>
		<c:if test = "${userName != null}">
		<header>
			<div>
				<table width = "100%">
					<tr>
						<td>
							<h2 style="text-align:center">Welcome <c:out value = "${userName}"/> fill below details to 
								search flights.</h2>
						</td>
						<td align="right">
							<form action="Logout">
								<input type="submit" value = "Log Out"/>
							</form>
						</td>
					</tr>
				</table>
			</div>
			<div align="center" class="container">
				<form action="search" method="post">
					<table>
						<tr>
							<td>From :</td>
							<td>
								<select name="depLocation">
									<c:forEach items = "${depLocations}" var="depLocation">
										<option value = "${depLocation}">${depLocation}</option>
									</c:forEach>	
								</select>
							</td>
						</tr>
						<tr>
							<td>To :</td>
							<td>
								<select name="arrLocation">
									<c:forEach items = "${arrLocations}" var="arrLocation">
										<option value = "${arrLocation}">${arrLocation}</option>
									</c:forEach>	
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Date :
							</td>
							<td>
								<input type="date" name="flightDate"/>
							</td>
						</tr>
						<tr>
							<td>
								Flight Class :
							</td>
							<td>
								<select name = "seatClass">
									<option value="E">Economy</option>
									<option value="B">Business</option>	
								</select>
							</td>
						</tr>
						<tr>
							<td>
								Sort By :
							</td>
							<td>
								<select name="sortFlight">
								<option value="1">Fare</option>
								<option value="2">Fare And Duration</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<input type="submit" value="Search Flight"/>	
							</td>	
						</tr>
					</table>
				</form>
			</div>
		</header>
	</c:if>
	<c:if test="${userName == null}">
		<c:redirect url ="index.jsp"/>
	</c:if>
	</body>
</html>