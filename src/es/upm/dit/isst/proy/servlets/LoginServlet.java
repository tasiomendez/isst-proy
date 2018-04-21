package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email= req.getParameter("email");
		String password = req.getParameter("password");
		System.out.println("email: "+email);
		System.out.println("pwd: "+password);
		Usuario usuario=UsuarioDAOImplementation.getInstance().loginUsuario(email, password);
		
		
		
		if(usuario == null) {
			System.out.print("Usuario no registrado");
			//Redireccionamos de nuevo a login (Podemos hacerlo con un codigo de error para mostrar un mensaje de usuario no registrado)
			req.setAttribute("error","Usuario no registrado.");
			
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
			req.getSession().setAttribute("role",usuario.getRol());
			req.getSession().setAttribute("email",usuario.getEmail());
			
			ArrayList<Contrato> contratos = new ArrayList<Contrato>();
			contratos.addAll(usuario.getContratos());
			Proyecto[] proyecto = new Proyecto[contratos.size()];
			for(int i=0;i<contratos.size();i++) {
				proyecto[i]=contratos.get(i).getProyecto();
			}
			req.getSession().setAttribute("project_list",proyecto );
			/*Borrar*/
			ArrayList<Usuario> list_trabajador=(ArrayList<Usuario>) UsuarioDAOImplementation.getInstance().readAllUsuario();
			req.getSession().setAttribute("trabajador_list", list_trabajador);
			//resp.sendRedirect(req.getContextPath());
			resp.sendRedirect(req.getContextPath()+"/dashboard.jsp");
		
		}
		
			
		
		
	}
}
