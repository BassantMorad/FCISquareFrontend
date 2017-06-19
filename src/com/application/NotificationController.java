package com.application;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/")
public class NotificationController {
	@Context
	HttpServletRequest request;
	
	@POST
	@Path("/getNotifications")
	@Produces(MediaType.TEXT_PLAIN)
	public void getNotifications() {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getNotifications";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getNotifications";

		String urlParameters = "email=" + userEmail ;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		JSONArray arr;
		JSONObject obj=new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<Notification>all=new ArrayList<Notification>();
			Notification n;
			for (int i = 0; i < arr.size(); i++) {
				n=new Notification();
				obj = (JSONObject)parser.parse(arr.get(i).toString());
				n.setTo(obj.get("to").toString());
				n.setFrom(obj.get("from").toString());
				n.setDescription(obj.get("notificationDescription").toString());
				n.setType(obj.get("type").toString());
				n.setCheckin(Integer.parseInt(obj.get("notificationCheckin").toString()));
				all.add(n);
			}
			session.setAttribute("arr",all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
