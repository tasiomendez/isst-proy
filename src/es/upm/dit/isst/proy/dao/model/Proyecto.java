package es.upm.dit.isst.proy.dao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Proyecto {
	@Id
	private int id=0;
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFinal;
	private boolean acabado; 

	private int project_code;
	
	/*@OneToMany(mappedBy="proyecto", fetch = FetchType.EAGER) //DONE 
	private List<Tarjeta> tarjetas;*/
	
	@OneToMany(mappedBy="proyecto", fetch = FetchType.EAGER)
	private List<Contrato> contratos;
	
	public Proyecto() {
		id+=1;
		//tarjetas=new ArrayList<>();
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
	
}
