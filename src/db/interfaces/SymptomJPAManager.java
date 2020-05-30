package db.interfaces;

import java.util.List;

import pojos.Symptom;
import pojos.JPA.Symptom_JPA;

public interface SymptomJPAManager {
	public void connect();
	public void disconnect();
	public void createSymptom(Symptom_JPA symptom);
	public Symptom_JPA getSymptom(int id);
	public List<Symptom_JPA> getSymptoms();
	

}
