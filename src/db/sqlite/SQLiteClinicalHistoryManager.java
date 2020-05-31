package db.sqlite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.interfaces.ClinicalHistoryManager;
import pojos.ClinicalHistory;

public class SQLiteClinicalHistoryManager implements ClinicalHistoryManager {

	private Connection c;

	public SQLiteClinicalHistoryManager(Connection c) {
		this.c = c;
	}

	@Override
	public void add(ClinicalHistory clinicalHistory) {
		try {
			String sql = "INSERT INTO ClinicalHistory (doe, dod, "
					+ "bloodType, extraInfo, allergyId) VALUES (?, ?, ?, ?, ?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setDate(1, clinicalHistory.getDoe());
			prep.setDate(2, clinicalHistory.getDod()); // EL NO HA PUESTO ESTA FECHA EN SU PERRO, NO SE SI HAY QUE
														// DEJARLA O NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
			prep.setString(3, clinicalHistory.getBloodType());
			prep.setString(4, clinicalHistory.getExtraInfo());
			prep.setInt(5, clinicalHistory.getAllergyId());
			prep.executeUpdate();
			prep.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ClinicalHistory clinicalHistory) {
		try {
			// update every aspect of a particular clinical history
			String sql = " UPDATE ClinicalHistory SET doe=?, dod=?, bloodType=?, extraInfo=?, allergyId=? WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setDate(1, clinicalHistory.getDoe());
			s.setDate(2, clinicalHistory.getDod());
			s.setString(3, clinicalHistory.getBloodType());
			s.setString(4, clinicalHistory.getExtraInfo());
			s.setInt(5, clinicalHistory.getId());
			s.setInt(6, clinicalHistory.getAllergyId());
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(ClinicalHistory clinicalHistory) {
		try {
			// delete a particular clinical history
			String sql = "DELETE FROM ClinicalHistory WHERE id=?";
			PreparedStatement s = c.prepareStatement(sql);
			s.setInt(1, clinicalHistory.getId());
			s.executeUpdate();
			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ClinicalHistory searchClinicalHistoryById(int clinicalHistoryId) {

		ClinicalHistory clinicalHistory = null;

		try {

			String sql = "SELECT * FROM ClinicalHistory WHERE id=?";
			PreparedStatement g = c.prepareStatement(sql);
			g.setInt(1, clinicalHistoryId);
			ResultSet rs = g.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			Date doe = rs.getDate("doe");
			Date dod = rs.getDate("dod");
			String bloodType = rs.getString("bloodType");
			String extraInfo = rs.getString("extraInfo");
			int allergyId = rs.getInt("allergyId");

			clinicalHistory = new ClinicalHistory(id, doe, dod, bloodType, extraInfo, allergyId);

		} catch (SQLException e) {
			return null;
		}
		return clinicalHistory;
	}

}
