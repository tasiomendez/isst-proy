package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.http.GenericUrl;
import es.upm.dit.isst.proy.util.CalendarAPI;

@WebServlet("/CalendarServlet")
public class CalendarServlet extends AbstractAuthorizationCodeServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
	
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


