package db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.interfaces_JPA.UserManager;
import pojos_users.Role;
import pojos_users.User;

public class JPAUserManager implements UserManager {
	
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
	public void createUser(User user) {
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

	}

	@Override
	public void createRole(Role role) {

		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();

	}

	@Override
	public Role getRole(int id) {

		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		Role role = (Role) q.getSingleResult();
		return role;
		
	}

	@Override
	public List<Role> getRoles() {
		
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
		
	}

	@Override
	public User checkPassword(String username, String password) {

		User user = null;
		//Create a message digest
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes()); //this tells the system to do the digest with the password.
			byte[] hash = md.digest();
			//Create query
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, hash);
			
			user = (User) q.getSingleResult();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
	}

}
