package db.interfaces_JPA;

import java.util.List;

import pojos_JPA.Patient_JPA;

public interface PatientJPAManager {
	public void connect();
	public void disconnect();
	public void createPatient(Patient_JPA patient);
	public Patient_JPA getPatient(int id);
	public List<Patient_JPA> getPatient();
	public void delete (Patient_JPA patient);
	public void update (Patient_JPA patient);
}
