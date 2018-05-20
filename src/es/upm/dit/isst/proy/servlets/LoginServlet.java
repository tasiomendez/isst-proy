package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.util.cryptographicHash;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute("email") != null) {
			Usuario usuario = UsuarioDAOImplementation.getInstance().readUsuario((String) req.getSession().getAttribute("email"));
			reloadSession(req, resp, usuario);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email= req.getParameter("email");
		String password = cryptographicHash.getMD5(req.getParameter("password"));
		Usuario usuario = UsuarioDAOImplementation.getInstance().loginUsuario(email, password);

		if (usuario == null) {
			//Redireccionamos de nuevo a login
			if (UsuarioDAOImplementation.getInstance().readUsuario(email) != null)
				req.getSession().setAttribute("error", "La contraseña es incorrecta.");
			else
				req.getSession().setAttribute("error", "El correo introducido es incorrecto.");
			resp.sendRedirect(req.getContextPath());

		} else {
			reloadSession(req, resp, usuario);
			
		}
	}
	
	private void reloadSession(HttpServletRequest req, HttpServletResponse resp, Usuario usuario) throws ServletException, IOException {
		req.getSession().setAttribute("role",usuario.getRol());
		req.getSession().setAttribute("email",usuario.getEmail());

		ArrayList<Contrato> contratos = new ArrayList<Contrato>();
		contratos.addAll(usuario.getContratos());
		Proyecto[] proyecto = new Proyecto[contratos.size()];
		for(int i=0;i<contratos.size();i++) {
			proyecto[i]=contratos.get(i).getProyecto();
		}
		req.getSession().setAttribute("project_list",proyecto );
		req.getSession().setAttribute("name",usuario.getNombre());
		req.getSession().setAttribute("user", usuario);
		ArrayList<Usuario> list_trabajador = (ArrayList<Usuario>) UsuarioDAOImplementation.getInstance().readAllUsuario(3);
		req.getSession().setAttribute("trabajador_list", list_trabajador);
		req.getSession().setAttribute("calendar_id", usuario.getIdCalendar());

		loadEvents(req, resp, usuario);
	}

	private void loadEvents(HttpServletRequest req, HttpServletResponse resp, Usuario usuario) throws ServletException, IOException {
		if (usuario.getIdCalendar() != null) {
			try {
				RequestDispatcher rd = req.getRequestDispatcher("EventListServlet");
				rd.forward(req,resp);
			} catch (Exception e) {
				RequestDispatcher rd = req.getRequestDispatcher("CalendarServlet");
				rd.forward(req,resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath());
		}
	}
}

