package es.upm.dit.isst.proy.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contrato {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Proyecto proyecto;
	
	public Contrato() {}
	
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
	
	public int getId() {
		return id;
	}

}
