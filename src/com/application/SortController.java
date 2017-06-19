package com.application;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
@Path("/")
public abstract class SortController {
ArrayList<Checkin>allCheckins=new ArrayList<Checkin>();
ArrayList<SavedPlace>allSaves=new ArrayList<SavedPlace>();
@Context
HttpServletRequest request;

public ArrayList<Checkin> getAllCheckins() {
	return allCheckins;
}
public void setAllCheckins() {
	HttpSession session = request.getSession();
	String userEmail = (String) session.getAttribute("email");
	CheckinController c=new CheckinController();
	this.allCheckins.addAll(c.getCheckins(userEmail));
	
	FollowController f=new FollowController();
	this.allCheckins.addAll(f.getFollowCheckins(userEmail));
	this.allCheckins.addAll(f.getFollowLikes(userEmail));
	this.allCheckins.addAll(f.getFollowComments(userEmail));
}
public ArrayList<SavedPlace> getAllSaves() {
	return allSaves;
}
public void setAllSaves() {
	HttpSession session = request.getSession();
	String userEmail = (String) session.getAttribute("email");
	FollowController f=new FollowController();
	this.allSaves.addAll(f.getFollowSaves(userEmail));
}
public abstract void sort();
}
