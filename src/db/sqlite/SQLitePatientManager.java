package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String sql = " INSERT patient (name , gender , state ,dob, pathology_id) "
				+ "VALUES (?,?,?,? );"; 
		PreparedStatement prep =c.prepareStatement(sql);
		prep.setString(1,patient.getName());
		prep.setString(2,patient.getGender());
		prep.setString(3,patient.getState());
		prep.setDate(4,patient.getDob());
		prep.setInt(4,patient.getPathology_id());
		prep.executeUpdate();
		prep.close();
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void update(Patient patient) {
		// TODO 

	}

	@Override
	public void delete(Patient patient) {
		// TODO 

	}

	@Override
	public List<Patient> searchPatientById(Integer id) {
		List<Patient> patientsList= new ArrayList<Patient>();
		try {
			String sql ="SELECT * FROM patient WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1,"%" + id + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int Patientid = rs.getInt("id");
				String PatientName = rs.getString("name");
				String PatientGender = rs.getString("gender");
				String PatientState = rs.getString("state");
				Date PatientDOB =rs.getDate("dob");
				int PatientPathology_id = rs.getInt("pathology_id");
				Patient newPatient = new Patient(Patientid, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id);
				patientsList.add(newPatient);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return patientsList;
	}

	@Override
	public List<Patient> searchPatientByName(String name) {
		
		List<Patient> patientsList= new ArrayList<Patient>();
		try {
			String sql ="SELECT * FROM patient WHERE name LIKE ?";
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
				Patient newPatient = new Patient(id, PatientName, PatientGender ,PatientState,PatientDOB,PatientPathology_id);//pathology_id
				patientsList.add(newPatient);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return patientsList;
	}



}
