package db.interfaces;

import java.util.List;

import pojos.Allergy;

public interface AllergyJPAManager {
	
	public void connect();
	public void disconnect();
	public void createAllergy(Allergy allergy);
	public Allergy getAllergy(int id);
	public List<Allergy> getAllergies();
}
