package es.upm.dit.isst.proy.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.isst.proy.dao.model.Contrato;
import es.upm.dit.isst.proy.dao.model.Proyecto;
import es.upm.dit.isst.proy.dao.model.Usuario;

public class UsuarioDAOTest {

	private Usuario user=null;
	private UsuarioDAOImplementation dao =null;
	@Before
	public void setUp() throws Exception {
		user=new Usuario();
		user.setNombre("name");
		user.setEmail("email");
		user.setContraseña("password");
		user.setRol(1);
		
		dao=UsuarioDAOImplementation.getInstance();
		dao.createUsuario(user);
		
		
	}

	@After
	public void tearDown() throws Exception {
		user=dao.readUsuario("email");
		dao.deleteUsuario(user);
		user=null;
		dao=null;
				
	}

	@Test
	public void testCreateUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name1");
		user1.setEmail("email1");
		user1.setContraseña("password1");
		user1.setRol(1);
		
		dao.createUsuario(user1);
		Usuario user2=dao.readUsuario("email1");
		dao.deleteUsuario(user1);
		assertEquals(user1.getNombre(),user2.getNombre());
		assertEquals(user1.getEmail(),user2.getEmail());
		assertEquals(user1.getContraseña(),user2.getContraseña());
		assertEquals(user1.getRol(),user2.getRol());
		
	}

	@Test
	public void testReadUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name1");
		user1.setEmail("email1");
		user1.setContraseña("password1");
		user1.setRol(1);
		
		dao.createUsuario(user1);
		
		Usuario user2=dao.readUsuario("email1");
		dao.deleteUsuario(user1);
		assertEquals(user1.getNombre(),user2.getNombre());
		assertEquals(user1.getEmail(),user2.getEmail());
		assertEquals(user1.getContraseña(),user2.getContraseña());
		assertEquals(user1.getRol(),user2.getRol());
		
	}

	@Test
	public void testUpdateUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name1");
		user1.setEmail("email1");
		user1.setContraseña("password1");
		user1.setRol(1);
		dao.createUsuario(user1);
		
		Usuario user2=dao.readUsuario("email1");
		user2.setNombre("name2");
		user2.setContraseña("password2");
		user2.setRol(2);
		dao.updateUsuario(user2);
		
		Usuario user3=dao.readUsuario("email1");
		dao.deleteUsuario(user2);
		assertEquals(user2.getNombre(),user3.getNombre());
		assertEquals(user2.getEmail(),user3.getEmail());
		assertEquals(user2.getContraseña(),user3.getContraseña());
		assertEquals(user2.getRol(),user3.getRol());
		
	}

	@Test
	public void testDeleteUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name1");
		user1.setEmail("email1");
		user1.setContraseña("password1");
		user1.setRol(1);
		dao.createUsuario(user1);
		
		dao.deleteUsuario(user1);
		assertNull(dao.readUsuario(user1.getEmail()));
	}

	@Test
	public void testLoginUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name3");
		user1.setEmail("email3");
		user1.setContraseña("password3");
		user1.setRol(1);
		dao.createUsuario(user1);
		
		Usuario user2=dao.loginUsuario(user1.getEmail(), user1.getContraseña());
		dao.deleteUsuario(user1);
		
		assertEquals(user1.getNombre(),user2.getNombre());
		assertEquals(user1.getEmail(),user2.getEmail());
		assertEquals(user1.getContraseña(),user2.getContraseña());
		assertEquals(user1.getRol(),user2.getRol());
		
	}

	@Test
	public void testReadAllUsuario() {
		Usuario user1=new Usuario();
		user1.setNombre("name3");
		user1.setEmail("email3");
		user1.setContraseña("password3");
		user1.setRol(1);
		dao.createUsuario(user1);
		
		Usuario user2=new Usuario();
		user2.setNombre("name4");
		user2.setEmail("email4");
		user2.setContraseña("password4");
		user2.setRol(2);
		dao.createUsuario(user2);
		
		List<Usuario> users=dao.readAllUsuario();
		ArrayList<Usuario> users_expected=new ArrayList<Usuario>();
		users_expected.add(user);
		users_expected.add(user1);
		users_expected.add(user2);
		dao.deleteUsuario(user1);
		dao.deleteUsuario(user2);
		
		for(Usuario u:users)
			System.out.println(u.getEmail());
		for(int i =0;i<users.size();i++)
			assertEquals(users_expected.get(i).getNombre(), users.get(i).getNombre());
		
		
		
	}

	@Test
	public void testReadAllUsuarioInt() {
		Usuario user1=new Usuario();
		user1.setNombre("name3");
		user1.setEmail("email3");
		user1.setContraseña("password3");
		user1.setRol(2);
		dao.createUsuario(user1);
		
		Usuario user2=new Usuario();
		user2.setNombre("name4");
		user2.setEmail("email4");
		user2.setContraseña("password4");
		user2.setRol(2);
		dao.createUsuario(user2);
		
		List<Usuario> users=dao.readAllUsuario(2);
		ArrayList<Usuario> users_expected=new ArrayList<Usuario>();
		users_expected.add(user1);
		users_expected.add(user2);
		dao.deleteUsuario(user1);
		dao.deleteUsuario(user2);
		
		
		for(int i =0;i<users.size();i++)
			assertEquals(users_expected.get(i).getNombre(), users.get(i).getNombre());
		
		
		
		
	}

}
