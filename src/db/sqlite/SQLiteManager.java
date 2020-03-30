package db.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import db.interfaces.DBManager;

public class SQLiteManager implements DBManager {

	private Connection c;

	public SQLiteManager() {
		super();
	}
	@Override
	public void connect() {
		// Open database connection
		try {
			Class.forName("org.sqlite.JDBC");
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/Clinicaltrials.db");//asegurarse que esta bien puesto el nombre
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			//create Managers
			
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
		try {
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE Pathologies "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " duration  INTEGER	 NOT NULL, "
					   + " startDate DATE NOT NULL, "
					   + " endingDate DATE NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			String sql2 = "CREATE TABLE Clinical_histories "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " doe     DATE     NOT NULL, "
					   + " dod  DATE	 NOT NULL, "
					   + " bloodType STRING NOT NULL, "
					   + " extraInfo STRING NOT NULL)";
			stmt1.executeUpdate(sql2);
			stmt1.close();
			String sql3 = "CREATE TABLE Symptoms "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " manifestation     TEXT     NOT NULL)";
			stmt1.executeUpdate(sql3);
			stmt1.close();
			String sql4 = "CREATE TABLE Treatments "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " medication  TEXT	 NOT NULL, "
					   + " description TEXT NOT NULL ";
			stmt1.executeUpdate(sql4);
			stmt1.close();
			String sql5 = "CREATE TABLE Patients "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " allergy     TEXT     NOT NULL, "
					   + " degree  INTEGER	 NOT NULL ";
			stmt1.executeUpdate(sql5);
			stmt1.close();
			String sql6 = "CREATE TABLE Patients "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " gender  TEXT	 NOT NULL, "
					   + " state TEXT NOT NULL, "
					   + " dob INTEGER NOT NULL, "
					   + " pathology_id INTEGER REFERENCES Pathologies(id),"//son references
					   + " clinical_history_id INTEGER REFERENCES Clinical_histories(id))";
			stmt1.executeUpdate(sql6);
			stmt1.close();
			String sql7 = "CREATE TABLE Medical_personnel "//asegurarse que esta bien puesto
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " department  TEXT	 NOT NULL, "
					   + " position TEXT NOT NULL, "
					   + " pathology_id INTEGER REFERENCES Pathologies(id))";
			stmt1.executeUpdate(sql7);
			stmt1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
