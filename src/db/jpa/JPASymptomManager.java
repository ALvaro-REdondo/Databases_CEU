package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.SymptomJPAManager;
import pojos.Patient;
import pojos.Symptom;
import pojos_JPA.Symptom_JPA;

public class JPASymptomManager implements SymptomJPAManager {
	
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
	public void createSymptom(Symptom_JPA symptom) {
		em.getTransaction().begin();
		em.persist(symptom);
		em.getTransaction().commit();

	}

	@Override
	public Symptom_JPA getSymptom(int id) {
		Query q=em.createNativeQuery("SELECT * FROM symptom WHERE id = ?",Symptom_JPA.class);
		q.setParameter(1,id);
		Symptom_JPA symptom = (Symptom_JPA)q.getSingleResult();
		return symptom;
	}

	@Override
	public List<Symptom_JPA> getSymptoms() {
		Query q = em.createNativeQuery("SELECT * FROM symptom", Symptom.class);
		List<Symptom_JPA> symptoms = (List<Symptom_JPA>)q.getResultList();
		return symptoms;
	}

}
