package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.TareaDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Tarea;

@WebServlet("/kanban/UpdateKanbanServlet")
public class UpdateKanbanServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String id_card = req.getParameter("id_card");
		String state_card = req.getParameter("state_card");
		String worked_hours = req.getParameter("worked_hours");

		Tarea tarea = TareaDAOImplementation.getInstance().readTarea(Integer.parseInt(id_card));

		resp.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		resp.setCharacterEncoding("UTF-8"); 
		String response="";
		if(action.equals("state")) {
			//Accion de actualizar tarea
			tarea.setEstado(state_card);
			TareaDAOImplementation.getInstance().updateTarea(tarea);
			response = "Success";
		} else if(action.equals("delete")) {
			TareaDAOImplementation.getInstance().deleteTarea(tarea);
			response = "Success";
		} else if(action.equals("saveHours")) {
			tarea.setWorked_hours(Integer.parseInt(worked_hours));
			TareaDAOImplementation.getInstance().updateTarea(tarea);
			response = "Success";
		}
		resp.getWriter().write(response); 

	}

}