package com.application;

public class Comment {
	String user,to;
	int checkin ;
	String date,desc="commented on ";
	public Comment(){
		
	}
	public Comment(String userModel, int int1, String date2) {
		this.user = userModel;
		this.checkin = int1;
		this.date=date2;
		desc+=user+"'s post";
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getCheckin() {
		return checkin;
	}
	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
