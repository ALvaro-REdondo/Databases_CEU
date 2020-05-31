package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import db.interfaces_JPA.*;
import pojos_JPA.Pathology_JPA;
import pojos_JPA.Treatment_JPA;


public class JPAPathologyManager implements PathologyJPAManager {
	
	private EntityManager em;

	@Override
	public void connect() {
		
		// Get the entity manager
		em = Persistence.createEntityManagerFactory("provider-Clinicaltrials").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public void disconnect() {

		em.close();

	}

	@Override
	public void createPathology(Pathology_JPA pathology) {
		
		em.getTransaction().begin();
		em.persist(pathology);
		em.getTransaction().commit();

	}

	@Override
	public Pathology_JPA searchPathologyById(int id) {
		Query q = em.createNativeQuery("SELECT * FROM Pathology WHERE id = ?", Pathology_JPA.class);
		q.setParameter(1, id);
		Pathology_JPA pathology = (Pathology_JPA) q.getSingleResult();
		return pathology;
	}

	@Override
	public List<Pathology_JPA> getPathologies() {
		Query q = em.createNativeQuery("SELECT * FROM Pathology", Pathology_JPA.class);
		List<Pathology_JPA> pathologies = (List<Pathology_JPA>) q.getResultList();
		return pathologies;
	}

	@Override
	public void deletePathology(Pathology_JPA pathology) {
		
		em.getTransaction().begin();
		em.remove(pathology);
		em.getTransaction().commit();


	}

	@Override
	public void updatePathology(Pathology_JPA pathology) {
		Query q = em.createNativeQuery("SELECT * FROM Pathology WHERE id = ?", Pathology_JPA.class);
		q.setParameter(1, pathology.getId());
		Pathology_JPA toUpdate = (Pathology_JPA) q.getSingleResult();
		em.getTransaction().begin();
		toUpdate.setName(pathology.getName());
		toUpdate.setStartDate(pathology.getStartDate());
		toUpdate.setEndingDate(pathology.getEndingDate());
		toUpdate.setTreatment(pathology.getTreatment());
		toUpdate.setPatients(pathology.getPatients());
		toUpdate.setMedicalPersonnels(pathology.getMedicalPersonnels());
		em.getTransaction().commit();
	}

	@Override
	public List<Pathology_JPA> SearchPathologyByName(String name) {
		Query q = em.createNativeQuery("SELECT * FROM Pathology WHERE name LIKE ?", Pathology_JPA.class);
		q.setParameter(1, "%" + name + "%");
		List<Pathology_JPA> pathologies = (List<Pathology_JPA>) q.getResultList();
		return pathologies;
	}

}
