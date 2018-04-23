package es.upm.dit.isst.proy.dao;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Contrato;

public class ContratoDAOImplementation implements ContratoDAO {

	private static ContratoDAOImplementation instance;
	private ContratoDAOImplementation() {};
	public static ContratoDAOImplementation getInstance() {
		if(instance==null) {
			instance = new ContratoDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public void createContrato(Contrato contrato) {
		Session session = SessionFactoryService.get().openSession();
		try {
		     session.beginTransaction();
		     session.save(contrato);
		     session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}		
	}

	@Override
	public Contrato readContrato(int id) {
		Contrato contrato = null;
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	contrato = session.get(Contrato.class, id);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return contrato;
	}

	@Override
	public void updateContrato(Contrato contrato) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.saveOrUpdate(contrato);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		
	}

	@Override
	public void deleteContrato(Contrato contrato) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.delete(contrato);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		
	}
	

}
