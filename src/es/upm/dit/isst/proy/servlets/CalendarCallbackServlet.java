package es.upm.dit.isst.proy.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;

import es.upm.dit.isst.proy.dao.UsuarioDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/oauth2callback")
public class CalendarCallbackServlet extends AbstractAuthorizationCodeCallbackServlet {

	@Override
	protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
			throws ServletException, IOException {
		String email = (String) req.getSession().getAttribute("email");
		Usuario user = UsuarioDAOImplementation.getInstance().readUsuario(email);
		if (user.getIdCalendar() != null) {
			RequestDispatcher rd = req.getRequestDispatcher("EventListServlet");
			rd.forward(req,resp);
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("CalendarListServlet");
			rd.forward(req,resp);
		}
	}

	@Override
	protected void onError(
			HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
					throws ServletException, IOException {
		resp.getWriter().print("<h3>Why don't you want to play with me?</h3>");
		resp.setStatus(200);
		resp.addHeader("Content-Type", "text/html");
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		return CalendarAPI.getInstance().getRedirectUri(req);
	}

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws IOException {
		return CalendarAPI.getInstance().flow();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		return (String) req.getSession().getAttribute("email");
	}
}

