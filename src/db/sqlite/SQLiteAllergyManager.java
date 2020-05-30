package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.AllergyManager;
import pojos.Allergy;
import pojos.ClinicalHistory;
import pojos.MedicalPersonnel;

public class SQLiteAllergyManager implements AllergyManager {

	private Connection c;

	public SQLiteAllergyManager(Connection c) {
		this.c = c;
	}

	@Override
	public void add(Allergy allergy) {
		try {
			String sql = "INSERT INTO Allergy (allergy, degree) VALUES (?, ?);";
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
	public List<Allergy> searchAllergyByName(String allergy) {
		List<Allergy> allergyList = new ArrayList<Allergy>();
		try {
			String sql = "SELECT * FROM Allergy WHERE allergy LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + allergy + "%");
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

	public Allergy searchAllergyById(int allergyId) {
		
		Allergy allergy = null;
		
		try {
			
			String sql = "SELECT * FROM Allergy WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  allergyId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String allergyName = rs.getString("allergy");
			int degree = rs.getInt("degree");
			
			allergy = new Allergy(id, allergyName, degree);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return allergy;
	}
	
	public List<Allergy> showAllergies() {
		//create empty list of allergies
		List<Allergy> allergiesList = new ArrayList<Allergy>();
		//get all allergies
		try {
			String sql = "SELECT * FROM Allergy";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			//for each result...
			while (rs.next()) {
				int id = rs.getInt("id");
				String allergyAllergy = rs.getString("allergy");
				int allergyDegree = rs.getInt("degree"); 
				//create a new allergy and...
				Allergy newAllergy = new Allergy(id, allergyAllergy, allergyDegree);
				//add it to the list
				allergiesList.add(newAllergy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return the list
		return allergiesList;
	}
}
