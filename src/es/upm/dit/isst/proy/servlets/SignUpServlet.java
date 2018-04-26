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

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		
		if (UsuarioDAOImplementation.getInstance().readUsuario(email) != null) {
			req.getSession().setAttribute("user_error", "Este usuario ya está registrado.");
			resp.sendRedirect(req.getContextPath() + "/signup.jsp");
		} else {
			String name = req.getParameter("name");
			String password = req.getParameter("password");//Esto lo cambiare para guardar un Hash
			String project_code = req.getParameter("project_code");

			//Busco el proyecto asociado al referal code
			Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
			if (proyecto == null) {
				req.getSession().setAttribute("user_error", "No existe ningún proyecto con ese Project Code.");
				resp.sendRedirect(req.getContextPath() + "/signup.jsp");
				
			} else {
				Usuario usuario= new Usuario();
				usuario.setNombre(name);
				usuario.setEmail(email);
				usuario.setContraseña(password);
				usuario.setRol(3); //Tiene rol de trabajador
				
				//Creo el usuario
				UsuarioDAOImplementation.getInstance().createUsuario(usuario);
				
				//Creo el contrato entre proyecto-usuario
				Contrato contrato = new Contrato();
				contrato.setUsuario(usuario);
				contrato.setProyecto(proyecto);
				ContratoDAOImplementation.getInstance().createContrato(contrato);
				req.getSession().setAttribute("role",usuario.getRol());
				req.getSession().setAttribute("email", usuario.getEmail());
				
				Usuario user = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
				ArrayList<Contrato> contratos = new ArrayList<Contrato>();
				contratos.addAll(user.getContratos());
				Proyecto[] proyectos = new Proyecto[contratos.size()];
				for(int i=0;i<contratos.size();i++) {
					proyectos[i]=contratos.get(i).getProyecto();
				}
				req.getSession().setAttribute("project_list",proyectos );
				req.getSession().setAttribute("name",user.getNombre());
				
				resp.sendRedirect(req.getContextPath());
			}
		}		
	}
}
