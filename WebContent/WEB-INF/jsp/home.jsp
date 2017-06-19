<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=windows-1256">
		<title>Insert title here</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	
	<body>
		<center>
			<h1 align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome ${it.name}</h1>
			<h1 align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA" >This is your home page</h1>
			<p align="center" style=" color:#045FB4; font-family:Arial Unicode MS;">
				You can show your current position on map and update your position
				from <a href="/FCISquareApp/app/showLocation"> here</a> <br>
				<c:set var="titleURL">
					<c:url value="/app/showHistoryPage">
						<c:param name="email" value="${it.email}" />
					</c:url>
				</c:set>
			</p>	
		
			<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="/FCISquareApp/app/showNewPlacePage"> Add a new place </a> <br>
			<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="/FCISquareApp/app/showNotificationsPage"> My notifications</a> <br>
			<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="/FCISquareApp/app/showFriendsPage"> My friends list </a> <br>
			<a style="font-family:Arial Unicode MS; font-size:150%; color:#BFFF00;" href="${titleURL}"> My history </a> <br> <br>
		
			<form align="center" style=" color:#045FB4; font-family:Arial Unicode MS;" action="getMyLastLocation" method="post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" 
				type="submit" value="Show my last location" /> 
			</form>
			<br><br>
			
			<div
				style="background-color: #CEF6E3; padding: 15px; width: 1300px; height: 0px;">	
			</div>
			<br><br>
			
			<form align="center" style=" color:#045FB4; font-family:Arial Unicode MS;" action="checkin" method="post">
				Place: <input type="text" name="place" /> <br> <br>
				Post: <input type="text" name="post" /> <br> <br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value="Checkin" />
			</form>
			<br><br>
			
			<div
				style="background-color: #CEF6E3; padding: 15px; width: 1300px; height: 0px;">	
			</div>
			<br><br>
			
			<form align="center" style=" color:#045FB4; font-family:Arial Unicode MS;" action="savePlace" method="post">
				Place: <input type="text" name="place" /> <br> <br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value="Save" />
			</form>
			<br><br>
			
			<div
				style="background-color: #CEF6E3; padding: 15px; width: 1300px; height: 0px;">	
			</div>
			<br><br>
			
			<form align="center" style=" color:#045FB4; font-family:Arial Unicode MS;" action="numCheckinsSort" method="post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value="Sort by number of checkins" />
			</form>
			<br><br>
			
			<form align="center" style=" color:#045FB4; font-family:Arial Unicode MS;" action="noSort" method="post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value="Show without sort" />
				<p>Check-ins :</p>
				<table>
					<c:forEach items="${myHomepageCheckins}" var="i">
						<tr>
							<c:set var="titleURL">
								<c:url value="/app/showPostPage">
									<c:param name="checkinID" value="${i.checkin}" />
								</c:url>
							</c:set>
							<td><a href="${titleURL}"> ${i.user} ${i.desc} </a></td>
						</tr>
					</c:forEach>
				</table>
				<p>Saves :</p>
				<table>
					<c:forEach items="${myHomepageSaves}" var="i">
						<tr>
							<td>${i.user} ${i.desc}</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</center>
	</body>
</html>