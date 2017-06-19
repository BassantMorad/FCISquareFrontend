<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Actions history</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	<body>
		<center>
			<h1 style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1 style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Actions history Page</h1>
			<br><br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "getLikes" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3; " type = "submit" value = "Like History" />
				<table>
					<c:forEach items="${history1}" var="i">
						<tr>
								<c:set var="titleURL">
									<c:url value="/app/undoLike">
										<c:param name="checkinID" value="${i.checkin}"/>  
									</c:url>  
									</c:set>
								<td> You ${i.desc} <br>
								<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}"> undo </a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<br><br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "getComments" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3; " type = "submit" value = "Comment History" />
				<table>
					<c:forEach items="${history2}" var="i">
						<tr>
								<c:set var="titleURL">
									<c:url value="/app/undoComment">
										<c:param name="checkinID" value="${i.checkin}"/>  
									</c:url>  
									</c:set>
								<td> You ${i.desc} <br>
								<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}"> undo </a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<br> <br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "getCheckins" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3; " type = "submit" value = "Checkin History" />
				<table>
					<c:forEach items="${history3}" var="i">
						<tr>
								<c:set var="titleURL">
									<c:url value="/app/undoCheckin">
										<c:param name="historyPlace" value="${i.place}"/>  
										<c:param name="checkinID" value="${i.checkin}"/> 
									</c:url>  
									</c:set>
								<td> You ${i.desc} <br>
								<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}"> undo </a></td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<br><br>
			
		</center>
	</body>
</html>