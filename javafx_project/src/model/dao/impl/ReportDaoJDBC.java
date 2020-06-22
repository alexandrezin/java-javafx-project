package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ReportDao;
import model.entities.Collaborator;
import model.entities.Department;
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
			st = conn.prepareStatement("INSERT INTO tb_Report (title_Report, description_Report, date_Report, id_Collaborator_Report) VALUES (?,?,?,?)");
			st.setString(1, report.getTitle());
			st.setString(2, report.getDescription());
			st.setDate(3, new java.sql.Date(report.getDate().getTime()));
			st.setInt(4, report.getCollaborator().getId());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Report report) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE tb_Report SET title_Report = ?, description_Report = ?, date_Report = ?, id_Collaborator_Report = ? WHERE id_Report = ?");
			st.setString(1, report.getTitle());
			st.setString(2, report.getDescription());
			st.setDate(3, new java.sql.Date(report.getDate().getTime()));
			st.setInt(4, report.getCollaborator().getId());
			st.setInt(5, report.getId());
			st.executeUpdate();
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Report> getByParemeter(String parameter, Date date) {
		Statement st = null;
		ResultSet rs = null;
		
		List<Report> reportList = new ArrayList<Report>();
		
		try {
			st = conn.createStatement();
			
			if(date == null) {
				rs = st.executeQuery("SELECT * FROM tb_Report INNER JOIN tb_Collaborator ON tb_Report.id_Collaborator_Report = tb_Collaborator.id_Collaborator\n" + 
						 "INNER JOIN tb_Department ON tb_Collaborator.id_Department_Collaborator = tb_Department.id_Department\n" + 
						 "WHERE title_Report LIKE '%" + parameter + "%' OR name_Collaborator LIKE '%" + parameter + "%' OR name_Department LIKE '%" + parameter + "%'");
			}
			
			else {
				rs = st.executeQuery("SELECT * FROM tb_Report INNER JOIN tb_Collaborator ON tb_Report.id_Collaborator_Report = tb_Collaborator.id_Collaborator\n" + 
						 "INNER JOIN tb_Department ON tb_Collaborator.id_Department_Collaborator = tb_Department.id_Department\n" + 
						 "WHERE date_Report = '" + new java.sql.Date(date.getTime()) + "' AND (title_Report LIKE '%" + parameter + "%' OR name_Collaborator LIKE '%" + parameter + "%' OR name_Department LIKE '%" + parameter + "%')");
			}

			while(rs.next()) {
				Report report = new Report();
				Collaborator collaborator = new Collaborator();
				Department department = new Department();
				
				department.setId(rs.getInt("id_Department"));
				department.setName(rs.getString("name_Department"));
				
				collaborator.setId(rs.getInt("id_Collaborator"));
				collaborator.setName(rs.getString("name_Collaborator"));
				collaborator.setEmail(rs.getString("email_Collaborator"));
				collaborator.setRegisterDate(rs.getDate("registerDate_Collaborator"));
				collaborator.setDepartment(department);
				
				report.setId(rs.getInt("id_Report"));
				report.setTitle(rs.getString("title_Report"));
				report.setDescription(rs.getString("description_Report"));
				report.setDate(rs.getDate("date_Report"));
				report.setColaborator(collaborator);
				
				reportList.add(report);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return reportList;
	}

	
	@Override
	public List<Report> getAll() {
		Statement st = null;
		ResultSet rs = null;
		
		List<Report> reportList = new ArrayList<Report>();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_Report INNER JOIN tb_Collaborator ON tb_Report.id_Collaborator_Report = tb_Collaborator.id_Collaborator "
							    +"INNER JOIN tb_Department ON tb_Collaborator.id_Department_Collaborator = tb_Department.id_Department");
			
			while(rs.next()) {
				Report report = new Report();
				Collaborator collaborator = new Collaborator();
				Department department = new Department();
				
				department.setId(rs.getInt("id_Department"));
				department.setName(rs.getString("name_Department"));
				
				collaborator.setId(rs.getInt("id_Collaborator"));
				collaborator.setName(rs.getString("name_Collaborator"));
				collaborator.setEmail(rs.getString("email_Collaborator"));
				collaborator.setRegisterDate(rs.getDate("registerDate_Collaborator"));
				collaborator.setDepartment(department);
				
				report.setId(rs.getInt("id_Report"));
				report.setTitle(rs.getString("title_Report"));
				report.setDescription(rs.getString("description_Report"));
				report.setDate(rs.getDate("date_Report"));
				report.setColaborator(collaborator);
				
				reportList.add(report);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return reportList;
	}
}
