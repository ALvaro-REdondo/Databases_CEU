package db.interfaces;

import java.util.List;

import pojos.Patient;

public interface PatientJPAManager {
	public void connect();
	public void disconnect();
	public void createPatient(Patient patient);
	public Patient getPatient(int id);
	public List<Patient> getPatient();
	public void delete (Patient patient);
}
