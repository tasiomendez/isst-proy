package es.upm.dit.isst.proy.dao;

import es.upm.dit.isst.proy.dao.model.Tarea;

public interface TareaDAO {
	public void createTarea(Tarea tarea);
	public Tarea readTarea(int id);
	public void updateTarea(Tarea tarea);
	public void deleteTarea(Tarea tarea);
	//public List<Tarea> readAllTareaofUser(String email, String password);
	//public List<Professor> readAllProfessor();
}
