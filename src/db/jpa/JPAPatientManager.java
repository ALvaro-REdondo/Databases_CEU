package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.PatientJPAManager;
import pojos.Patient;
import pojos_JPA.Patient_JPA;
import pojos_JPA.Treatment_JPA;

public class JPAPatientManager implements PatientJPAManager {
	
	private EntityManager em;
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("provider-Clinicaltrials").createEntityManager();
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
	public Patient_JPA searchPatientById(int id) {
		Query q=em.createNativeQuery("SELECT * FROM Patient WHERE id = ?",Patient_JPA.class);
		q.setParameter(1,id);
		Patient_JPA patient = (Patient_JPA)q.getSingleResult();
		return patient;
		
	}

	@Override
	public List<Patient_JPA> getPatients() {
		Query q = em.createNativeQuery("SELECT * FROM Patient", Patient_JPA.class);
		List<Patient_JPA> patients = (List<Patient_JPA>)q.getResultList();
		return patients;
	}

	@Override
	public void delete(Patient_JPA patient) {
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
	}

	public void update (Patient_JPA patient){
		
		Query q = em.createNativeQuery("SELECT * FROM Patient", Patient_JPA.class);
		q.setParameter(1,patient.getId());
		Patient_JPA patientToUpdate = (Patient_JPA)q.getSingleResult();
		em.getTransaction().begin();
		patientToUpdate.setName(patient.getName());
		patientToUpdate.setGender(patient.getGender());
		patientToUpdate.setState(patient.getState());
		patientToUpdate.setDob(patient.getDob());
		em.getTransaction().commit();
		
		
	}

	@Override
	public List<Patient_JPA> searchPatientByName(String name) {
		Query q = em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient_JPA.class);
		q.setParameter(1, "%" + name + "%");
		List<Patient_JPA> patients = (List<Patient_JPA>) q.getResultList();
		return patients;
	}
	


}
