package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.TreatmentManager;
import pojos.Treatment;

public class SQLiteTreatmentManager implements TreatmentManager {

	private Connection c;

	public SQLiteTreatmentManager(Connection c) {
		this.c = c;
	}

	@Override
	public void add(Treatment treatment) {
		//insert the provided treatment
		try {
			String sql = "INSERT INTO treatments (name, medication, description) " + "VALUES (?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, treatment.getName());
			prep.setString(2, treatment.getMedication());
			prep.setString(3, treatment.getDescription());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Treatment treatment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Treatment treatment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Treatment> searchTreatmentmById(Integer id) {
		List<Treatment> treatmentsList = new ArrayList<Treatment>();
		return null;
	}

	@Override
	public List<Treatment> searchTreatmentByName(String name) {
		//create an empty list of treatments
		List<Treatment> treatmentsList = new ArrayList<Treatment>();
		//search for all treatments that fit the name
		try {
			String sql = "SELECT * FROM treatments WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			//for each result...
			while (rs.next()) {
				int id = rs.getInt("id");
				String TreatmentName = rs.getString("name");
				String TreatmentMedication = rs.getString("medication");
				String TreatmentDescription = rs.getString("description");
				//create a new treatment and..
				Treatment newTreatment = new Treatment(id, TreatmentName, TreatmentMedication, TreatmentDescription);
				//add it to the list
				treatmentsList.add(newTreatment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return the list
		return treatmentsList;
	}
}