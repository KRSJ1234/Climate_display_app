<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Weather Information App</title>
</head>
<body style = "background-color:CadetBlue">
	<h1 style ="font-size:60px;text-align:center;color:White;">Weather Information</h1>
	<nav style = "text-align:center">
		<a href = "index.jsp">Home</a> | <a href="about.jsp">About</a> | <a>Weather Info</a>
	</nav>
	<p style = "text-align:center;font-size:20px">Just enter your desired city's name below!!
	</p>
	<form style = "text-align:center" action = "coordinates" method = "post">
		<label for = "placename">Name of the city :</label>
		<input type ="text" id ="placename" name ="placename"></input>
		<input type="submit" value="Submit">
	</form>
	
	
</body>
</html>