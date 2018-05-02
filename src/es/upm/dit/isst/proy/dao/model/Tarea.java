package es.upm.dit.isst.proy.dao.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Tarea {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String titulo;
	private String descripcion;
	private int planned_hours;
	private String estado;
	private int worked_hours;
	private String fecha_entrega;

	@ManyToOne
	private Proyecto proyecto;
	
	@ManyToOne
	private Usuario usuario;

	public Tarea() {}
	
	public String getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(String fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	
	public int getWorked_hours() {
		return worked_hours;
	}

	public void setWorked_hours(int worked_hours) {
		this.worked_hours = worked_hours;
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}

	public int getPlanned_hours() {
		return planned_hours;
	}

	public void setPlanned_hours(int planned_hours) {
		this.planned_hours = planned_hours;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

}