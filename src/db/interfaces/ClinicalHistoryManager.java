package db.interfaces;

import java.util.List;

import pojos.ClinicalHistory;

public interface ClinicalHistoryManager {
	public void add(ClinicalHistory clinicalHistory);
	public void update(ClinicalHistory clinicalHistory);
	public void delete(ClinicalHistory clinicalHistory);
	public List<ClinicalHistory> searchClinicalHistoryById(Integer id);

}
