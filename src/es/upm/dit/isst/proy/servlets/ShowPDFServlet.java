package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Proyecto;



@WebServlet("/ShowPDFServlet")
public class ShowPDFServlet extends HttpServlet{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
	 		int id = Integer.parseInt(req.getParameter("project_id"));

	 		Proyecto project = ProyectoDAOImplementation.getInstance().readProyecto(id);
			
			resp.setContentLength(project.getDocument().length);
			resp.getOutputStream().write(project.getDocument());
		}
	}



