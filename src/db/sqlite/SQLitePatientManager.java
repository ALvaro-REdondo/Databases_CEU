package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.interfaces.PatientManager;
import pojos.Patient;

public class SQLitePatientManager implements PatientManager {
	
	private Connection c;
	
	public SQLitePatientManager(Connection c) {
		this.c=c;
	}
	
	@Override
	public void add(Patient patient) {
		try {
		String sql = " INSERT INTO Patient (name , gender , state , dob , pathology_id , clinical_history_id) "
				+ "VALUES (?,?,?,?,? ,?);"; 
		PreparedStatement prep =c.prepareStatement(sql);
		prep.setString(1,patient.getName());
		prep.setString(2,patient.getGender());
		prep.setString(3,patient.getState());
		prep.setDate(4,patient.getDob());
		prep.setInt(5,patient.getPathology_id());
		prep.setInt(6, patient.getClinicalhistory_id());
		prep.executeUpdate();
		prep.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	
	@Override
	public void update(Patient patient) {
		try {
		String sql = " UPDATE Patient  SET name=?,  gender=?, state=?, DOB=? , pathology_id = ?, clinical_history_id=?\r\n"; 
		 PreparedStatement s =c.prepareStatement(sql);
		 s.setString(1,patient.getName());
		 s.setString(2,patient.getGender());
		 s.setString(3,patient.getState());
		 s.setDate(4,patient.getDob());
		 s.setInt(5,patient.getPathology_id());
		 s.setInt(6,patient.getClinicalhistory_id());
		 s.executeUpdate();
		 s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Patient patient) {
		try {
			String sql = " DELETE FROM Patient WHERE id =?";
			PreparedStatement s= c.prepareStatement(sql);
			s.setInt(1, patient.getId());
			s.executeUpdate();
			s.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}

	}



	@Override
	public List<Patient> searchPatientByName(String name) {
		
		List<Patient> patientsList= new ArrayList<Patient>();
		try {
			String sql ="SELECT * FROM Patient WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,"%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String PatientName = rs.getString("name");
				String PatientGender = rs.getString("gender");
				String PatientState = rs.getString("state");
				Date PatientDOB =rs.getDate("dob");
				int PatientPathology_id = rs.getInt("pathology_id");
				int PatientClinicalHistory_id = rs.getInt("cliniclaHistory_id");
				Patient newPatient = new Patient(id, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id,PatientClinicalHistory_id);//pathology_id
				patientsList.add(newPatient);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return patientsList;
	}
	

	@Override

	public Patient searchPatientById(int PatientId) {
		
		Patient patient = null;
		
		try {
			
			String sql = "SELECT * FROM Patient WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  PatientId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String PatientName = rs.getString("name");
			String PatientGender = rs.getString("gender");
			String PatientState = rs.getString("state");
			Date PatientDOB =rs.getDate("dob");
			int PatientPathology_id = rs.getInt("pathology_id");
			int PatientClinicalHistory_id = rs.getInt("cliniclaHistory_id");
			patient = new Patient(id, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id,PatientClinicalHistory_id );//pathology_id
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return patient;
	}
	
	@Override

	public List<Patient> searchPatientByPathologyId (int pathologyId) {
		
		List<Patient> patientsList= new ArrayList<Patient>();
		try {
			
			String sql = "SELECT * FROM Patient WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  pathologyId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String PatientName = rs.getString("name");
			String PatientGender = rs.getString("gender");
			String PatientState = rs.getString("state");
			Date PatientDOB =rs.getDate("dob");
			int PatientPathology_id = rs.getInt("pathology_id");
			int PatientClinicalHistory_id = rs.getInt("clinical_history_id");
			Patient newpatient = new Patient(id, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id,PatientClinicalHistory_id );//pathology_id
			patientsList.add(newpatient);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return patientsList;
	}
	

	@Override
	
	public List<Patient> showPatients() {
		
		List<Patient> patientsList = new ArrayList<Patient>();
		
		try {
			String sql = "SELECT * FROM patient";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
		
			while (rs.next()) {
				int id = rs.getInt("id");
				String PatientName = rs.getString("name");
				String PatientGender = rs.getString("gender");
				String PatientState = rs.getString("state");
				Date PatientDOB =rs.getDate("dob");
				int PatientPathology_id = rs.getInt("pathology_id");
				int PatientClinicalHistory_id = rs.getInt("cliniclaHistory_id");
				Patient newPatient = new Patient(id, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id,PatientClinicalHistory_id);//pathology_id
				patientsList.add(newPatient);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return patientsList;
	}

	

}
