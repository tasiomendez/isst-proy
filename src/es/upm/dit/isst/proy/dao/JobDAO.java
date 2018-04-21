package es.upm.dit.isst.proy.dao;

import es.upm.dit.isst.proy.dao.model.Job;

public interface JobDAO {
	public void createJob(Job job);
	public Job readJob(int id);
	public void updateJob(Job job);
	public void deleteJob(Job job);
}
