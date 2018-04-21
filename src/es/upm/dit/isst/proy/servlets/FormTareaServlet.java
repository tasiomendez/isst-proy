package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.JobDAOImplementation;
import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.TareaDAOImplementation;
import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Job;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/FormTareaServlet")
public class FormTareaServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title =req.getParameter("title");
		String description =req.getParameter("description");
		String planned_hours=req.getParameter("planned_hours");
		String email =req.getParameter("trabajador");
		String project_code= req.getParameter("project_code");
		Proyecto proyecto = ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		Usuario trabajador= UsuarioDAOImplementation.getInstance().readUsuario(email);
		
		Tarea tarea = new Tarea();
		tarea.setTitulo(title);
		tarea.setDescripcion(description);
		tarea.setProyecto(proyecto);
		tarea.setPlanned_hours(Integer.parseInt(planned_hours));
		TareaDAOImplementation.getInstance().createTarea(tarea);
		
		Job job =new Job();
		job.setTarea(tarea);
		job.setUsuario(trabajador);
		job.setHoras_hechas(0);
		job.setHoras_planificadas(Integer.parseInt(planned_hours));
		
		JobDAOImplementation.getInstance().createJob(job);
		resp.sendRedirect(req.getContextPath()+"/dashboard.jsp");
		
		
		
	}
}
