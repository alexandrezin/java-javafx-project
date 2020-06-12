package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import model.dao.ReportDao;
import model.entities.Report;

public class ReportDaoJDBC implements ReportDao{
	
	private Connection conn;
	
	public ReportDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Report report) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO tb_Report (title_Report, description_Report, date_Report, idColaborator_Report) VALUES (?,?,?,?)");
			st.setString(1, report.getTitle());
			st.setString(2, report.getDescription());
			st.setDate(3, new java.sql.Date(report.getDate().getTime()));
			st.setInt(4, report.getColaborator().getId());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void update(Report report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Report getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
