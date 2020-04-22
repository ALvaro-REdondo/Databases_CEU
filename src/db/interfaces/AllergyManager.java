package db.interfaces;

import java.util.List;

import pojos.Allergy;

public interface AllergyManager {

	public void add(Allergy allergy);
	public List<Allergy> searchAllergyById(Integer id);
	public List<Allergy> searchAllergyByName(String allergy);
	public Allergy getAllergy(int allergyId);
	public List<Allergy> showAllergies();
}
