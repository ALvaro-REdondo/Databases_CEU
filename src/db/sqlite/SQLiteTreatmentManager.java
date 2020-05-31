package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// insert the provided treatment
		try {
			String sql = "INSERT INTO Treatment (name, medication, description) " + "VALUES (?,?,?);";
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
		try {
			String sql = " UPDATE Treatment  SET name=?, medication=? , description=? WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setString(1, treatment.getName());
			s.setString(2, treatment.getMedication());
			s.setString(3, treatment.getDescription());
			s.executeUpdate();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Treatment treatment) {
		try {
			String sql = " DELETE FROM Treatment WHERE id =?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, treatment.getId());
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Treatment> searchTreatmentByName(String name) {
		// create an empty list of treatments
		List<Treatment> treatmentsList = new ArrayList<Treatment>();
		// search for all treatments that fit the name
		try {
			String sql = "SELECT * FROM Treatment WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			// for each result...
			while (rs.next()) {
				int id = rs.getInt("id");
				String TreatmentName = rs.getString("name");
				String TreatmentMedication = rs.getString("medication");
				String TreatmentDescription = rs.getString("description");
				// create a new treatment and..
				Treatment newTreatment = new Treatment(id, TreatmentName, TreatmentMedication, TreatmentDescription);
				// add it to the list
				treatmentsList.add(newTreatment);
			}
		} catch (SQLException e) {
			return null;
		}
		// return the list
		return treatmentsList;
	}

	public List<Treatment> showTreatments() {

		List<Treatment> treatmentsList = new ArrayList<Treatment>();

		try {
			String sql = "SELECT * FROM treatment";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String treatmentName = rs.getString("name");
				String treatmentMedication = rs.getString("medication");
				String treatmentDescription = rs.getString("description");
				Treatment newTreatment = new Treatment(id, treatmentName, treatmentMedication, treatmentDescription);
				treatmentsList.add(newTreatment);

			}
		} catch (SQLException e) {
			return null;
		}
		return treatmentsList;
	}

	@Override
	public Treatment searchTreatmentById(Integer id) {
		Treatment newTreatment = null;

		try {
			String sql = "SELECT * FROM treatment WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1, id);
			ResultSet rs = g.executeQuery();
			rs.next();

			int Id = rs.getInt("id");
			String TreatmentName = rs.getString("name");
			String TreatmentMedication = rs.getString("medication");
			String TreatmentDescription = rs.getString("description");
			newTreatment = new Treatment(Id, TreatmentName, TreatmentMedication, TreatmentDescription);
		} catch (SQLException e) {
			return null;
		}
		return newTreatment;
	}
}