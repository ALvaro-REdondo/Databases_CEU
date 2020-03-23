package db.interfaces;

import pojos.Patient;
import java.util.List;

public interface PatientManager {
	
		public void add(Patient patient);
		public void update(Patient patient);
		public void delete(Patient patient);
		public List<Patient> searchPatientById(Integer id);
		public List<Patient> search�tientByName(String name);

}
