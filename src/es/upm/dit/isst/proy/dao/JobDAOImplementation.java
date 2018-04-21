package es.upm.dit.isst.proy.dao;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Job;
import es.upm.dit.isst.proy.dao.model.Job;

public class JobDAOImplementation implements JobDAO {
	
	private static JobDAOImplementation instance;
	private JobDAOImplementation() {};
	public static JobDAOImplementation getInstance() {
		if(instance==null) {
			instance = new JobDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public void createJob(Job job) {
		Session session = SessionFactoryService.get().openSession();
		try {
		     session.beginTransaction();
		     session.save(job);
		     session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}		

	}

	@Override
	public Job readJob(int id) {
		Job job = null;
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	job = session.get(Job.class, id);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return job;
	}

	@Override
	public void updateJob(Job job) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.saveOrUpdate(job);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}

	}

	@Override
	public void deleteJob(Job job) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.delete(job);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}

	}

}
