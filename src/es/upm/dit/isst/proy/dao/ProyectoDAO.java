/**
 * 
 */
package es.upm.dit.isst.proy.dao;

import java.util.List;

import es.upm.dit.isst.proy.dao.model.Proyecto;

/**
 * @author dsuarezsouto
 *
 */
public interface ProyectoDAO {
	
	public void createProyecto(Proyecto proyecto);
	public Proyecto readProyecto(int id);
	public Proyecto readProyectoFromProjectCode(int project_code);
	public void updateProyecto(Proyecto proyecto);
	public void deleteProyecto(Proyecto proyecto);
	//public Proyecto loginUsusario(String email, String password);
	public List<Proyecto> readAllProyectos();
}
