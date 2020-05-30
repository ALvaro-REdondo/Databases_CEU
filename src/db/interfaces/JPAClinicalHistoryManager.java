package db.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import pojos.JPA.Allergy_JPA;
import pojos.JPA.ClinicalHistory_JPA;

public class JPAClinicalHistoryManager implements ClinicalHistoryJPAManager {

	private EntityManager em;

	@Override
	public void connect() {
		// HAY QUE CAMBIAR LO DE COMPANY-PROVIDER
		// REALMENTE NO SE SI TENGO QUE HACER LO DE CONNECT Y DISCONNECT
		em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
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
	public ClinicalHistory_JPA getClinicalHistory(int id) {
		Query q = em.createNativeQuery("SELECT * FROM ClinicalHistory WHERE id = ?", ClinicalHistory_JPA.class);
		q.setParameter(1, id);
		ClinicalHistory_JPA clinicalHistory = (ClinicalHistory_JPA) q.getSingleResult();
		return clinicalHistory;
	}

	@Override
	public List<ClinicalHistory_JPA> getClinicalHistories() {
		Query q = em.createNativeQuery("SELECT * FROM Clinical History", ClinicalHistory_JPA.class);
		List<ClinicalHistory_JPA> clinicalHistory = (List<ClinicalHistory_JPA>) q.getResultList();
		return clinicalHistory;		
	}

}
