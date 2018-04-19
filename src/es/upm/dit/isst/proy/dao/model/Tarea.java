package es.upm.dit.isst.proy.dao.model;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Entity
public class Tarea {

	@Id
	private int id=0;
	private int planned_hours;
	
	@OneToMany(mappedBy="tarea",fetch=FetchType.EAGER)
	private List<Job> jobs;
	
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

	public Tarea() {id+=1;}
	
}
