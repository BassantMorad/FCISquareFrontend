<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>FCISquare Sign up Page</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	
	<body>
		<center>
			<br>
			<img src="../Images/Signup.png" width=35% height=35%  style="float:left; size:200%">
			
			<br> <br> <br> <br> <br>
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">SIGN UP Page</h1>
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "doSignUp" method = "post">
				
				Name : <input type = "text" name = "name" /> <br> <br>
				Email : <input type = "text" name = "email" /> <br> <br>
				Password : <input type = "password" name = "pass" /> <br> <br>
				
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "Sign up" /> <br>
				
			</form>
		</center>
	</body>
</html>