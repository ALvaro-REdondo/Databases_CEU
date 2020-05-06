package db.sqlite;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.PathologyManager;
import pojos.MedicalPersonnel;
import pojos.Pathology;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLitePathologyManager implements PathologyManager {
	
	private Connection c;
	
	public SQLitePathologyManager(Connection c) {
		
		this.c = c;
		
	}
	
	@Override
	public void add(Pathology pathology) {
		try {
			// TODO Auto-generated method stub
		String sql = " INSERT INTO Pathology (name, duration, startDate, endingDate, treatmentId)"
				+ "VALUES(?, ?, ?, ?, ?);";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, pathology.getName());
		prep.setInt(2, pathology.getDuration());
		prep.setDate(3, pathology.getStartDate());
		prep.setDate(4, pathology.getEndingDate());
		prep.setInt(5,  pathology.getTreatmentId());
		prep.executeUpdate();
		prep.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Pathology pathology) {
		// TODO Auto-generated method stub
		try {
			
		String sql = "UPDATE pathology SET name =?, duration=?, startDate=?, endingDate=?, treatmentId=? WHERE id=?";
		PreparedStatement s = c.prepareStatement(sql);
		
		s.setString(1, pathology.getName());
		s.setInt(2, pathology.getDuration());
		s.setDate(3,  pathology.getStartDate());
		s.setDate(4,  pathology.getEndingDate());
		s.setInt(5, pathology.getTreatmentId());
		s.setInt(6, pathology.getId());
		s.executeUpdate();
		s.close();
		
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
	}

	@Override
	public void delete(Pathology pathology) {
		// TODO Auto-generated method stub
		
		try {
		String sql = "DELETE pathology WHERE id=?";
		PreparedStatement d = c.prepareStatement(sql);
		
		d.setInt(1, pathology.getId());
		d.executeUpdate();
		d.close();
		
				
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public List<Pathology> searchPathologyById(Integer id) {
		// TODO Auto-generated method stub
		
		List <Pathology> pathologyList = new ArrayList<Pathology>();
		try {
			
			//Search pathology that has the same id as the one inserted by the user.
			
			String sql = "SELECT * FROM Pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int pathologyId = rs.getInt("id");
				String name = rs.getString("name");
				int duration = rs.getInt("duration");
				Date startDate = rs.getDate("start date");
				Date endingDate = rs.getDate("Ending Date");
				int treatmentId = rs.getInt("Treatment id");
				Pathology pathology = new Pathology(pathologyId, name, duration, startDate, endingDate, treatmentId);
				
				pathologyList.add(pathology);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return pathologyList;
	}

	@Override
	public List<Pathology> searchPathologyByName(String name) {
		// TODO Auto-generated method stub
		
		//Create empty list of pathologies.
		
		List <Pathology> pathologyList = new ArrayList<Pathology>();
		try {
			
			//Search pathology that has the same name as the one inserted by the user.
			
			String sql = "SELECT * FROM pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String pathologyName = rs.getString("name");
				int duration = rs.getInt("duration");
				Date startDate = rs.getDate("start date");
				Date endingDate = rs.getDate("Ending Date");
				int treatmentId = rs.getInt("treatment id");
				
				//Creates a new pathology
				
				Pathology pathology = new Pathology(id, pathologyName, duration, startDate, endingDate, treatmentId);
				
				
				pathologyList.add(pathology);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return pathologyList;
	}
	
	@Override
	
	public List<Pathology> showPathologies() {
		
		List<Pathology> pathologyList = new ArrayList<Pathology>();
		
		try {
			String sql = "SELECT * FROM Pathology";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
		
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Integer duration = rs.getInt("duration");
				Date startDate = rs.getDate("Start Date");
				Date endingDate = rs.getDate("Ending Date");
				Integer treatmentId = rs.getInt("Treatment id");
				
				Pathology pathology = new Pathology(id, name, duration, startDate, endingDate, treatmentId);
				
				pathologyList.add(pathology);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pathologyList;
	}

}
