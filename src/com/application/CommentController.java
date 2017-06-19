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
public class CommentController {

	@Context
	HttpServletRequest request;
	
	@POST
	@Path("/commentCheckin")
	@Produces(MediaType.TEXT_PLAIN)
	public String commentCheckin(@FormParam("comment") String comment){
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		String toEmail = (String) session.getAttribute("checkinUser");
		String id=(String)session.getAttribute("checkinID");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/addcommentcheckin";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/addcommentcheckin";
		
		
		String urlParameters = "email=" + userEmail+"&toEmail=" + toEmail +"&checkin=" + id+"&comment=" + comment ;
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
	
	@POST
	@Path("/getComments")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Comment> getComments(String email){
		String userEmail;HttpSession session=null;
		if(email.compareTo("")==0){
		session= request.getSession();
		 userEmail= (String) session.getAttribute("email");
		}
		else userEmail=email;
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getComments";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getComments";
		
		
		String urlParameters = "email=" + userEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		
		JSONParser parser = new JSONParser();
		JSONArray arr;
		JSONObject obj=new JSONObject();
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<Comment>all=new ArrayList<Comment>();
			Comment c;
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject)parser.parse(arr.get(i).toString());
				c=new Comment(obj.get("ToUser").toString(),Integer.parseInt(obj.get("aCheckin").toString()),obj.get("aDate").toString());
				all.add(c);
			}
			if(email.compareTo("")!=0)return all;
			session.setAttribute("history2",all);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/undoComment")
	@Produces(MediaType.TEXT_PLAIN)
	public String undoComment(@QueryParam("checkinID") String id){
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/undoComment";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/undoComment";
		
		
		String urlParameters = "email=" + userEmail +"&checkin=" +id ;
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
