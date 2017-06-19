package com.application;

public class SavedPlace {
	String user,place,desc="saved ";

	public SavedPlace(String user, String place) {
		super();
		this.user = user;
		this.place = place;
		this.desc+=place;
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
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
