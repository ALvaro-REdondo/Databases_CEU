package db.interfaces_JPA;

import java.sql.Date;
import java.util.List;

import pojos_JPA.ClinicalHistory_JPA;


public interface ClinicalHistoryJPAManager {

	public void connect();
	public void disconnect();
	public void createClinicalHistory(ClinicalHistory_JPA clinicalHistory);
	public ClinicalHistory_JPA getClinicalHistory(int id);
	public List<ClinicalHistory_JPA> getClinicalHistories(); 
	public void deleteClinicalHistory(ClinicalHistory_JPA clinicalHistory);
	public void updateClinicalHistory(ClinicalHistory_JPA clinicalHistory);
}
