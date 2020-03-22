package db.interfaces;

import pojos.*;
import java.util.List;

public interface MedicalPersonnelManager {

	public void add(MedicalPersonnel medicalPersonnel);
	public void update(MedicalPersonnel medicalPersonnel);
	public void delete(MedicalPersonnel medicalPersonnel);
	public List<MedicalPersonnel> searchMedicalPersonnelById(Integer id);
	public List<MedicalPersonnel> searchMedicalPersonnelByPathologyId (Integer pathologyId);
	
}
