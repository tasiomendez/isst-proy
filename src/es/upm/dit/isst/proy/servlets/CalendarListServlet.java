package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.calendar.model.CalendarListEntry;

import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/CalendarListServlet")
public class CalendarListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<CalendarListEntry> calendar_list = CalendarAPI.getInstance().getCalendars((String) req.getSession().getAttribute("email"));

		System.out.println("lista" + calendar_list);

		req.getSession().setAttribute("calendar_list", calendar_list);
		resp.sendRedirect(req.getContextPath());
	}

}