package es.upm.dit.isst.proy.dao.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	
	private String nombre;
	@Id
	private String email;
	private String contraseña;
	private int rol;
	private int edad;
	private String disponibilidad;
	private String departamento;
	
	/*@OneToMany(mappedBy="usuarioDestino", fetch = FetchType.EAGER) //DONE destino usuario
	private List<Notificacion> notificaciones;
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)
	private List<Tarea> tareas;*/
	
	@OneToMany(mappedBy="usuario", fetch=FetchType.EAGER)
	private List<Contrato> contratos;
	/*@ManyToMany(mappedBy="usuario")
	private List<Tarea> tareas;*/
	
	public Usuario() {
		//notificaciones=new ArrayList<>();
		//tareas=new ArrayList<>();
		
	}

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}



	public List<Contrato> getContratos() {
		return contratos;
	}



	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	/*public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}*/

}
