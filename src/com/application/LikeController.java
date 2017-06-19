package com.application;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LikeController {

	@Context
	HttpServletRequest request;

	@POST
	@Path("/likeCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String likeCheckin() {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		String toEmail = (String) session.getAttribute("checkinUser");
		String id = (String) session.getAttribute("checkinID");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/likeCheckin";
		String serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/likeCheckin";

		String urlParameters = "email=" + userEmail + "&toEmail=" + toEmail
				+ "&Checkin=" + id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Done";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";

	}

	@POST
	@Path("/getLikes")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Like> getLikes(String email) {
		String userEmail;HttpSession session=null;
		if(email.compareTo("")==0){
		session= request.getSession();
		 userEmail= (String) session.getAttribute("email");
		}
		else userEmail=email;
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getLikes";
		String serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getLikes";

		String urlParameters = "email=" + userEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		JSONArray arr;
		JSONObject obj = new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<Like> all = new ArrayList<Like>();
			Like l;
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject) parser.parse(arr.get(i).toString());
				l = new Like(obj.get("ToUser").toString(), Integer.parseInt(obj
						.get("aCheckin").toString()), obj.get("aDate")
						.toString());
				all.add(l);
			}
			if(email.compareTo("")!=0)return all;
			session.setAttribute("history1",all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/undoLike")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoLike(@QueryParam("checkinID") String id) {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/undoLike";
		String serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/undoLike";
		String urlParameters = "email=" + userEmail + "&checkinID=" + id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Done";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";

	}

	@POST
	@Path("/peopleWhoLikedThis")
	@Produces(MediaType.TEXT_PLAIN)
	public void getPeopleLikes() {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("checkinID");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/numberOfLikes";
		String serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/numberOfLikes";

		String urlParameters = "CheckinID=" + id;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long numLikes = (Long) obj.get("number of likes");
			session.setAttribute("numLikestoPost",numLikes);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		serviceUrl ="http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/whoLikes";
		retJson = Connection.connect(serviceUrl, urlParameters, "POST",
						"application/x-www-form-urlencoded;charset=UTF-8");
		parser = new JSONParser();
		JSONArray arr;
		obj = new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<String> all = new ArrayList<String>();
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject) parser.parse(arr.get(i).toString());
				String tmp = "user";
				tmp += Integer.toString(i+1);
				all.add(obj.get(tmp).toString());
			}
			session.setAttribute("whoLiked", all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
