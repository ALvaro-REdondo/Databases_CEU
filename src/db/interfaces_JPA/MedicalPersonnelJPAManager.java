package db.interfaces_JPA;

import java.util.List;

import pojos_JPA.MedicalPersonnel_JPA;

public interface MedicalPersonnelJPAManager {
	
	public void connect();
	public void disconnect();
	public void createMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel);
	public MedicalPersonnel_JPA searchMedicalPersonnelById(int id);
	public List<MedicalPersonnel_JPA> getMedicalPersonnels();
	public List<MedicalPersonnel_JPA> searchMedicalPersonnelByName(String name);
	public void deleteMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel);
	public void updateMedicalPersonnel(MedicalPersonnel_JPA medicalPersonnel);	

}
