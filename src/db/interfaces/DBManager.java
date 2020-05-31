package db.interfaces;

public interface DBManager {

	public void connect();
	public void disconnect();
	public void createTables();
	
	public PathologyManager getPathologyManager();
	public ClinicalHistoryManager getClinicalHistoryManager();
	public SymptomManager getSymptomManager();
	public TreatmentManager getTreatmentManager();
	public AllergyManager getAllergyManager();
	public PatientManager getPatientManager();
	public MedicalPersonnelManager getMedicalPersonnelManager();
	public int getLastId();
}
