<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<title>
			FCISquare Friend Page
		</title>
		<link rel="icon" href="../Images/icon.png">
	</head>

	<body>

		<center>
			<div style="float:left;" >
				<img src="../Images/friends.png"> <br> 
				<img src="Images/friend word.png"  width=55% height=55%>
			</div>
			<br> <br>
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Friends Page</h1> <br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "unFollowFriend" method = "post" >
				
				Friend's email <input type="text" name = "email2" /> <br> <br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value = "Unfollow" />

			</form>
			<br> <br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "doFollow" method = "post" >
				
				User's email <input type="text" name = "email2" /> <br> <br>
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type="submit" value = "Follow" />
				
			</form>
			<br><br>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "dogetFollowers" method = "post" >
					<input style="font-family:Arial Unicode MS; font-size:155%; color:#0B610B; background-color:#CEF6E3;" type="submit" value = "Show my friends" />
					<table>
						<c:forEach items="${myFollowList}" var="i">
							<tr>
								<td> ${i} </td>
							</tr>
						</c:forEach>
					</table>
			</form>
		</center>
	</body>

</html>