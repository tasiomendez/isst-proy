package es.upm.dit.isst.proy.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Proyecto;

@WebServlet("/SearchProjectServlet")
public class SearchProjectServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String project_code = req.getParameter("project_code");
		
		Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		
		resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    resp.setCharacterEncoding("UTF-8"); // You want world domination
		String response="";
		if(proyecto==null) {
			response="No existe proyecto con este prject code.";
			
		}else {
			response=proyecto.toString();
		}
		resp.getWriter().write(response); 
	}
}
