package es.upm.dit.isst.proy.dao;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Tarea;
import es.upm.dit.isst.proy.dao.model.Tarea;

public class TareaDAOImplementation implements TareaDAO {
	private static TareaDAOImplementation instance;
	private TareaDAOImplementation() {};
	public static TareaDAOImplementation getInstance() {
		if(instance==null) {
			instance = new TareaDAOImplementation();
		}
		return instance;
	}

	@Override
	public void createTarea(Tarea tarea) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(tarea);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}		

	}

	@Override
	public Tarea readTarea(int id) {
		Tarea tarea = null;
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			tarea = session.get(Tarea.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return tarea;
	}

	@Override
	public void updateTarea(Tarea tarea) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(tarea);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void deleteTarea(Tarea tarea) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(tarea);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
