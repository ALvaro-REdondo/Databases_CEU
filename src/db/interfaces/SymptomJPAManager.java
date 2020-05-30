package db.interfaces;

import java.util.List;

import pojos.Symptom;

public interface SymptomJPAManager {
	public void connect();
	public void disconnect();
	public void createSymptom(Symptom symptom);
	public Symptom getSymptom(int id);
	public List<Symptom> getSymptoms();
	

}
