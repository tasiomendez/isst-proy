package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.*;
import java.util.List;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/EventListServlet")
public class EventListServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Events events = CalendarAPI.getInstance().getEvents(email, 
				UsuarioDAOImplementation.getInstance().readUsuario(email).getIdCalendar());

		List <String> newEvents = new ArrayList <String>();
		List <Event> eventList = events.getItems();
		if (eventList.size() == 0) {
			System.out.println("No hay eventos");
		} else {
			for (Event event : eventList){
				DateTime start = event.getStart().getDateTime();

				if (start == null) 
					start = event.getStart().getDate();

				// Cambiar el formato de la fecha para añadir en el calendario
				String start2 = start.toString();
				SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd"); 
				Date date = null;
				try {
					date = dt.parse(start2);
				} catch (ParseException e) {
					e.printStackTrace();
				} 
				SimpleDateFormat dt1 = new SimpleDateFormat("yyyy, mm-1, dd");
				newEvents.add("{'Date': new Date("+dt1.format(date)+"), 'Title': '" +event.getSummary()+"'}");   
			}
		}
		req.getSession().setAttribute("events", newEvents);
		req.getSession().setAttribute("calendar_id", UsuarioDAOImplementation.getInstance().readUsuario(email).getIdCalendar());
		resp.sendRedirect(req.getContextPath());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
