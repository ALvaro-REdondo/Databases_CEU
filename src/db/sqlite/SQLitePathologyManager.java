package db.sqlite;

import java.util.ArrayList;
import java.util.List;

import db.interfaces.PathologyManager;
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
		String sql = " INSERT INTO Pathology (name, duration, startDate, endingDate)"
				+ "VALUES(?, ?, ?, ?);";
		PreparedStatement prep = c.prepareStatement(sql);
		
		prep.setString(1, pathology.getName());
		prep.setInt(2, pathology.getDuration());
		prep.setDate(3, pathology.getStartDate());
		prep.setDate(4, pathology.getEndingDate());
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

	}

	@Override
	public void delete(Pathology pathology) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pathology> searchPathologyById(Integer id) {
		// TODO Auto-generated method stub
		
		List <Pathology> pathologyList = new ArrayList<Pathology>();
		try {
			
			//Search pathology that has the same id as the one inserted by the user.
			
			String sql = "SELECT * FROM pathology WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				
				int pathologyId = rs.getInt("id");
				String name = rs.getString("name");
				int duration = rs.getInt("duration");
				Date startDate = rs.getDate("start date");
				Date endingDate = rs.getDate("Ending Date");
				Pathology pathology = new Pathology(pathologyId, name, duration, startDate, endingDate);
				
				pathologyList.add(pathology);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return null;
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
				Pathology pathology = new Pathology(id, pathologyName, duration, startDate, endingDate);
				
				pathologyList.add(pathology);
				
			}
		} catch(SQLException e) {
			
			
			e.printStackTrace();
			
		};
		return pathologyList;
	}

}
