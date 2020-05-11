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
		String sql4 = " INSERT INTO Pathology (name, startDate, endingDate, treatmentId)"
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement prep = c.prepareStatement(sql4);
		
		prep.setString(1, pathology.getName());
		prep.setDate(2, pathology.getStartDate());
		prep.setDate(3, pathology.getEndingDate());
		prep.setInt(4,  pathology.getTreatmentId());
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
			
		String sql = "UPDATE Pathology SET name =?, startDate=?, endingDate=?, treatmentId=? WHERE id=?";
		PreparedStatement s = c.prepareStatement(sql);
		
		s.setString(1, pathology.getName());
		s.setDate(2,  pathology.getStartDate());
		s.setDate(3,  pathology.getEndingDate());
		s.setInt(4, pathology.getTreatmentId());
		s.setInt(5, pathology.getId());
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
		String sql = "DELETE Pathology WHERE id=?";
		PreparedStatement d = c.prepareStatement(sql);
		
		d.setInt(1, pathology.getId());
		d.executeUpdate();
		d.close();
		
				
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}

	}

	@Override
	public Pathology searchPathologyById(int pathologyId) {
		
		Pathology newPathology = null;
		
		try {
		
		String sql = "SELECT * FROM Pathology WHERE id = ?";
		PreparedStatement p = c.prepareStatement(sql);
		
		p.setInt(1, pathologyId);
		
		ResultSet rs = p.executeQuery();
		rs.next();
			
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Date startDate = rs.getDate("startDate");
			Date endingDate = rs.getDate("endingDate");
			int treatmentId = rs.getInt("treatmentId");
		
			newPathology = new Pathology(id, name, startDate, endingDate, treatmentId);
		
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}
		
			return newPathology;
				
	}
	
	@Override
	public List<Pathology> searchPathologyByName(String name) {
		// TODO Auto-generated method stub
		
		//Create empty list of pathologies.
		
		List <Pathology> pathologyList = new ArrayList<Pathology>();
		try {
			
			//Search pathology that has the same name as the one inserted by the user.
			
			String sql = "SELECT * FROM Pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String pathologyName = rs.getString("name");
				Date startDate = rs.getDate("startDate");
				Date endingDate = rs.getDate("endingDate");
				int treatmentId = rs.getInt("treatmentId");
				
				//Creates a new pathology
				
				Pathology pathology = new Pathology(id, pathologyName, startDate, endingDate, treatmentId);
				
				
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
				Date startDate = rs.getDate("startDate");
				Date endingDate = rs.getDate("endingDate");
				Integer treatmentId = rs.getInt("treatmentId");
				
				Pathology pathology = new Pathology(id, name, startDate, endingDate, treatmentId);
				
				pathologyList.add(pathology);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pathologyList;
	}

}
