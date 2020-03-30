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
		
		try {
			
			String sql = "UPDATE medicalPersonnel SET name=?, department=?, position=?, pathologyId=? WHERE id=?";
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
			
			String sql = "DELETE medicalPersonnel WHERE id=?";
			PreparedStatement d = c.prepareStatement(sql);
			
			d.executeUpdate();
			d.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public MedicalPersonnel getMedicalPersonnel(int medicalPersonnelId) {
		
		MedicalPersonnel medicalPersonnel = null;
		
		try {
			
			String sql = "SELECT * FROM medicalPersonnel WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1,  medicalPersonnelId);
			ResultSet rs = g.executeQuery();
			rs.next();
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String department = rs.getString("department");
			String position = rs.getString("position");
			int pathologyId = rs.getInt("pathology id");
			
			medicalPersonnel = new MedicalPersonnel(id, name, department, position, pathologyId);
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return medicalPersonnel;
		
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
