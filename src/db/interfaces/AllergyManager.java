package db.interfaces;

import java.util.List;

import pojos.Allergy;

public interface AllergyManager {

	public void add(Allergy allergy);
	public List<Allergy> searchAllergyByName(String allergy);
	public Allergy searchAllergyById(int allergyId);
	public List<Allergy> showAllergies();
}
