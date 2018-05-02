package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Usuario;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email= req.getParameter("email");
		
		if (UsuarioDAOImplementation.getInstance().readUsuario(email) != null) {
			req.getSession().setAttribute("user_error", "Este usuario ya está registrado.");
			resp.sendRedirect(req.getContextPath() + "/admin.jsp");
		} else {
			String name= req.getParameter("name");
			String password= req.getParameter("password");
			String role = req.getParameter("role");
			Usuario usuario =new Usuario();
			usuario.setEmail(email);
			usuario.setNombre(name);
			usuario.setContraseña(password);
			usuario.setRol(Integer.parseInt(role));
			
			UsuarioDAOImplementation.getInstance().createUsuario(usuario);
			req.getSession().setAttribute("user_success", "Usuario creado con éxito.");
			resp.sendRedirect(req.getContextPath() + "/admin.jsp");
		}
	}
}
