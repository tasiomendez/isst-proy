package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
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
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}else{
			req.getSession().setAttribute("role",usuario.getRol());
			resp.sendRedirect(req.getContextPath()+"/dashboard.jsp");
		
		}
		
			
		
		
	}
}
