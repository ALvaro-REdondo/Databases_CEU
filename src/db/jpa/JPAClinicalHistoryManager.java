package db.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.ClinicalHistoryJPAManager;
import pojos.Patient;
import pojos_JPA.Allergy_JPA;
import pojos_JPA.ClinicalHistory_JPA;
import pojos_JPA.MedicalPersonnel_JPA;

public class JPAClinicalHistoryManager implements ClinicalHistoryJPAManager {

	private EntityManager em;

	@Override
	public void connect() {
		// HAY QUE CAMBIAR LO DE COMPANY-PROVIDER
		// REALMENTE NO SE SI TENGO QUE HACER LO DE CONNECT Y DISCONNECT
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
	public void createClinicalHistory(ClinicalHistory_JPA clinicalHistory) {
		em.getTransaction().begin();
		em.persist(clinicalHistory);
		em.getTransaction().commit();
	}

	@Override
	public ClinicalHistory_JPA searchClinicalHistoryById(int id) {
		Query q = em.createNativeQuery("SELECT * FROM ClinicalHistory WHERE id = ?", ClinicalHistory_JPA.class);
		q.setParameter(1, id);
		ClinicalHistory_JPA clinicalHistory = (ClinicalHistory_JPA) q.getSingleResult();
		return clinicalHistory;
	}

	@Override
	public List<ClinicalHistory_JPA> getClinicalHistories() {
		Query q = em.createNativeQuery("SELECT * FROM ClinicalHistory", ClinicalHistory_JPA.class);
		List<ClinicalHistory_JPA> clinicalHistory = (List<ClinicalHistory_JPA>) q.getResultList();
		return clinicalHistory;		
	}
	
	@Override
	public void deleteClinicalHistory(ClinicalHistory_JPA clinicalHistory) {
		em.getTransaction().begin();
		em.remove(clinicalHistory);
		em.getTransaction().commit();
	}
	
	@Override
	public void updateClinicalHistory(ClinicalHistory_JPA clinicalHistory) {
		Query q = em.createNativeQuery("SELECT * FROM ClinicalHistory WHERE id = ?", ClinicalHistory_JPA.class);
		q.setParameter(1, clinicalHistory.getId());
		ClinicalHistory_JPA clinicalHistoryToUpdate = (ClinicalHistory_JPA) q.getSingleResult();
		em.getTransaction().begin();
		clinicalHistory.setDoe(clinicalHistory.getDoe());
		clinicalHistory.setDod(clinicalHistory.getDod());
		clinicalHistory.setBloodType(clinicalHistory.getBloodType());
		clinicalHistory.setExtraInfo(clinicalHistory.getExtraInfo());
		em.getTransaction().commit();
	}

	@Override
	public List<ClinicalHistory_JPA> SearchClinicalHistoryByName(String name) {
		Query q = em.createNativeQuery("SELECT * FROM ClinicalHistory WHERE name LIKE ?",ClinicalHistory_JPA.class);
		q.setParameter(1, "%" + name + "%");
		List<ClinicalHistory_JPA> clinicalHistories = (List<ClinicalHistory_JPA>) q.getResultList();
		return clinicalHistories;
	}

}
