package es.upm.dit.isst.proy.dao.model;

import java.util.LinkedHashSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Contrato {

	@Id
	private int id=0;
	
	
	/*@OneToMany(mappedBy="equipo", fetch = FetchType.EAGER) //Un equipo tiene muchos usuarios
	private Set<Usuario> usuarios;
	
	@OneToMany(mappedBy="equipo", fetch = FetchType.EAGER) // Un equipo tiene muchos proyectos
	private Set<Proyecto> proyectos;*/
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Proyecto proyecto;
	public Contrato() {
		id+=1;
			
	}
	public int getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	


	

	/*public int getUsuarioEmail() {
		return usuarioEmail;
	}
	public void setUsuarioEmail(int usuarioEmail) {
		this.usuarioEmail = usuarioEmail;
	}*/
	/*public int getProyectoID() {
		return proyectoID;
	}

	public void setProyectoID(int proyectoID) {
		this.proyectoID = proyectoID;
	}
*/
	
	
	
	
}
