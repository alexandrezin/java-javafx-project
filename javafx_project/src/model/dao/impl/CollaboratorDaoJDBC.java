package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CollaboratorDao;
import model.entities.Collaborator;
import model.entities.Department;

public class CollaboratorDaoJDBC implements CollaboratorDao{
	
	Connection conn;
			
	public CollaboratorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Collaborator collaborator) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO tb_Collaborator(name_Collaborator, email_Collaborator, registerDate_Collaborator, id_Department_Collaborator) "
									 + "VALUES (?,?,?,?)");
			st.setString(1, collaborator.getName());
			st.setString(2, collaborator.getEmail());
			System.out.println(collaborator.getRegisterDate());
			st.setDate(3, new java.sql.Date(collaborator.getRegisterDate().getTime()));
			st.setInt(4, collaborator.getDepartment().getId());
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
	public void update(Collaborator collaborator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collaborator getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collaborator> getAll() {
		Statement st = null;
		ResultSet rs = null;
		List<Collaborator> collaboratorList = new ArrayList<Collaborator>();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_Collaborator INNER JOIN tb_Department ON tb_Collaborator.id_Department_Collaborator = tb_Department.id_Department");
			
			while (rs.next()) {
				Collaborator collaborator = new Collaborator();
				Department department = new Department();
				//Collaborator
				collaborator.setId(rs.getInt("id_Collaborator"));
				collaborator.setName(rs.getString("name_Collaborator"));
				collaborator.setEmail(rs.getString("email_Collaborator"));
				collaborator.setRegisterDate(rs.getDate("registerDate_Collaborator"));
				//Department
				department.setId(rs.getInt("id_Department_Collaborator"));
				department.setName(rs.getString("name_Department"));
				
				collaborator.setDepartment(department);
				collaboratorList.add(collaborator);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		return collaboratorList;
	}

}
