<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="resources/index.css"/>
		<meta charset="ISO-8859-1">
		<title>Login</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script type="text/javascript">
		    $(function () {
		        $("#btnSubmit").click(function () {
		            var password = $("#txtPassword").val();
		            var confirmPassword = $("#txtConfirmPassword").val();
		            if (password != confirmPassword) {
		                alert("Passwords do not match.");
		                return false;
		            }
		            return true;
		        });
		    });
		</script>
	</head>
	<body>
		<div align="center"	class="container"">
			<div>
				<form action="Login" method="post"><br>
					<table>
						<tr>
							<td>Username :</td>
							<td><input type="text" placeholder = "Enter Username" name="userName" required></td>
						</tr>
						<tr>
							<td>Password : </td>
							<td><input type="password" placeholder="Enter Password" name="password" required><br></td>
						</tr>
						<tr>
							<th></th>
							<td align="right"><input type="submit" value="Login" align="right"></td>
						</tr>
					</table>
				</form>
			</div>
			<br>
			<br>
			<div>
				<form action="Signup" method="post"><br>
					<table>
						<tr>
							<td> Username :</td>
							<td><input type="text" placeholder = "Enter Username" name="userName" required></td>
						</tr>
						<tr>
							<td>Password : </td>
							<td><input type="password" placeholder = "Enter Password" id="txtPassword" name="password" required><br></td>
						</tr>
						<tr>
							<td>Confirm Password : </td>
							<td><input type="password" placeholder = "Enter Password" id="txtConfirmPassword" name="confirmPassword" required><br></td>
						</tr>
						<tr>
							<th></th>
							<td align="right"><input type="submit" id="btnSubmit" value="Sign Up" align="right" required></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>