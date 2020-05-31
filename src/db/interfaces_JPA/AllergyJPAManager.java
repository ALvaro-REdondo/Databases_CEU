package db.interfaces_JPA;

import java.util.List;

import pojos_JPA.Allergy_JPA;

public interface AllergyJPAManager {
	
	public void connect();
	public void disconnect();
	public void createAllergy(Allergy_JPA allergy);
	public Allergy_JPA searchAllergyById(int id);
	public List<Allergy_JPA> getAllergies();
	public List<Allergy_JPA> searchAllergiesByName(String name);
}
