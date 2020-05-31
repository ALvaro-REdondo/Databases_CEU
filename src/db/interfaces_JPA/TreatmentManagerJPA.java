package db.interfaces_JPA;

import java.util.List;

import pojos_JPA.Treatment_JPA;


public interface TreatmentManagerJPA {
	public void add(Treatment_JPA treatment);
	public void update(Treatment_JPA treatment);
	public void delete(Treatment_JPA treatment);
	public Treatment_JPA searchTreatmentById(Integer id);
	public List<Treatment_JPA> getTreatments();
	public List<Treatment_JPA> searchTreatmentByName(String name);
}
