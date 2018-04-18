/**
 * 
 */
package es.upm.dit.isst.proy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Usuario;


/**
 * @author dsuarezsouto
 *
 */
public class UsuarioDAOImplementation implements UsuarioDAO{

	private static UsuarioDAOImplementation instance;
	private UsuarioDAOImplementation() {};
	public static UsuarioDAOImplementation getInstance() {
		if(null== instance) {
			instance = new UsuarioDAOImplementation();
		}
		return instance;
	}
	
	@Override
	public void createUsuario(Usuario usuario) {
		Session session = SessionFactoryService.get().openSession();
		try {
		     session.beginTransaction();
		     session.save(usuario);
		     session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}
		
	}

	@Override
	public Usuario readUsuario(String email) {
		Usuario usuario = null;
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	usuario = session.get(Usuario.class, email);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return usuario;
	}

	@Override
	public void updateUsuario(Usuario usuario) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.saveOrUpdate(usuario);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	session.delete(usuario);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		
	}

	@Override
	public Usuario loginUsuario(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		Usuario usuario = null;
		try {
        	session.beginTransaction();
        	usuario = (Usuario) session.createQuery("select p from Usuario p where p.email = :email and p.password = :password")
        		.setParameter("email", email)
	        	.setParameter("password", password)
	        	.getSingleResult();
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return usuario;
	}
}

