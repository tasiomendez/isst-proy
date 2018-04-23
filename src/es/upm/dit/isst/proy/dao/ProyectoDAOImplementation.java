package es.upm.dit.isst.proy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Proyecto;

public class ProyectoDAOImplementation implements ProyectoDAO{

	private static ProyectoDAOImplementation instance;
	private ProyectoDAOImplementation() {};
	public static ProyectoDAOImplementation getInstance() {
		if(null== instance) {
			instance = new ProyectoDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public void createProyecto(Proyecto proyecto) {
		Session session = SessionFactoryService.get().openSession();
		try {
		     session.beginTransaction();
		     session.save(proyecto);
		     session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}
		
	}

	@Override
	public Proyecto readProyecto(int id) {
		Proyecto proyecto = null;
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	proyecto = session.get(Proyecto.class, id);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return proyecto;
	}

	@Override
	public void updateProyecto(Proyecto proyecto) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.saveOrUpdate(proyecto);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		
	}

	@Override
	public void deleteProyecto(Proyecto proyecto) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.delete(proyecto);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
	}

	@Override
	public List<Proyecto> readAllProyectos() {
		List<Proyecto> proyectos = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	proyectos.addAll(
        			session.createQuery("from Proyecto").list()
        	);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return proyectos;
	}
	@Override
	public Proyecto readProyectoFromProjectCode(int project_code) {
		Proyecto proyecto = null;
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	proyecto = (Proyecto) session.createQuery("select p from Proyecto p where p.project_code= :code")
        			.setParameter("code", project_code)
        			.getSingleResult();
        	session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e);
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return proyecto;
	}

}
