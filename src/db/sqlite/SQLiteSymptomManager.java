package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.interfaces.SymptomManager;
import pojos.Patient;
import pojos.Symptom;

public class SQLiteSymptomManager implements SymptomManager {
	
	private Connection c;
	public SQLiteSymptomManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void add(Symptom symptom) {
		try {
			String sql = "INSERT INTO symptom ( manifestation)"+" VALUES (?)"; 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,symptom.getManifestation());
			prep.executeUpdate();
			prep.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
	}
		
	

	@Override
	public List<Symptom> searchSymptomByManifestation(String manifestation) {
		List<Symptom> symptomsList= new ArrayList<Symptom>();
		try {
			String sql ="SELECT * FROM symptom WHERE manifestation LIKE ?";
			PreparedStatement prep =  c.prepareStatement(sql);
			prep.setString(1,"%" + manifestation + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String SymptomManifestation = rs.getString("manifestation");
				Symptom newSymptom = new Symptom(id, SymptomManifestation);
				symptomsList.add(newSymptom);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return symptomsList;
		
	}

	@Override
	public Symptom searchSymptomById(int SymptomId) {
		
		Symptom symptom = null;
		
		try {
			
			String sql = "SELECT * FROM symptom WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  SymptomId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String SymptomManifestation = rs.getString("manifestation");
			symptom = new Symptom(id, SymptomManifestation);
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return symptom;
	}
	

	@Override
	
	public List<Symptom> showSymptoms() {
		
		List<Symptom> symptomsList = new ArrayList<Symptom>();
		
		try {
			String sql = "SELECT * FROM symptom";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
		
			while (rs.next()) {
				int id = rs.getInt("id");
				String SymptomManifestation = rs.getString("manifestation");
				Symptom newSymptom = new Symptom(id, SymptomManifestation);
				symptomsList.add(newSymptom);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return symptomsList;
	}

	
}
