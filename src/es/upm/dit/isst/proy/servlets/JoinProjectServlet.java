package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ContratoDAOImplementation;
import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/JoinProjectServlet")
public class JoinProjectServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String project_code=req.getParameter("project_code");
		String trabajador_email=(String) req.getSession().getAttribute("email");
		
		Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
	
		if(proyecto==null) {
			System.out.println("No existe ningun proyecto con este project code");
		}else {
			//Crear un nuevo contrato entre este proyecto y usuario
			Usuario trabajador = UsuarioDAOImplementation.getInstance().readUsuario(trabajador_email);
			
			Contrato contrato = new Contrato();
			contrato.setUsuario(trabajador);
			contrato.setProyecto(proyecto);
			ContratoDAOImplementation.getInstance().createContrato(contrato);
			
			//Actualizar el att de sesion de lista de proyectos
			trabajador = UsuarioDAOImplementation.getInstance().readUsuario(trabajador_email);
			ArrayList<Contrato> contratos = new ArrayList<Contrato>();
			contratos.addAll(trabajador.getContratos());
			Proyecto[] proyectos = new Proyecto[contratos.size()];
			for(int i=0;i<contratos.size();i++) {
				proyectos[i]=contratos.get(i).getProyecto();
			}
			req.getSession().setAttribute("project_list",proyectos );
			
			
		}
		resp.sendRedirect(req.getContextPath());

	}
}
