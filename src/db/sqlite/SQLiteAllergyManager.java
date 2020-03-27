package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.AllergyManager;
import pojos.Allergy;
import pojos.ClinicalHistory;

public class SQLiteAllergyManager implements AllergyManager {

	private Connection c;

	public SQLiteAllergyManager(Connection c) {
		this.c = c;
	}

	@Override
	public void add(Allergy allergy) {
		try {
			String sql = "INSERT INTO allergy (allergy, degree) VALUES (?, ?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, allergy.getAllergy());
			prep.setInt(2, allergy.getDegree());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Allergy> searchAllergyById(Integer id) {
		List<Allergy> allergyList = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergy WHERE allergy LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + id + "%");
			// HAY QUE HACER EL SEARCHALLERGY(???)********************************************************************************************************************
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int allergyId = rs.getInt("id");
				String name = rs.getString("allergy");
				int degree = rs.getInt("degree");
				Allergy newAllergy = new Allergy(allergyId, name, degree);
				allergyList.add(newAllergy);
				
			}
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return allergyList;
	}

	@Override
	public List<Allergy> searchAllergyByAllergy(String allergy) {
		List<Allergy> allergyList = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM allergy WHERE allergy LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + allergy + "%");
			// HAY QUE HACER EL SEARCHALLERGY(???)********************************************************************************************************************
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String allergyName = rs.getString("allergy");
				int degree = rs.getInt("degree");
				Allergy newAllergy = new Allergy(id, allergyName, degree);
				allergyList.add(newAllergy);
				
			}
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return allergyList;
	}

}
