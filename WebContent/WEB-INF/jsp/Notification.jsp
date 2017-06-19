<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Notifications</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	
	<body>
		<center>
			<img src="../Images/bell.jpg"  width=25% height=25% style="float:left; ">
			<br>
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Notifications Page</h1>
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "getNotifications" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "Show" />
				<br><br>
				<table>
				<c:forEach items="${arr}" var="i">
					<tr>
						<c:set var="titleURL">
							<c:url value="/app/showPostPage">
								<c:param name="checkinID" value="${i.checkin}"/>  
							</c:url>  
						</c:set>
						<td>
							<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}">${i.from} ${i.description}</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</form>
		</center>
	</body>
</html>