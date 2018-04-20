package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title= req.getParameter("title");
		String description=req.getParameter("description");
		String initialDate=req.getParameter("initialDate");
		System.out.println(initialDate);
		String finalDate=req.getParameter("finalDate");
		//Email del gestor
		String email = (String) req.getSession().getAttribute("email");
		Usuario gestor = UsuarioDAOImplementation.getInstance().readUsuario(email);
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		Date date_start=new Date();
		Date date_end=new Date();
		try {
			date_start = format.parse(initialDate);
			date_end= format.parse(finalDate);
		} catch (ParseException e) {
			System.out.println("Error al parsear fechas");
			resp.sendRedirect(req.getContextPath()+"/create.jsp");
			//Redireccionar con error
			e.printStackTrace();
		}
		
		
		Proyecto proyecto=new Proyecto();
		System.out.println("ID de proyecto:"+proyecto.getId());
		proyecto.setTitulo(title);
		proyecto.setDescripcion(description);
		proyecto.setFechaInicio(date_start);
		proyecto.setFechaFinal(date_end);
		//Project-code: hash del titulo y de la fecha de creacion
		Date today = new Date();		
		String code= title+today.toString();
		int hash = Math.abs(code.hashCode()%1000);
		proyecto.setProject_code(hash);
		proyecto.setAcabado(false);
		
		ProyectoDAOImplementation.getInstance().createProyecto(proyecto);
		
		//Crear un Contrato del Gestor con el proyecto creado
		Contrato contrato=new Contrato();
		System.out.println("ID de contrato:"+contrato.getId());
		contrato.setProyecto(proyecto);
		contrato.setUsuario(gestor);
		ContratoDAOImplementation.getInstance().createContrato(contrato);
		
		resp.sendRedirect(req.getContextPath()+"/dashboard.jsp");
		
		
	}
}
