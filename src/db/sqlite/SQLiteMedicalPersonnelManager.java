package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.MedicalPersonnelManager;
import pojos.MedicalPersonnel;
import pojos.Pathology;

public class SQLiteMedicalPersonnelManager implements MedicalPersonnelManager {
	
	private Connection c;
	
public SQLiteMedicalPersonnelManager(Connection c) {
		
		this.c = c;
		
	}

	@Override
	public void add(MedicalPersonnel medicalPersonnel) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
		String sql = " INSERT INTO MedicalPersonnel (name, department, position, pathologyId)"
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, medicalPersonnel.getName());
		prep.setString(2, medicalPersonnel.getDepartment());
		prep.setString(3, medicalPersonnel.getPosition());
		prep.setInt(4, medicalPersonnel.getPathologyId());
		prep.executeUpdate();
		prep.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(MedicalPersonnel medicalPersonnel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(MedicalPersonnel medicalPersonnel) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MedicalPersonnel> searchMedicalPersonnelById(Integer id) {
		// TODO Auto-generated method stub
		
		List <MedicalPersonnel> medicalPersonnelList = new ArrayList<MedicalPersonnel>();
		try {
			
			//Search medical personnel that has the same id as the one inserted by the user.
			
			String sql = "SELECT * FROM pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int medicalPersonnelId = rs.getInt("id");
				String name = rs.getString("name");
				String department = rs.getString("department");
				String position = rs.getString("position");
				int pathologyId = rs.getInt("pathology Id");
				MedicalPersonnel medicalPersonnel = new MedicalPersonnel(medicalPersonnelId, name, department, position, pathologyId);
				
				medicalPersonnelList.add(medicalPersonnel);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return medicalPersonnelList;
	}

	@Override
	public List<MedicalPersonnel> searchMedicalPersonnelByPathologyId(Integer pathologyId) {
		// TODO Auto-generated method stub
		
		List <MedicalPersonnel> medicalPersonnelList = new ArrayList<MedicalPersonnel>();
		try {
			
			//Search medical personnel that has the same id as the one inserted by the user.
			
			String sql = "SELECT * FROM pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String department = rs.getString("department");
				String position = rs.getString("position");
				int medicalPersonnelPathologyId = rs.getInt("pathology Id");
				MedicalPersonnel medicalPersonnel = new MedicalPersonnel(id, name, department, position, medicalPersonnelPathologyId);
				
				medicalPersonnelList.add(medicalPersonnel);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return medicalPersonnelList;
	}

}
