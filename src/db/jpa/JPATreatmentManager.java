package db.jpa;

import java.util.List;
import javax.persistence.*;

import db.interfaces_JPA.TreatmentManagerJPA;
import pojos_JPA.Treatment_JPA;


public class JPATreatmentManager implements TreatmentManagerJPA{
	private EntityManager em;
	

	public void connect() {
		// Get the entity manager
		em = Persistence.createEntityManagerFactory("Clinicaltrials-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();
	}

	public void disconnect() {
		em.close();
	}

	
	@Override
	public void add(Treatment_JPA treatment) {
	em.getTransaction().begin();
	em.persist(treatment);
	em.getTransaction().commit();	
	}

	@Override
	public void update(Treatment_JPA treatment) {
		Query q = em.createNativeQuery("SELECT * FROM Treatment WHERE id = ?", Treatment_JPA.class);
		q.setParameter(1, treatment.getId());
		Treatment_JPA toUpdate = (Treatment_JPA) q.getSingleResult();
		em.getTransaction().begin();
		toUpdate.setName(treatment.getName());
		toUpdate.setMedication(treatment.getMedication());
		toUpdate.setDescription(treatment.getDescription());
		em.getTransaction().commit();
	}

	@Override
	public void delete(Treatment_JPA treatment) {
		em.getTransaction().begin();
		em.remove(treatment);
		em.getTransaction().commit();
	}

	@Override
	public Treatment_JPA searchTreatmentById(Integer id) {
		Query q=em.createNativeQuery("SELECT * FROM Treatment WHERE id = ?",Treatment_JPA.class);
		q.setParameter(1,id);
		Treatment_JPA treatment = (Treatment_JPA)q.getSingleResult();
		return treatment;
	}

	@Override
	public List<Treatment_JPA> searchTreatmentByName(String name) {
		Query q = em.createNativeQuery("SELECT * FROM Treatment WHERE name LIKE ?", Treatment_JPA.class);
		q.setParameter(1, "%" + name + "%");
		List<Treatment_JPA> treatments = (List<Treatment_JPA>) q.getResultList();
		return treatments;
	}

}
