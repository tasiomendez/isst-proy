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

@WebServlet("/CreateEventServlet")
public class CreateEventServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuario(email);
		String calendarId = user.getIdCalendar();
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String initialDate = req.getParameter("initialEventDate");
				
		CalendarAPI.getInstance().insertEvents(email, calendarId, title, description, initialDate);
		
		RequestDispatcher rd = req.getRequestDispatcher("EventListServlet");
		rd.forward(req,resp);
	}
	
}