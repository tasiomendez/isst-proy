package es.upm.dit.isst.proy.dao.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Tarea {

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String titulo;
	private String descripcion;
	//hay que añadir un att estado
	private int planned_hours;
	
	@OneToMany(mappedBy="tarea",fetch=FetchType.EAGER)
	private List<Job> jobs;
	@ManyToOne
	private Proyecto proyecto;
	
	

	

	public Tarea() {
		//id+=1;
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

	public int getPlanned_hours() {
		return planned_hours;
	}

	public void setPlanned_hours(int planned_hours) {
		this.planned_hours = planned_hours;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
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

}
