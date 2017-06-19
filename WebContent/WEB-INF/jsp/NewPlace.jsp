<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>FCISquare add place</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	
	<body>
		<center>
			<img src="../Images/add.png" style="float:left; " width=30% height=30%>
			<br> <br>
			<h1 style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1 style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Add Place Page</h1>
			<br> <br>
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "addNewPlace" method = "post">
				
				Place name : <input type = "text" name = "name" /> <br> <br>
				Description : <input type = "text" name = "desc" /> <br> <br>
				Latitude : <input type = "text" name = "lat" /> <br> <br>
				Longitude : <input type = "text" name = "lon" /> <br> <br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3; " type = "submit" value = "Add" />
				
			</form>
		</center>
	</body>
</html>