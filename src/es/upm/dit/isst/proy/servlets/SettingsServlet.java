package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.util.cryptographicHash;

@WebServlet("/SettingsServlet")
public class SettingsServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuario(email);
		user.setNombre(name);
		user.setContraseña(cryptographicHash.getMD5(password));
		user.setEmail(email);
		UsuarioDAOImplementation.getInstance().updateUsuario(user);
		req.getSession().setAttribute("user", user);
		resp.sendRedirect(req.getContextPath());
	}

}
