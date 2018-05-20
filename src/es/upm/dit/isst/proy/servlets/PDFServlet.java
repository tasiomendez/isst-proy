package es.upm.dit.isst.proy.servlets;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Usuario;

@MultipartConfig
@WebServlet("/PDFServlet")
public class PDFServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ServletOutputStream sout = resp.getOutputStream();

		//Elementos a imprimir del proyecto
		int id = Integer.parseInt(req.getParameter("project_id"));
		Proyecto project = ProyectoDAOImplementation.getInstance().readProyecto(id);
		Set <Tarea> tareas = project.getTareas();

		ArrayList <Contrato> contratos = new ArrayList<Contrato>();
		ArrayList <Usuario> usuarios = new ArrayList<Usuario>();
		contratos.addAll(project.getContratos());
		for(Contrato c : contratos) {
			usuarios.add(c.getUsuario());
		}

		try {

			Calendar fecha = new GregorianCalendar();
			int año = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			int hora = fecha.get(Calendar.HOUR_OF_DAY);
			int minuto = fecha.get(Calendar.MINUTE);

			int cont_i = 1;
			int cont_j = 1;
			int cont_k = 1;
			PdfReader reader = new PdfReader("/InformePlantilla.pdf");


			//PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));	
			PdfStamper stamper = new PdfStamper(reader, baos);
			AcroFields form = stamper.getAcroFields();
			form.removeXfa();
			form.setField("project_name", project.getTitulo());
			form.setField("initial_date", project.getFechaInicio());
			form.setField("end_date", project.getFechaFinal());
			form.setField("project_description", project.getDescripcion());
			form.setField("inform_date", Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año)+"  "+Integer.toString(hora)+":"+Integer.toString(minuto));
			form.setField("project_progress", Double.toString(project.getPercentage() * 100) + " %");

			for (int i = 1;i<23;i++){
				for (Usuario u: usuarios) {
					if(u.getRol() == 3) {

						form.setField("trabajador"+i+"","- "+ u.getNombre());
						usuarios.remove(u);
						break;
					}	  			
				}
			}

			for (Tarea t : tareas) {

				if (t.getEstado().equals("todo")) {

					for (int i = cont_i;i<13;i++){
						form.setField("toDo"+i+"", t.getTitulo()+"  ("+t.getWorked_hours()+"/"+t.getPlanned_hours()+")");
						cont_i++;
						break;
					}

				}
				if (t.getEstado().equals("doing")) {
					for (int j = cont_j;j<13;j++){
						form.setField("doing"+j+"", t.getTitulo()+"  ("+t.getWorked_hours()+"/"+t.getPlanned_hours()+")");
						cont_j++;
						break;
					}
				}
				if (t.getEstado().equals("done")) {
					for (int k = cont_k;k<13;k++){
						form.setField("done"+k+"", t.getTitulo()+"  ("+t.getWorked_hours()+"/"+t.getPlanned_hours()+")");
						cont_k++;
						break;
					}
				}
			}

			stamper.setFormFlattening(true);
			stamper.close();
			reader.close();
			project.setDocument(baos.toByteArray());
			ProyectoDAOImplementation.getInstance().updateProyecto(project); 
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		resp.setContentType("application/pdf");
		resp.setContentLength(baos.size());
		baos.writeTo(sout);

	}




}