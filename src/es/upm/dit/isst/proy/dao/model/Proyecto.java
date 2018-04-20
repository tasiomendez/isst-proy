package es.upm.dit.isst.proy.dao.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proyecto {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFinal;
	private boolean acabado; 

	private int project_code;
	
	@OneToMany(mappedBy="proyecto", fetch = FetchType.EAGER) 
	private Set<Tarea> tareas;
	
	@OneToMany(mappedBy="proyecto", fetch = FetchType.EAGER)
	private Set<Contrato> contratos;
	
	
	public Proyecto() {
		//id+=1;
		//tarjetas=new ArrayList<>();
	}
	public int getId() {
		return id;
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public boolean getAcabado() {
		return acabado;
	}
	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}
	
	public int getProject_code() {
		return project_code;
	}
	public void setProject_code(int project_code) {
		this.project_code = project_code;
	}
	public Set<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}
	public Set<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(Set<Contrato> contratos) {
		this.contratos = contratos;
	}
}
