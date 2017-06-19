package com.application;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/")
public class JustShowSort extends SortController{

	@POST
	@Path("/noSort")
	@Produces(MediaType.TEXT_PLAIN)
	public void sort() {
		setAllCheckins();
		setAllSaves();
		HttpSession session = request.getSession();
		session.setAttribute("myHomepageCheckins",allCheckins);
		session.setAttribute("myHomepageSaves",allSaves);
	}

}
