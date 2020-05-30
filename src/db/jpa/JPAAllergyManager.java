package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.AllergyJPAManager;
import pojos.Allergy;
import pojos.JPA.Allergy_JPA;

public class JPAAllergyManager implements AllergyJPAManager {

	private EntityManager em;

	@Override
	public void connect() {
		// HAY QUE CAMBIAR LO DE COMPANY-PROVIDER
		// REALMENTE NO SE SI TENGO QUE HACER LO DE CONNECT Y DISCONNECT
		em = Persistence.createEntityManagerFactory("Clinical_trials_provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void createAllergy(Allergy_JPA allergy) {
		em.getTransaction().begin();
		em.persist(allergy);
		em.getTransaction().commit();
	}

	@Override
	public Allergy_JPA getAllergy(int id) {
		Query q = em.createNativeQuery("SELECT * FROM Allergy WHERE id = ?", Allergy_JPA.class);
		q.setParameter(1, id);
		Allergy_JPA allergy = (Allergy_JPA) q.getSingleResult();
		return allergy;
	}

	@Override
	public List<Allergy_JPA> getAllergies() {
		Query q = em.createNativeQuery("SELECT * FROM Allergy", Allergy_JPA.class);
		List<Allergy_JPA> allergies = (List<Allergy_JPA>) q.getResultList();
		return allergies;
	}	

}
