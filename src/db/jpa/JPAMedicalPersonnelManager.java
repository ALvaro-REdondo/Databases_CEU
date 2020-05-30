package db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.MedicalPersonnelJPAManager;
import pojos_JPA.*;

public class JPAMedicalPersonnelManager implements MedicalPersonnelJPAManager {

private EntityManager em;	
	
	@Override
	public void connect() {
		// Get the entity manager
		em = Persistence.createEntityManagerFactory("Clinical_trials_provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
		em.getTransaction().commit();

	}

	@Override
	public void disconnect() {

		em.close();

	}

	@Override
	public void createMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel) {

		em.getTransaction().begin();
		em.persist(medicalPersonnel);
		em.getTransaction().commit();

	}

	@Override
	public MedicalPersonnel_JPA getMedicalPersonnel(int id) {

		Query q = em.createNativeQuery("SELECT * FROM MedicalPersonnel WHERE id = ?", MedicalPersonnel_JPA.class);
		q.setParameter(1, id);
		MedicalPersonnel_JPA medicalPersonnel = (MedicalPersonnel_JPA) q.getSingleResult();
		return medicalPersonnel;
		
	}

	@Override
	public List<MedicalPersonnel_JPA> getMedicalPersonnels() {

		Query q = em.createNativeQuery("SELECT * FROM MedicalPersonnel", MedicalPersonnel_JPA.class);
		List<MedicalPersonnel_JPA> medicalPersonnels = (List<MedicalPersonnel_JPA>) q.getResultList();
		return medicalPersonnels;
				
	}

	@Override
	public void deleteMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel) {

		em.getTransaction().begin();
		em.remove(medicalPersonnel);
		em.getTransaction().commit();

	}

	@Override
	public void updateMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel) {
		
		Query q = em.createNativeQuery("SELECT * FROM MedicalPersonnel", MedicalPersonnel_JPA.class);
		q.setParameter(1, medicalPersonnel.getId());
		MedicalPersonnel_JPA medicalPersonnelToUpdate = (MedicalPersonnel_JPA)q.getSingleResult();
		medicalPersonnelToUpdate.setName(medicalPersonnel.getName());
		medicalPersonnelToUpdate.setDepartment(medicalPersonnel.getDepartment());
		medicalPersonnelToUpdate.setPosition(medicalPersonnel.getPosition());
		//medicalPersonnelToUpdate.setPathology(medicalPersonnel.getPathology());

	}

}
