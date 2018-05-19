package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/AnalysisServlet")
public class AnalysisServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String trabajador_email=req.getParameter("email");
		Usuario trabajador= UsuarioDAOImplementation.getInstance().readUsuario(trabajador_email);
		resp.setContentType("text/plain");
		String response="";
		String proyectos="";
		String proyectos_tareas="";
		/*Informacion del usuario:nombre y email*/
		response+=trabajador.getNombre()+"\\&\\"+trabajador.getEmail()+"//&//";
		/*Proyectos de un usuario y tareas que tiene en los proyectos*/
		ArrayList<Contrato> contratos=new ArrayList<Contrato>();
		contratos.addAll(trabajador.getContratos());
		ArrayList<Tarea> tareas=new ArrayList<Tarea>();
		tareas.addAll(trabajador.getTareas());
		int count_tareas=0;
		for(int i=0;i<contratos.size();i++) {
			Proyecto proyecto = contratos.get(i).getProyecto();
			
			for(int j=0;j<tareas.size();j++) {
				if(tareas.get(j).getProyecto().getTitulo().equals(proyecto.getTitulo())){
					count_tareas++;
				}
			}
			if(i==contratos.size()-1) {
				proyectos+=proyecto.getTitulo()+','+proyecto.getFechaInicio()+','+proyecto.getFechaFinal()+','+proyecto.getProject_code();
				proyectos_tareas+=count_tareas;
			}else {
				proyectos+=proyecto.getTitulo()+','+proyecto.getFechaInicio()+','+proyecto.getFechaFinal()+','+proyecto.getProject_code()+"\\&\\";
				proyectos_tareas+=count_tareas+"\\&\\";
			}
			count_tareas=0;	
		}
		response+=proyectos+"//&//"+proyectos_tareas+"//&//";
		/* Eficiencia de tareas */
		int tareas_rapidas=0;
		int tareas_lentas=0;
		for(int i=0;i<tareas.size();i++) {
			Tarea tarea = tareas.get(i);
			if(tarea.getEstado().equals("done")) {
				if(tarea.getWorked_hours()>tarea.getPlanned_hours())
					tareas_lentas++;
				else
					tareas_rapidas++;
					
			}
		}
		response+=tareas_rapidas+"\\&\\"+tareas_lentas;
		
		System.out.println(response);
		resp.getWriter().write(response);
		
	}

}
