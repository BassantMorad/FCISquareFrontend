<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>FCISquare Like Page</title>
		<link rel="icon" href="../Images/icon.png">
	</head>
	<body>
		<center>
			<img src="../Images/likee.png" style="float:left; " width=35% height=35%>
			<br> <br>
			<h1  align="center" style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1  align="center" style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Like Page</h1>
			
			<form style=" color:#045FB4; font-family:Arial Unicode MS;" action = "peopleWhoLikedThis" method = "post">
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3;" type = "submit" value = "Show" />
				
				<p> Number of likes to this post ${numLikestoPost} </p>
				
				<table>
					<c:forEach items="${whoLiked}" var="i">
						<tr>
								<td>${i} liked your post</td>
						</tr>
					</c:forEach>
				</table>
			</form>
		</center>
	</body>
</html>