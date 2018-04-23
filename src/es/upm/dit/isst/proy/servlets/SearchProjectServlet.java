package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;

@WebServlet("/SearchProjectServlet")
public class SearchProjectServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		String project_code = req.getParameter("project_code");
		
		Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		
		resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    resp.setCharacterEncoding("UTF-8"); 
		String response="";
		if(proyecto==null) {
			response="No existe proyecto con este project code.";
			resp.setStatus(404);;
		}else {
			boolean isJoined=false;
			ArrayList<Contrato> contratos = new ArrayList<Contrato>();
			contratos.addAll(proyecto.getContratos());
			for(int i=0;i<contratos.size();i++) {
				if(contratos.get(i).getUsuario().getEmail().equals(email)) {
					isJoined=true;
					break;
				}
			}
			response=proyecto.toString()+"\\&\\"+isJoined;
		}
		resp.getWriter().write(response);
	}
}
