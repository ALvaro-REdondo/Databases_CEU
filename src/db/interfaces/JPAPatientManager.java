package db.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Patient;
import pojos.JPA.Patient_JPA;

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
	public void createPatient(Patient_JPA patient) {
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}

	@Override
	public Patient_JPA getPatient(int id) {
		Query q=em.createNativeQuery("SELECT * FROM patient WHERE id = ?",Patient.class);
		q.setParameter(1,id);
		Patient_JPA patient = (Patient_JPA)q.getSingleResult();
		return patient;
		
	}

	@Override
	public List<Patient_JPA> getPatient() {
		Query q = em.createNativeQuery("SELECT * FROM patient", Patient.class);
		List<Patient_JPA> patients = (List<Patient_JPA>)q.getResultList();
		return patients;
	}

	@Override
	public void delete(Patient_JPA patient) {
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
	}

	


}
