package db.interfaces;

import java.util.List;
import javax.persistence.*;
import pojos.JPA.Treatment_JPA;


public class JPATreatmentManager implements TreatmentManagerJPA{
	private EntityManager em;
	
	@Override
	public void add(Treatment_JPA treatment) {
	em.getTransaction().begin();
	em.persist(treatment);
	em.getTransaction().commit();	
	}

	@Override
	public void update(Treatment_JPA treatment) {
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void delete(Treatment_JPA treatment) {
		em.getTransaction().begin();
		em.remove(treatment);
		em.getTransaction().commit();
		em.close();
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
		Query q = em.createNativeQuery("SELECT * FROM Treatment", Treatment_JPA.class);
		List<Treatment_JPA> treatments = (List<Treatment_JPA>) q.getResultList();
		return treatments;
	}

}