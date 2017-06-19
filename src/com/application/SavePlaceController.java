package com.application;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
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
public class SavePlaceController {
	@Context
	HttpServletRequest request;

	@POST
	@Path("/savePlace")
	@Produces(MediaType.TEXT_PLAIN)
	public String savePlace(@FormParam("place") String place) {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		// String serviceUrl = "http://localhost:8080/FCISquare/rest/savePlace";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/savePlace";

		String urlParameters = "userEmail=" + userEmail + "&placeName=" + place;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Place successfully saved";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";

	}

	@POST
	@Path("/getSaves")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<SavedPlace> getSaves(String email) {
		String userEmail;
		HttpSession session = null;
		if (email.compareTo("") == 0) {
			session = request.getSession();
			userEmail = (String) session.getAttribute("email");
		} else
			userEmail = email;
		// String serviceUrl = "http://localhost:8080/FCISquare/rest/getSaves";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getSaves";

		String urlParameters = "email=" + userEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		JSONArray arr;
		JSONObject obj = new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<SavedPlace> all = new ArrayList<SavedPlace>();
			SavedPlace s;
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject) parser.parse(arr.get(i).toString());
				s = new SavedPlace(obj.get("userEmail").toString(), obj.get(
						"placeName").toString());
				all.add(s);
			}
			if (email.compareTo("") != 0)
				return all;
			session.setAttribute("saveHistory", all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
