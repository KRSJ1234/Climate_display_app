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
	<p style = "text-align:center;font-size:25px;color:black">The current weather in <%= request.getAttribute("city") %> is<br></p>
	<p style = "text-align:center;font-size:30px;color:white"><strong>
	<%= request.getAttribute("description") %><br>
	<img src="http://openweathermap.org/img/wn/<%= request.getAttribute("icon") %>@2x.png"><br>
	<%= request.getAttribute("temperature") %>°C<br>
	
	<p style = "text-align:center;font-size:25px;color:black"><a href = "Weatherinfo.jsp">Click here</a> to enter another city</p>
</body>
</html>