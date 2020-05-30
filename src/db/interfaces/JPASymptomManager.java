package db.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.Patient;
import pojos.Symptom;

public class JPASymptomManager implements SymptomJPAManager {
	
	private EntityManager em;

	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("symptom").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void createSymptom(Symptom symptom) {
		em.getTransaction().begin();
		em.persist(symptom);
		em.getTransaction().commit();

	}

	@Override
	public Symptom getSymptom(int id) {
		Query q=em.createNativeQuery("SELECT * FROM symptom WHERE id = ?",Patient.class);
		q.setParameter(1,id);
		Symptom symptom = (Symptom)q.getSingleResult();
		return symptom;
	}

	@Override
	public List<Symptom> getSymptoms() {
		Query q = em.createNativeQuery("SELECT * FROM symptom", Symptom.class);
		List<Symptom> symptoms = (List<Symptom>)q.getResultList();
		return symptoms;
	}

}
