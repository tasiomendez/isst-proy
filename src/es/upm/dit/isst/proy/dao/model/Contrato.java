package es.upm.dit.isst.proy.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contrato {




	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	
	/*@OneToMany(mappedBy="equipo", fetch = FetchType.EAGER) //Un equipo tiene muchos usuarios
	private Set<Usuario> usuarios;
	
	@OneToMany(mappedBy="equipo", fetch = FetchType.EAGER) // Un equipo tiene muchos proyectos
	private Set<Proyecto> proyectos;*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Proyecto proyecto;
	public Contrato() {
			
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
