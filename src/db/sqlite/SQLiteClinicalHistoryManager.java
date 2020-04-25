package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.ClinicalHistoryManager;
import pojos.ClinicalHistory;
import pojos.MedicalPersonnel;

public class SQLiteClinicalHistoryManager implements ClinicalHistoryManager {

	private Connection c;

	public SQLiteClinicalHistoryManager(Connection c) {
		this.c = c;
	}
	
	@Override
	public void add(ClinicalHistory clinicalHistory) {
		try {
			String sql = "INSERT INTO clinical history (id, date_of_entry, date_of_discharge, "
					+ "blood_type, extra_info) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, clinicalHistory.getId());
			prep.setDate(2, clinicalHistory.getDoe());
			prep.setDate(3, clinicalHistory.getDod()); //EL NO HA PUESTO ESTA FECHA EN SU PERRO, NO SE SI HAY QUE DEJARLA O NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
			prep.setString(4, clinicalHistory.getBloodType());
			prep.setString(5, clinicalHistory.getExtraInfo());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(ClinicalHistory clinicalHistory) {
		try {
			//update every aspect of a particular clinical history
			String sql = " UPDATE clinical history SET doe=?, dod=?, bloodType=?, extraInfo=? WHERE id=?"; 
			PreparedStatement s = c.prepareStatement(sql);
			s.setDate(1, clinicalHistory.getDoe());
			s.setDate(2, clinicalHistory.getDod());
			s.setString(3, clinicalHistory.getBloodType());
			s.setString(4, clinicalHistory.getExtraInfo());
			s.setInt(5, clinicalHistory.getId());
			s.executeUpdate();
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public void delete(ClinicalHistory clinicalHistory) {
		try {
			//delete a particular clinical history
			String sql = "DELETE clinical history WHERE id=?"; 
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, clinicalHistory.getId());
			s.executeUpdate();
			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ClinicalHistory getClinicalHistory(int clinicalHistoryId) {
		
		ClinicalHistory clinicalHistory = null;
		
		try {
			
			String sql = "SELECT * FROM clinicalHistory WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  clinicalHistoryId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			Date doe = rs.getDate("doe");
			Date dod = rs.getDate("dod");
			String bloodType = rs.getString("bloodType");
			String extraInfo = rs.getString("extraInfo");
			
			clinicalHistory = new ClinicalHistory(id, doe, dod, bloodType, extraInfo);
			
		} catch(SQLException e) {	
			e.printStackTrace();	
		}
		return clinicalHistory;
	}
	
	@Override
	public List<ClinicalHistory> searchClinicalHistoryById(Integer id) {
		List<ClinicalHistory> clinicalHistoryList = new ArrayList<ClinicalHistory>();
		try {
			String sql = "SELECT * FROM clinical history WHERE id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + id + "%");
			// HAY QUE HACER EL SEARCHCLINICHIST(???)********************************************************************************************************************
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int clinicalhistoryId = rs.getInt("id");
				Date doe = rs.getDate("doe");
				Date dod = rs.getDate("dod");
				String bloodType = rs.getString("bloodType");
				String extraInfo = rs.getString("extraInfo");
				ClinicalHistory newClinicalHistory = new ClinicalHistory(clinicalhistoryId, doe, dod, bloodType, extraInfo);
				clinicalHistoryList.add(newClinicalHistory);
				
			}
		} catch (Exception e) {
			e.printStackTrace();;
		}
		return clinicalHistoryList;
	}

	
	
}
