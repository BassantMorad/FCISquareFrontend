package com.application;

public class Checkin {
	String user,place;
	int checkin ;
	String date,desc="checked in ";
	public Checkin(){
		
	}
	public Checkin(String userModel, int int1, String date2) {
		this.user = userModel;
		this.checkin = int1;
		this.date=date2;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
		desc+=place;
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
		this.desc+=place;
	}
	
}
