package com.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class PlaceController {
	
	@Context
	HttpServletRequest request;
	
	@POST
	@Path("/addNewPlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String addNewPlace(@FormParam("name") String name,@FormParam("desc") String desc,@FormParam("lat") String lat,@FormParam("lon") String lon){
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/addNewPlace";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/addNewPlace";
		
		
		String urlParameters = "name=" + name +"&latitude=" + lat+"&longitude=" + lon+"&description=" + desc+"&email=" + userEmail ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if(status == 1)
				return "Place successfully added";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";
		
	}
	
	@POST
	@Path("/numCheckins")
	@Produces(MediaType.TEXT_PLAIN)
	public Long getNumberOfCheckins(String placeName) {
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/numberOfCheckins";
		 String serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/numberOfCheckins";

		String urlParameters = "placeName=" + placeName;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long numCheckins = (Long) obj.get("number of checkins");
			return numCheckins;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
