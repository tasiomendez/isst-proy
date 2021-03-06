package es.upm.dit.isst.proy.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import es.upm.dit.isst.proy.dao.ProyectoDAOImplementation;
import es.upm.dit.isst.proy.dao.TareaDAOImplementation;
import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Usuario;

@MultipartConfig
@WebServlet("/kanban/ImportTareasServlet")
public class ImportTareasServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String project_code= (String) req.getSession().getAttribute("project_code");
		Part filePart = req.getPart("file");
		InputStream fileContent = filePart.getInputStream();

		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(fileContent);
			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			/* Listas con la informacion de las tareas */
			String titulo = new String();
			String descripcion = new String();
			String fechaEntrega = new String();
			String horas = new String();
			String trabajador_email = new String();
			ArrayList<String> error_tareas_vacias =new ArrayList<String>();
			ArrayList<String> error_tareas_trabajador =new ArrayList<String>();

			String response="";

			Proyecto proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));

			ArrayList<Contrato>contratos=new ArrayList<Contrato>();
			contratos.addAll(proyecto.getContratos());

			for(int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row=sheet.getRow(i);
				titulo=dataFormatter.formatCellValue(row.getCell(0));
				descripcion=dataFormatter.formatCellValue(row.getCell(1));
				fechaEntrega=dataFormatter.formatCellValue(row.getCell(2));
				horas=dataFormatter.formatCellValue(row.getCell(3));
				trabajador_email=dataFormatter.formatCellValue(row.getCell(4));
				if(titulo.isEmpty()||descripcion.isEmpty()||
						fechaEntrega.isEmpty()||horas.isEmpty()||trabajador_email.isEmpty()) {
					error_tareas_vacias.add(titulo);
					continue;
				}
				Usuario trabajador=null;
				for(int j=0;j<contratos.size();j++) {
					Contrato contrato=contratos.get(j);
					Usuario usr=contrato.getUsuario();
					if(usr.getEmail().equals(trabajador_email)) {
						trabajador = usr;
						break;
					}
				}
				if(trabajador == null){
					error_tareas_trabajador.add(titulo);
					continue;
				}

				Tarea tarea = new Tarea();
				tarea.setTitulo(titulo);
				tarea.setDescripcion(descripcion);
				tarea.setFecha_entrega(fechaEntrega);
				tarea.setUsuario(trabajador);
				tarea.setEstado("todo");
				tarea.setPlanned_hours(Integer.parseInt(horas));
				tarea.setWorked_hours(0);        		
				tarea.setProyecto(proyecto);
				TareaDAOImplementation.getInstance().createTarea(tarea);
			}
			if(error_tareas_trabajador.isEmpty()==false)
				req.getSession().setAttribute("error_tareas_trabajador", error_tareas_trabajador);
			if(error_tareas_vacias.isEmpty()==false)
				req.getSession().setAttribute("error_tareas_vacias", error_tareas_vacias);

			for(int i=0;i<error_tareas_trabajador.size();i++) {
				if(i==error_tareas_trabajador.size()-1)
					response+=error_tareas_trabajador.get(i);
				else
					response+=error_tareas_trabajador.get(i)+"\\&\\";
			}
			response+="//&//";
			for(int i=0;i<error_tareas_vacias.size();i++) {
				if(i==error_tareas_vacias.size()-1)
					response+=error_tareas_vacias.get(i);
				else
					response+=error_tareas_vacias.get(i)+"\\&\\";
			}



			proyecto=ProyectoDAOImplementation.getInstance().readProyectoFromProjectCode(Integer.parseInt(project_code));

			//Modificamos el porcentaje de proyecto hecho
			ArrayList<Tarea> tareas=new ArrayList<Tarea>();
			tareas.addAll(proyecto.getTareas());
			double count_done=0;
			for (Tarea t : tareas) {
				if(t.getEstado().equals("done"))
					count_done++;
			}
			double percentage=count_done/(double)tareas.size();
			proyecto.setPercentage(percentage);
			ProyectoDAOImplementation.getInstance().updateProyecto(proyecto);			
			req.getSession().setAttribute("tareas_list", tareas);
			resp.getWriter().write(response);

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

	}
}
