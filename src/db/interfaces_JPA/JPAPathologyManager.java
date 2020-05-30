package db.interfaces_JPA;

import java.util.List;

import pojos.JPA.Pathology_JPA;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JPAPathologyManager implements PathologyJPAManager {
	
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
	public void createPathology(Pathology_JPA pathology) {
		
		em.getTransaction().begin();
		em.persist(pathology);
		em.getTransaction().commit();

	}

	@Override
	public Pathology_JPA getPathology(int id) {

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
		// TODO Auto-generated method stub

	}

}
