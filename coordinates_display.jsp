<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Weather Information App</title>
<style>
		#query{
			display: none;
		}
		#city{
			display: none;
		}
	</style>
</head>
<body style = "background-color:CadetBlue">
	<h1 style ="font-size:60px;text-align:center;color:White;">Weather Information</h1>
	<nav style = "text-align:center">
		<a href = "index.jsp">Home</a> | <a href="about.jsp">About</a> | <a>Weather Info</a>
	</nav>
	<p style = "text-align:center;font-size:34px;color:White;"><strong> Your city is <%= request.getAttribute("city") %></strong>
	</p>
	<p style = "text-align:center;font-size:28px;color:White;">Latitude of your city : <strong><%= request.getAttribute("latitude") %></strong>
	</p>
	<p style = "text-align:center;font-size:28px;color:White;">Longitude of your city : <strong><%= request.getAttribute("longitude") %></strong>
	</p>
	<form style = "text-align:center" action = "weather" method = "post" >
		<label for = "query">To find the current weather in your location:</label>
		<textarea id ="query" name ="query"><%= request.getAttribute("query") %></textarea>
		<textarea id ="city" name ="city"><%= request.getAttribute("city") %></textarea>
		<input type="submit" value="Click here!">
	</form>
	<p style = "text-align:center;color:Black;"> Not your desired city?<a href = "Weatherinfo.jsp">Click here to try again!</a>
	</p>
	
	
</body>
</html>