<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<title>FCISquare login page</title>
		<link rel="icon" href="Images/icon.png">
	</head>
	
	<div
		style="background-color: white; padding: 25px; width: 1300px; height: 0px;">
		<br> <br> <br>
	</div>

	

	<body>
		<center>
		
			<img src="Images/login_key.jpg" style="float:left; ">
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">LOG IN Page</h1>
			<h2  align="center" style="color:#045FB4; font-family:Arial Unicode MS;">
				If you don't have an account you can SIGN UP from 
				<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;"	href="/FCISquareApp/app/signUp">here</a>
			</h2>

			<br> <br> 
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action="app/doLogin" method="post">
				<p align="center" style="padding: 50px;float:center;">
				Email:<input type="text" name="email" /> <br><br>  
				Password: <input type="password" name="pass" /> <br><br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;"  type="submit" value="Log in" />
				</p>
			</form>
			
		</center>
		
	</body>

</html>