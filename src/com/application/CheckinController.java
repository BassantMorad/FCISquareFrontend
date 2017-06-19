package com.application;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class CheckinController {
	@Context
	HttpServletRequest request;

	@POST
	@Path("/checkin")
	@Produces(MediaType.TEXT_PLAIN)
	public void checkin(@FormParam("place") String place,
			@FormParam("post") String post) {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/checkin";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/checkin";

		String urlParameters = "email=" + userEmail + "&placeName=" + place
				+ "&post=" + post;

		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			session.setAttribute("mycheckinID", obj.get("id"));
			session.setAttribute("mycheckinPost", obj.get("post"));
			session.setAttribute("mycheckinDate", obj.get("date"));
			session.setAttribute("mycheckinTime", obj.get("time"));
			session.setAttribute("mycheckinPlace", obj.get("place"));
			session.setAttribute("mycheckinUser", obj.get("user"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@POST
	@Path("/getcheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public Checkin getcheckin(String checkinID) {
		String id;HttpSession session=null;
		if(checkinID.compareTo("")==0){
		session= request.getSession();
		 id= (String) session.getAttribute("checkinID");
		}
		else id=checkinID;
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/respondNotification";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/respondNotification";

		String urlParameters = "checkinID=" +id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Checkin c=new Checkin(obj.get("userEmail").toString(),Integer.parseInt(obj.get("id").toString()),obj.get("date").toString());
			c.setPlace(obj.get("placeName").toString());
			if(checkinID.compareTo("")!=0)return c;
			session.setAttribute("checkinID", obj.get("id"));
			session.setAttribute("checkinPost", obj.get("post"));
			session.setAttribute("checkinDate", obj.get("date"));
			session.setAttribute("checkinTime", obj.get("time"));
			session.setAttribute("checkinPlace", obj.get("placeName"));
			session.setAttribute("checkinUser", obj.get("userEmail"));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Path("/getCheckins")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Checkin> getCheckins(String email){
		String userEmail;HttpSession session=null;
		if(email.compareTo("")==0){
		session= request.getSession();
		 userEmail= (String) session.getAttribute("email");
		}
		else userEmail=email;
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getCheckins";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getCheckins";
		
		
		String urlParameters = "email=" + userEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		JSONArray arr;
		JSONObject obj=new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<Checkin>all=new ArrayList<Checkin>();
			Checkin c;
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject)parser.parse(arr.get(i).toString());
				c=new Checkin(obj.get("ToUser").toString(),Integer.parseInt(obj.get("aCheckin").toString()),obj.get("aDate").toString());
				c.setPlace(obj.get("place").toString());
				all.add(c);
			}
			if(email.compareTo("")!=0)return all;
			session.setAttribute("history3",all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/undoCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoComment(@QueryParam("historyPlace") String place,@QueryParam("checkinID") String id){
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/undoCheckin";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/undoCheckin";
		
		
		String urlParameters = "email=" + userEmail+"&name=" +place+"&id=" +id ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject)parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if(status == 1)
				return "Done";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";
		
	}
}
