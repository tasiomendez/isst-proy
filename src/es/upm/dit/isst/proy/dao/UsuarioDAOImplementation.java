/**
 * 
 */
package es.upm.dit.isst.proy.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Usuario;
import es.upm.dit.isst.proy.dao.SessionFactoryService;


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
        	usuario = (Usuario) session.createQuery("select u from Usuario u where u.email = :email and u.contraseña = :password")
        		.setParameter("email", email)
	        	.setParameter("password", password)
	        	.getSingleResult();
        	session.getTransaction().commit();
		} catch (Exception e) {
		     System.out.println(e);
		} finally {
		    session.close();
		}
		return usuario;
	}
	@Override
	public List<Usuario> readAllUsuario() {
		List<Usuario> professors = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	professors.addAll(
        			session.createQuery("from Usuario").list()
        	);
        	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		    session.close();
		}
		return professors;
	}
	@Override
	public List<Proyecto> readAllProyectoFromUser(String email) {
		List<Proyecto> proyectos = new ArrayList<>();
		Session session = SessionFactoryService.get().openSession();
		try {
        	session.beginTransaction();
        	proyectos.addAll(
        			session.createQuery("from Proyecto as p INNER JOIN Contrato as c inner join Usuario as u where p.id=c.proyecto_id and c.usuario_email=u.email and u.email : email")
        			.setParameter("email",email)
        			.list()
        	);
        	session.getTransaction().commit();
		} catch (Exception e) {
		         System.out.println(e);   	// manejar excepciones
		} finally {
		    session.close();
		}
		return proyectos;
	}
	
	
}
