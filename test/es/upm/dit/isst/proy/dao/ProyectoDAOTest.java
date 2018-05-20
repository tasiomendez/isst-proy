package es.upm.dit.isst.proy.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.upm.dit.isst.proy.dao.model.Proyecto;

public class ProyectoDAOTest {

	private Proyecto project=null;
	private ProyectoDAOImplementation dao=null;
	
	@Before
	public void setUp() throws Exception {
		
		project=new Proyecto();
		project.setTitulo("title");
		project.setDescripcion("description");
		project.setProject_code(1);
		project.setAcabado(false);
		
		dao=ProyectoDAOImplementation.getInstance();
		
		dao.createProyecto(project);
	}

	@After
	public void tearDown() throws Exception {
		project=dao.readProyectoFromProjectCode(1);
		dao.deleteProyecto(project);
		project=null;
		dao=null;
	}

	@Test
	public void testCreateProyecto() {
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		Proyecto project2=dao.readProyectoFromProjectCode(project1.getProject_code());
		dao.deleteProyecto(project1);
		
		assertEquals(project1.getTitulo(),project2.getTitulo());
		assertEquals(project1.getDescripcion(),project2.getDescripcion());
		assertEquals(project1.getProject_code(),project2.getProject_code());
		assertEquals(project1.getAcabado(),project2.getAcabado());
		
	}

	@Test
	public void testReadProyecto() {
		
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		Proyecto project2=dao.readProyecto(project1.getId());
		dao.deleteProyecto(project1);
		
		assertEquals(project1.getTitulo(),project2.getTitulo());
		assertEquals(project1.getDescripcion(),project2.getDescripcion());
		assertEquals(project1.getProject_code(),project2.getProject_code());
		assertEquals(project1.getAcabado(),project2.getAcabado());
	}

	@Test
	public void testUpdateProyecto() {
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		Proyecto project2=dao.readProyecto(project1.getId());
		project2.setTitulo("title2");
		project2.setDescripcion("description2");
		project2.setAcabado(true);
		
		dao.updateProyecto(project2);
		
		Proyecto project3=dao.readProyecto(project1.getId());
		dao.deleteProyecto(project2);
		
		assertEquals(project2.getTitulo(),project3.getTitulo());
		assertEquals(project2.getDescripcion(),project3.getDescripcion());
		assertEquals(project2.getProject_code(),project3.getProject_code());
		assertEquals(project2.getAcabado(),project3.getAcabado());
	}

	@Test
	public void testDeleteProyecto() {
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		dao.deleteProyecto(project1);
		assertNull(dao.readProyecto(project1.getId()));
		
		
	}

	@Test
	public void testReadAllProyectos() {
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		List<Proyecto> projects=dao.readAllProyectos();
		ArrayList<Proyecto> projects_expected=new ArrayList<Proyecto>();
		projects_expected.add(project);
		projects_expected.add(project1);
		dao.deleteProyecto(project1);
		
		for(int i=0;i<projects.size();i++)
			assertEquals(projects_expected.get(i).getProject_code(), projects.get(i).getProject_code());
		
	}

	@Test
	public void testReadProyectoFromProjectCode() {
		Proyecto project1=new Proyecto();
		project1.setTitulo("title1");
		project1.setDescripcion("description1");
		project1.setProject_code(2);
		project1.setAcabado(false);
		
		dao.createProyecto(project1);
		
		Proyecto project2=dao.readProyectoFromProjectCode(project1.getProject_code());
		dao.deleteProyecto(project1);
		
		assertEquals(project1.getTitulo(),project2.getTitulo());
		assertEquals(project1.getDescripcion(),project2.getDescripcion());
		assertEquals(project1.getProject_code(),project2.getProject_code());
		assertEquals(project1.getAcabado(),project2.getAcabado());
		
		
	}

}
