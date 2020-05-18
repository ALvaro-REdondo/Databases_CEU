package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.interfaces.MedicalPersonnelManager;
import pojos.MedicalPersonnel;
import pojos.Pathology;
import pojos.Symptom;

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
		String sql = " INSERT INTO MedicalPersonnel (name, department, position, pathology_id)"
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
		
		try {
			
			String sql = "UPDATE MedicalPersonnel SET name=?, department=?, position=?, pathology_id=? WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			
			s.setString(1, medicalPersonnel.getName());
			s.setString(2,  medicalPersonnel.getDepartment());
			s.setString(3, medicalPersonnel.getPosition());
			s.setInt(4,  medicalPersonnel.getPathologyId());
			s.setInt(5,  medicalPersonnel.getId());
			
			s.executeUpdate();
			s.close();
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public void delete(MedicalPersonnel medicalPersonnel) {
		// TODO Auto-generated method stub

		try {
			
			String sql = "DELETE FROM MedicalPersonnel WHERE id=?";
			PreparedStatement d2 = c.prepareStatement(sql);
			
			d2.setInt(1, medicalPersonnel.getId());
			d2.executeUpdate();
			d2.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}

	

	

	@Override
	
	public List<MedicalPersonnel> showMedicalPersonnel() {
		
		List<MedicalPersonnel> medicalPersonnelList = new ArrayList<MedicalPersonnel>();
		
		try {
			String sql = "SELECT * FROM MedicalPersonnel";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
		
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String department = rs.getString("department");
				String position = rs.getString("position");
				Integer pathologyId = rs.getInt("pathology_id");
				MedicalPersonnel newMedicalPersonnel = new MedicalPersonnel(id, name, department, position, pathologyId);
				
				medicalPersonnelList.add(newMedicalPersonnel);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return medicalPersonnelList;
	}

	@Override
	public MedicalPersonnel searchMedicalPersonnelById(Integer medicalPersonnelId) {
		// TODO Auto-generated method stub

		MedicalPersonnel newMedicalPersonnel = null;
		
		try {
			
			String sql = "SELECT * FROM MedicalPersonnel WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			
			g.setInt(1,  medicalPersonnelId);
			
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String department = rs.getString("department");
			String position = rs.getString("position");
			int pathologyId = rs.getInt("pathology_id");
			
			newMedicalPersonnel = new MedicalPersonnel(id, name, department, position, pathologyId);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return newMedicalPersonnel;
		
	}
	
	@Override
	public List<MedicalPersonnel> searchMedicalPersonnelByName(String name) {
		
		//Create empty list of medical personnel
		
		List<MedicalPersonnel> medicalPersonnelList = new ArrayList<MedicalPersonnel>();
		
		try {
			
			//Search medical personnel that has the same name as the one inserted by the user.
			
			String sql = "SELECT * FROM MedicalPersonnel WHERE name LIKE ?";
			
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%" + name + "%");
			ResultSet rs = prep.executeQuery();
			while(rs.next()) { 
				
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				String department = rs.getString("department");
				String position = rs.getString("position");
				int pathologyId = rs.getInt("pathology_id");
				
				//Create a new medical personnel
				
				MedicalPersonnel medicalPersonnel = new MedicalPersonnel(id, name1, department, position, pathologyId);
				
				medicalPersonnelList.add(medicalPersonnel);
				
			}
			
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return medicalPersonnelList;
		
	}
	
	@Override
	public List<MedicalPersonnel> searchMedicalPersonnelByPathologyId(Integer pathologyId) {
		// TODO Auto-generated method stub
		
		List <MedicalPersonnel> medicalPersonnelList = new ArrayList<MedicalPersonnel>();
		try {
			
			//Search medical personnel that has the same id as the one inserted by the user.
			
			String sql = "SELECT * FROM MedicalPersonnel WHERE pathology_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1,  pathologyId);
			ResultSet rs2 = prep.executeQuery();
			while(rs2.next()) {
				
				int id = rs2.getInt("id");
				String name = rs2.getString("name");
				String department = rs2.getString("department");
				String position = rs2.getString("position");
				int medicalPersonnelPathologyId = rs2.getInt("pathology_id");
				MedicalPersonnel medicalPersonnel = new MedicalPersonnel(id, name, department, position, medicalPersonnelPathologyId);
				
				medicalPersonnelList.add(medicalPersonnel);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return medicalPersonnelList;
	}

}
