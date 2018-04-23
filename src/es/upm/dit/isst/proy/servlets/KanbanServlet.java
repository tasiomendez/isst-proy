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
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/KanbanServlet")
public class KanbanServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String project_code = req.getParameter("project_code");
		redirectToKanban(req, resp, project_code);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String project_code = req.getParameter("project_code");
		redirectToKanban(req, resp, project_code);
	}
	
	private void redirectToKanban(HttpServletRequest req, HttpServletResponse resp, String project_code) throws ServletException, IOException {
		Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		req.getSession().setAttribute("tareas_list",proyecto.getTareas());
		req.getSession().setAttribute("project_title",proyecto.getTitulo());
		//Necesitamos la lista de trabajadores asociados a un proyecto para poder asociarles tareas
		ArrayList<Contrato> contratos= new ArrayList<Contrato>();
		contratos.addAll(proyecto.getContratos());
		ArrayList<Usuario> trabajadores=new ArrayList<Usuario>();
		for(int i =0;i<contratos.size();i++) {
			if(contratos.get(i).getUsuario().getRol()==3) {
				trabajadores.add(contratos.get(i).getUsuario());
			}
		}
		req.getSession().setAttribute("project_code", project_code);
		req.getSession().setAttribute("trabajador_list",trabajadores);
		resp.sendRedirect(req.getContextPath() + "/kanban");
	}
}





