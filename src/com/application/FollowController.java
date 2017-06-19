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
public class FollowController {

	@Context
	HttpServletRequest request;

	@POST
	@Path("/doFollow")
	@Produces(MediaType.TEXT_PLAIN)
	public String doFollow(@FormParam("email2") String emailTo) {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/follow";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/follow";

		String urlParameters = "email1=" + userEmail + "&email2=" + emailTo;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "Follow done";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";
	}

	@POST
	@Path("/dogetFollowers")
	@Produces(MediaType.TEXT_HTML)
	public ArrayList<String> getFollowers(String uEmail) {
		//String serviceUrl = "http://localhost:8080/FCISquare/rest/getfollowlist";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/getfollowlist";
		String email=uEmail;
		HttpSession session=null;
		if(uEmail.compareTo("")==0){
		session = request.getSession();
		 email = (String) session.getAttribute("email");}
		String urlParameters = "email=" + email;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj = new JSONObject();
		JSONArray arr;
		try {
			arr = (JSONArray) parser.parse(retJson);
			ArrayList<String> all = new ArrayList<String>();
			for (int i = 0; i < arr.size(); i++) {
				obj = (JSONObject) parser.parse(arr.get(i).toString());
				String tmp = "user";
				tmp += Integer.toString(i + 1);
				all.add(obj.get(tmp).toString());
			}
			if(uEmail.compareTo("")!=0)return all;
			session.setAttribute("myFollowList", all);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/unFollowFriend")
	@Produces(MediaType.TEXT_PLAIN)
	public String unFollowFriend(@FormParam("email2") String FollowEmail) {
		HttpSession session = request.getSession();
		String userEmail = (String) session.getAttribute("email");
		// String serviceUrl = "http://localhost:8080/FCISquare/rest/unfollow";
		String serviceUrl = "http://firstapp-fciseengineering.rhcloud.com/FCISquare/rest/unfollow";

		String urlParameters = "email1=" + userEmail + "&email2=" + FollowEmail;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		JSONObject obj;
		try {
			obj = (JSONObject) parser.parse(retJson);
			Long status = (Long) obj.get("status");
			if (status == 1)
				return "You removed " + FollowEmail + " from your friends list";
			else
				return "A problem occured";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "A problem occured";
	}

	@POST
	@Path("/getFollowCheckins")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Checkin> getFollowCheckins(String email) {
		CheckinController c = new CheckinController();
		ArrayList<Checkin> followCheckins = new ArrayList<Checkin>();
		ArrayList<String> followed = getFollowers(email);
		for (int i = 0; i < followed.size(); i++) {
			followCheckins.addAll(c.getCheckins(followed.get(i)));
		}
		return followCheckins;
	}
	
	@POST
	@Path("/getFollowLikes")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Checkin> getFollowLikes(String email) {
		LikeController c = new LikeController();
		ArrayList<Like> followLikes = new ArrayList<Like>();
		ArrayList<String> followed = getFollowers(email);
		for (int i = 0; i < followed.size(); i++) {
			followLikes.addAll(c.getLikes(followed.get(i)));
		}
		ArrayList<Checkin>followLikedCheckin=new ArrayList<Checkin>();
		CheckinController cc=new CheckinController();
		for (int i = 0; i < followLikes.size(); i++) {
			Checkin tmp=cc.getcheckin(Integer.toString(followLikes.get(i).getCheckin()));
			tmp.setDesc("liked a check in at ");
			followLikedCheckin.add(tmp);
		}
		return followLikedCheckin;
	}
	
	@POST
	@Path("/getFollowComments")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<Checkin> getFollowComments(String email) {
		CommentController c = new CommentController();
		ArrayList<Comment> followComments = new ArrayList<Comment>();
		ArrayList<String> followed = getFollowers(email);
		for (int i = 0; i < followed.size(); i++) {
			followComments.addAll(c.getComments(followed.get(i)));
		}
		ArrayList<Checkin>followCommentedCheckin=new ArrayList<Checkin>();
		CheckinController cc=new CheckinController();
		for (int i = 0; i < followComments.size(); i++) {
			Checkin tmp=cc.getcheckin(Integer.toString(followComments.get(i).getCheckin()));
			tmp.setDesc("commented on a check in at ");
			followCommentedCheckin.add(tmp);
		}
		return followCommentedCheckin;
	}
	
	@POST
	@Path("/getFollowSaves")
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<SavedPlace> getFollowSaves(String email) {
		SavePlaceController c = new SavePlaceController();
		ArrayList<SavedPlace> followSaves = new ArrayList<SavedPlace>();
		ArrayList<String> followed = getFollowers(email);
		for (int i = 0; i < followed.size(); i++) {
			followSaves.addAll(c.getSaves(followed.get(i)));
		}
		return followSaves;
	}
}
