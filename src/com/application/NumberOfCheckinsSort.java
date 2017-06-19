package com.application;

import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class NumberOfCheckinsSort extends SortController {

	@POST
	@Path("/numCheckinsSort")
	@Produces(MediaType.TEXT_PLAIN)
	public void sort() {
		setAllCheckins();
		setAllSaves();
		PlaceController p=new PlaceController();
		Vector<Long>numCheckins=new Vector<Long>();
		Vector<Long>numCheckins2=new Vector<Long>();
		for (int i = 0; i < allCheckins.size(); i++) {
			numCheckins.addElement(p.getNumberOfCheckins(allCheckins.get(i).getPlace()));
		}
		for (int i = 0; i < allSaves.size(); i++) {
			numCheckins2.addElement(p.getNumberOfCheckins(allSaves.get(i).getPlace()));
		}
		Long current;Checkin cur;
		for (int i = 1; i < numCheckins.size(); i++) {
			current = numCheckins.elementAt(i);
			cur=allCheckins.get(i);
			int j = i - 1;
			while (j >= 0 && numCheckins.elementAt(j) < current) {
				numCheckins.setElementAt(numCheckins.elementAt(j), j + 1);
				allCheckins.set(j+1, allCheckins.get(j));
				j--;
			}
			numCheckins.setElementAt(current, j + 1);
			allCheckins.set(j+1,cur);
		}
		SavedPlace s;
		for (int i = 1; i <numCheckins2.size(); i++) {
			current = numCheckins2.elementAt(i);
			s=allSaves.get(i);
			int j = i - 1;
			while (j >= 0 && numCheckins2.elementAt(j) < current) {
				numCheckins2.setElementAt(numCheckins2.elementAt(j), j + 1);
				allSaves.set(j+1, allSaves.get(j));
				j--;
			}
			numCheckins2.setElementAt(current, j + 1);
			allSaves.set(j+1,s);
		}
		HttpSession session = request.getSession();
		session.setAttribute("myHomepageCheckins",allCheckins);
		session.setAttribute("myHomepageSaves",allSaves);
	}

}
