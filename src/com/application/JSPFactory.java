package com.application;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/")
public class JSPFactory {
	@Context
	HttpServletRequest request;
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public Response loginPage() {
		return Response.ok(new Viewable("/Login.jsp")).build();
	}

	@GET
	@Path("/signUp")
	@Produces(MediaType.TEXT_HTML)
	public Response signUpPage() {
		return Response.ok(new Viewable("/Signup.jsp")).build();
	}

	@GET
	@Path("/showLocation")
	@Produces(MediaType.TEXT_HTML)
	public Response showLocationPage() {
		return Response.ok(new Viewable("/ShowLocation.jsp")).build();
	}

	@GET
	@Path("/showFriendsPage")
	@Produces(MediaType.TEXT_HTML)
	public Response showFriendsPage() {
		return Response.ok(new Viewable("/FriendsPage.jsp")).build();
	}

	@GET
	@Path("/showNewPlacePage")
	@Produces(MediaType.TEXT_HTML)
	public Response showNewPlacePage() {
		return Response.ok(new Viewable("/NewPlace.jsp")).build();
	}

	@GET
	@Path("/showHistoryPage")
	@Produces(MediaType.TEXT_HTML)
	public Response showHistoryPage(@QueryParam("email") String email) {
		HttpSession session = request.getSession();
		session.setAttribute("email",email);
		return Response.ok(new Viewable("/ActionsHistory.jsp")).build();
	}

	@GET
	@Path("/showNotificationsPage")
	@Produces(MediaType.TEXT_HTML)
	public Response showNotificationsPage() {
		return Response.ok(new Viewable("/Notification.jsp")).build();
	}

	@GET
	@Path("/showPostPage")
	@Produces(MediaType.TEXT_HTML)
	public Response showPostPage(@QueryParam("checkinID") String checkinID) {
		HttpSession session = request.getSession();
		session.setAttribute("checkinID",checkinID);
		return Response.ok(new Viewable("/Post.jsp")).build();
	}
	
	@GET
	@Path("/showLikePage")
	@Produces(MediaType.TEXT_HTML)
	public Response showLikePage(@QueryParam("checkinID") String checkinID) {
		HttpSession session = request.getSession();
		session.setAttribute("checkinID",checkinID);
		return Response.ok(new Viewable("/Like.jsp")).build();
	}
}
