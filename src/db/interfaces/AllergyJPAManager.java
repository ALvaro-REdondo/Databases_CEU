package db.interfaces;

import java.util.List;

import pojos.JPA.Allergy_JPA;

public interface AllergyJPAManager {
	
	public void connect();
	public void disconnect();
	public void createAllergy(Allergy_JPA allergy);
	public Allergy_JPA getAllergy(int id);
	public List<Allergy_JPA> getAllergies();
	
	//ESTO YA ME LO ESTOY INVENTANDO
}
