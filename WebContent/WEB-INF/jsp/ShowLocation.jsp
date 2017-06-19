<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" href="../Images/icon.png">
		<meta http-equiv="Content-Type"
			content="text/html; charset=windows-1256">
		<title>FCISquare Show location</title>
		<script src="http://maps.googleapis.com/maps/api/js">
			
		</script>
		<script>
			function getLocation() {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(showPosition, showError);
				} else {

				}
			}

			function showPosition(position) {
				document.getElementById('lat').value = position.coords.latitude;
				document.getElementById('long').value = position.coords.longitude;
				var myLatLng = {
					lat : position.coords.latitude,
					lng : position.coords.longitude
				};
				var map = {
					center : myLatLng,
					zoom : 15,
					mapTypeId : google.maps.MapTypeId.ROADMAP
				};
				var map = new google.maps.Map(document.getElementById("googleMap"), map);
				var marker = new google.maps.Marker({
					position : myLatLng,
					map : map,
					title : 'You are here :D :D '
				});

			}

			function showError(error) {
				switch (error.code) {
				case error.PERMISSION_DENIED:
					x.innerHTML = "User denied the request for Geolocation."
					break;
				case error.POSITION_UNAVAILABLE:
					x.innerHTML = "Location information is unavailable."
					break;
				case error.TIMEOUT:
					x.innerHTML = "The request to get user location timed out."
					break;
				case error.UNKNOWN_ERROR:
					x.innerHTML = "An unknown error occurred."
					break;
				}
			}
			google.maps.event.addDomListener(window, 'load', getLocation);
		</script>
	</head>
	<body>
		
		<img src="../Images/my loc.gif" style="float:left; " width=15% height=15%>
		<center>
			<br> <br>
			<h1 style="font-family:Snap ITC; font-size:300%; color:#2E9AFE;"  >Welcome to FCI Square</h1>
			<h1 style="font-family:Snap ITC;font-size:200%; color:#2ECCFA">Show my location</h1>
			
			<div id="googleMap" style="width: 500px; height: 380px; color:blue"></div>
			<br>
			<form action="updateMyLocation" method="post">

				<input id="lat" type="hidden" name="lat" /> 
				<input id="long" type="hidden" name="long" /> 
				<input style="font-family:Arial Unicode MS; font-size:125%; color:#0B610B; background-color:#CEF6E3; " type="submit" value="Update my current position!" />
			</form>
		</center>
	</body>
</html>