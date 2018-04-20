package es.upm.dit.isst.proy.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Job {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private int horas_hechas=0;
	private int horas_planificadas=0;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Tarea tarea;
	
	public Job() {
		//id+=1;
		}
	
	public int getId() {
		return id;
	}

	public int getHoras_hechas() {
		return horas_hechas;
	}

	public void setHoras_hechas(int horas_hechas) {
		this.horas_hechas = horas_hechas;
	}

	public int getHoras_planificadas() {
		return horas_planificadas;
	}

	public void setHoras_planificadas(int horas_planificadas) {
		this.horas_planificadas = horas_planificadas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}
