package db.interfaces;

import java.util.List;

import pojos.*;

public interface TreatmentManager {

	public void add(Treatment treatment);
	public void update(Treatment treatment);
	public void delete(Treatment treatment);
	public Treatment searchTreatmentById(Integer id);
	public List<Treatment> showTreatments();
	public List<Treatment> searchTreatmentByName(String name);
}
