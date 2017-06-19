<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<title> FCISquare Post </title>
		<link rel="icon" href="../Images/icon.png">
	</head>

	<body>
		<center>
			
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Post Page</h1>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "getcheckin" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "View Post" />
				<p> ${checkinUser} checked in ${checkinPlace} on ${checkinDate} at ${checkinTime} <p>
				<p> Post: ${checkinPost} <p>
				</p>
			</form>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "likeCheckin" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "Like" />
			</form>
			<br> <br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "commentCheckin" method = "post">
				Comment: <input type="text" name = "comment" /> <br><br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "Comment" />
				<br> <br>
			</form>
			<c:set var="titleURL">
				<c:url value="/app/showLikePage">
					<c:param name="checkinID" value="${checkinID}"/>  
				</c:url>  
			</c:set>
			<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}">People who liked this post</a>
		</center>
	</body>

</html>
