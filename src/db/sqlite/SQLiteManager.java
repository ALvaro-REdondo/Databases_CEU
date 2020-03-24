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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
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
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE ClinicalTrials "//asegurarse que esta bien puesto
				   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
				   + " name     TEXT     NOT NULL, "
				   + " address  TEXT	 NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

}
