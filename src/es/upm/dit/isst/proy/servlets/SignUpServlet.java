package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

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
	
	// COSILLAS: el form del jsp no tiene tiene action y tiene que ser un metodo Post no?
	// Hay que añadir la edad en el form o cambiar la bbdd
	//Campo de la disponibilidad?
	// Departamento?
	// El referal code es el identificador unico del proyecto?
	//En funcion del referall code se mete en un equipo
	// cuando creo el usuario podria añadir tarjetas del proyecto asociado
	// Cambiar el doGet al doPost
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");//Esto lo cambiare para guardar un Hash
		String project_code=req.getParameter("project_code");
		
		
		//Busco el proyecto asociado al referal code
		Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));
		if (proyecto==null) {
			System.out.println("No existe ese project_code");
			//Redireccionar al Login con codigo de error: PROJECT CODE DOESNT EXIST
			resp.sendRedirect(req.getContextPath()+"/signup.jsp");
		}else {
			Usuario usuario= new Usuario();
			usuario.setNombre(name);
			usuario.setEmail(email);
			usuario.setContraseña(password);
			usuario.setRol(3); //Tiene rol de trabajador
			//Faltan sets
			
			//Creo el usuario
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
			//Creo el contrato entre proyecto-usuario
			Contrato contrato = new Contrato();
			contrato.setUsuario(usuario);
			contrato.setProyecto(proyecto);
			ContratoDAOImplementation.getInstance().createContrato(contrato);
				
			//Redireccionar a la vista del usuario
			req.getSession().setAttribute("role",usuario.getRol());
		}
		
	}
}
