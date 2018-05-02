package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.TareaDAOImplementation;
import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/kanban/CreateTareaServlet")
public class CreateTareaServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title =req.getParameter("title");
		String description =req.getParameter("description");
		String planned_hours=req.getParameter("planned_hours");
		String email =req.getParameter("trabajador");
		String fecha_entrega=req.getParameter("finalDate");
		String project_code= (String) req.getSession().getAttribute("project_code");
		Proyecto proyecto = ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		Usuario trabajador= UsuarioDAOImplementation.getInstance().readUsuario(email);
		
		Tarea tarea = new Tarea();
		tarea.setTitulo(title);
		tarea.setDescripcion(description);
		tarea.setProyecto(proyecto);
		tarea.setEstado("todo");
		tarea.setPlanned_hours(Integer.parseInt(planned_hours));
		tarea.setFecha_entrega(fecha_entrega);
		tarea.setWorked_hours(0);
		tarea.setUsuario(trabajador);
		
		TareaDAOImplementation.getInstance().createTarea(tarea);
		
		//Creo un evento en el calendario del trabajador (siempre y cuando tenga el calendario sincronizado)
		if(trabajador.getIdCalendar() != null)
			CalendarAPI.getInstance().insertEvents(trabajador.getEmail(),trabajador.getIdCalendar(), title, description, fecha_entrega);
		
		Proyecto proyecto_nuevo = ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		tareas.addAll(proyecto_nuevo.getTareas());
		req.getSession().setAttribute("tareas_list", tareas);
		
		resp.sendRedirect(req.getContextPath() + "/kanban");
		
	}
}