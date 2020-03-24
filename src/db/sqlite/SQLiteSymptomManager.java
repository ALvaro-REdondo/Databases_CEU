package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import db.interfaces.SymptomManager;
import pojos.Symptom;

public class SQLiteSymptomManager implements SymptomManager {
	
	private Connection c;
	public SQLiteSymptomManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void add(Symptom symptom) {
		try {
			String sql = "INSERT symptom ( manifestation)"+" VALUES (?)"; 
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,symptom.getManifestation());
			prep.executeUpdate();
			prep.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
	}
		
	

	@Override
	public List<Symptom> searchSymptomById(Integer id) {
		List<Symptom> symptomsList= new ArrayList<Symptom>();
		try {
			String sql ="SELECT * FROM symptom WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,"%" + id + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int Symptomid = rs.getInt("id");
				String SymptomManifestation = rs.getString("manifestation");
				Symptom newSymptom = new Symptom(Symptomid, SymptomManifestation);
				symptomsList.add(newSymptom);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return symptomsList;
	}

	@Override
	public List<Symptom> searchASymptomByManifestation(String manifestation) {
		List<Symptom> symptomsList= new ArrayList<Symptom>();
		try {
			String sql ="SELECT * FROM symptom WHERE name LIKE ?";
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
}
