package db.interfaces;

import java.util.List;

import pojos.MedicalPersonnel;
import pojos.users.*;
import pojos.JPA.*;

public interface MedicalPersonnelJPAManager {
	
	public void connect();
	public void disconnect();
	public void createMedicalPersonnel(MedicalPersonnel medicalPersonnel);
	public MedicalPersonnel_JPA getMedicalPersonnel(int id);
	public List<MedicalPersonnel_JPA> getMedicalPersonnels();
	public void deleteMedicalPersonnel();
	public void updateMedicalPersonnel();	
	
}
