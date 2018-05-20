package es.upm.dit.isst.proy.dao.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Proyecto {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String titulo;
	private String descripcion;
	private String fechaInicio;
	private String fechaFinal;
	private boolean acabado; 
	private int project_code;
	private double percentage;

	@Lob
	private byte[] document;
	
	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	@OneToMany(mappedBy="proyecto", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER) 
	private Set<Tarea> tareas;
	
	@OneToMany(mappedBy="proyecto", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
	private Set<Contrato> contratos;
	
	public Proyecto() {}
	
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
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFinal() {
		return fechaFinal;
	}
	
	public void setFechaFinal(String fechaFinal) {
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
	
	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	
	@Override
	public String toString() {
		String result=titulo + "\\&\\" + descripcion;
		return result;
	}
}
