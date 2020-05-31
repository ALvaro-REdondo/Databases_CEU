package db.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import db.interfaces.AllergyManager;
import db.interfaces.ClinicalHistoryManager;
import db.interfaces.DBManager;
import db.interfaces.MedicalPersonnelManager;
import db.interfaces.PathologyManager;
import db.interfaces.PatientManager;
import db.interfaces.SymptomManager;
import db.interfaces.TreatmentManager;

public class SQLiteManager implements DBManager {

	private Connection c;
	private PatientManager patient;
	private TreatmentManager treatment;
	private MedicalPersonnelManager medicalPersonnel;
	private SymptomManager symptom;
	private PathologyManager pathology;
	private ClinicalHistoryManager clinicalHistory;
	private AllergyManager allergy;

	public SQLiteManager() {
		super();
	}
	@Override
	public void connect() {
		// Open database connection
		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/Clinicaltrials.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			//create Managers
			patient = new SQLitePatientManager(c);
			treatment = new SQLiteTreatmentManager(c);
			medicalPersonnel = new SQLiteMedicalPersonnelManager(c);
			symptom = new SQLiteSymptomManager(c);
			pathology = new SQLitePathologyManager(c);
			clinicalHistory = new SQLiteClinicalHistoryManager(c);
			allergy = new SQLiteAllergyManager(c);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		return c;
	}

	@Override
	public void disconnect() {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createTables() {
		Statement stmt1;
		try {
			stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE Symptom "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " manifestation     TEXT     NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1 = c.createStatement();
			String sql2 = "CREATE TABLE Treatment "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " medication  TEXT	 NOT NULL, "
					   + " description TEXT )";
			stmt1.executeUpdate(sql2);
			stmt1 = c.createStatement();
			String sql3 = "CREATE TABLE Pathology "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " startDate DATE NOT NULL, "
					   + " endingDate DATE, "
					   + " treatmentId INTEGER REFERENCES Treatment(id))";
			stmt1.executeUpdate(sql3);
			stmt1 = c.createStatement();
			String sql4 = "CREATE TABLE Allergy "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " allergy     TEXT     NOT NULL, "
					   + " degree  INTEGER	 NOT NULL )";
			stmt1.executeUpdate(sql4);
			stmt1 = c.createStatement();
			String sql5 = "CREATE TABLE ClinicalHistory "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " doe     DATE     NOT NULL, "
					   + " dod  DATE, "
					   + " bloodType TEXT NOT NULL, "
					   + " extraInfo TEXT, " 
					   + " allergyId INTEGER REFERENCES Allergy(id))";
			stmt1.executeUpdate(sql5);
			stmt1 = c.createStatement();
			String sql6 = "CREATE TABLE Patient "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " gender  TEXT	 NOT NULL, "
					   + " state TEXT NOT NULL, "
					   + " dob INTEGER NOT NULL, "
					   + " pathology_id INTEGER REFERENCES Pathology(id),"
					   + " clinical_history_id INTEGER REFERENCES ClinicalHistory(id))";
			stmt1.executeUpdate(sql6);
			stmt1 = c.createStatement();
			String sql7 = "CREATE TABLE MedicalPersonnel "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " department  TEXT	 NOT NULL, "
					   + " position TEXT NOT NULL, "
					   + " pathology_id INTEGER REFERENCES Pathology(id))";
			stmt1.executeUpdate(sql7);
			stmt1 = c.createStatement();
			String sql8 = "CREATE TABLE Pathology-Symptom "
					   + "(  pathology_id INTEGER REFERENCES Pathology(id) ,"
					   + "  symptom_id INTEGER  REFERENCES Symptom(id))";
			stmt1.executeUpdate(sql8);
			stmt1.close();
		} catch (SQLException e) {
			if (e.getMessage().contains("already exists")) {
			} else {
				e.printStackTrace();
			}
		}
	}
	@Override
	public PathologyManager getPathologyManager() {
		// TODO Auto-generated method stub
		return pathology;
	}
	@Override
	public ClinicalHistoryManager getClinicalHistoryManager() {
		// TODO Auto-generated method stub
		return clinicalHistory;
	}
	@Override
	public SymptomManager getSymptomManager() {
		// TODO Auto-generated method stub
		return symptom;
	}
	@Override
	public TreatmentManager getTreatmentManager() {
		// TODO Auto-generated method stub
		return treatment;
	}
	@Override
	public AllergyManager getAllergyManager() {
		// TODO Auto-generated method stub
		return allergy;
	}
	@Override
	public PatientManager getPatientManager() {
		// TODO Auto-generated method stub
		return patient;
	}
	@Override
	public MedicalPersonnelManager getMedicalPersonnelManager() {
		// TODO Auto-generated method stub
		return medicalPersonnel;
	}

}
