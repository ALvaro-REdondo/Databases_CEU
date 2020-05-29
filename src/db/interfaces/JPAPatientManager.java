package db.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Patient;

public class JPAPatientManager implements PatientJPAManager {
	
	private EntityManager em;
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("patient").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public void disconnect() {
		em.close();

	}

	@Override
	public void createPatient(Patient patient) {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}

	@Override
	public Patient getPatient(int id) {
		Query q=em.createNativeQuery("SELECT * FROM patient WHERE id = ?",Patient.class);
		q.setParameter(1,id);
		Patient patient = (Patient)q.getSingleResult();
		return patient;
		
	}

	@Override
	public List<Patient> getPatient() {
		Query q = em.createNativeQuery("SELECT * FROM patient", Patient.class);
		List<Patient> patients = (List<Patient>)q.getResultList();
		return patients;
	}

	@Override
	public void delete(Patient patient) {
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
	}

	


}
