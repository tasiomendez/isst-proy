package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/FormTareaServlet")
public class FormTareaServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title =req.getParameter("title");
		String description =req.getParameter("description");
		String planned_hours=req.getParameter("planned_hours");
		String email =req.getParameter("trabajador");
		
		Usuario trabajador= UsuarioDAOImplementation.getInstance().readUsuario(email);
		
		
		
		
		
	}
}
