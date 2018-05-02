package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/CreateCalendarServlet")
public class CreateCalendarServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		String calendar = req.getParameter("calendar");
		String newcalendar = req.getParameter("newcalendar");

		Usuario user = UsuarioDAOImplementation.getInstance().readUsuario(email);
		
		if (newcalendar != null && newcalendar != "") {
			String newid = CalendarAPI.getInstance().insertCalendars(email, newcalendar);
			user.setIdCalendar(newid);
		} else {
			user.setIdCalendar(calendar);
		}

		UsuarioDAOImplementation.getInstance().updateUsuario(user);
		
		req.getSession().removeAttribute("calendar_list");
		RequestDispatcher rd = req.getRequestDispatcher("EventListServlet");
		rd.forward(req,resp);
	}
	
}