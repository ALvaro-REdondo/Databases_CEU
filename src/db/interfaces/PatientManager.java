package db.interfaces;


import pojos.Patient;
import java.util.List;

public interface PatientManager {
	
		public void add(Patient patient);
		public void update(Patient patient);
		public void delete(Patient patient);
		public List<Patient> searchPatientByName(String name);
		public Patient searchPatientById(int patientId);
		public List<Patient> showPatients ();

}
