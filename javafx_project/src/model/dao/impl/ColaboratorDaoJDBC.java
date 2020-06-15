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
import model.dao.ColaboratorDao;
import model.entities.Colaborator;
import model.entities.Department;

public class ColaboratorDaoJDBC implements ColaboratorDao{
	
	Connection conn;
			
	public ColaboratorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Colaborator colaborator) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO tb_Colaborator(name_Colaborator, email_Colaborator, registerDate_Colaborator, idDepartment_Colaborator) "
									 + "VALUES (?,?,?,?)");
			st.setString(1, colaborator.getName());
			st.setString(2, colaborator.getEmail());
			System.out.println(colaborator.getRegisterDate());
			st.setDate(3, new java.sql.Date(colaborator.getRegisterDate().getTime()));
			st.setInt(4, colaborator.getDepartment().getId());
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
	public void update(Colaborator colaborator) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Colaborator getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Colaborator> getAll() {
		Statement st = null;
		ResultSet rs = null;
		List<Colaborator> colaboratorList = new ArrayList<Colaborator>();
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_Colaborator INNER JOIN tb_Department ON tb_Colaborator.idDepartment_Colaborator = tb_Department.id_Department");
			
			while (rs.next()) {
				Colaborator colaborator = new Colaborator();
				Department department = new Department();
				//Collaborator
				colaborator.setId(rs.getInt("id_Colaborator"));
				colaborator.setName(rs.getString("name_Colaborator"));
				colaborator.setEmail(rs.getString("email_Colaborator"));
				colaborator.setRegisterDate(rs.getDate("registerDate_Colaborator"));
				//Department
				department.setId(rs.getInt("idDepartment_Colaborator"));
				department.setName(rs.getString("name_Department"));
				
				colaborator.setDepartment(department);
				colaboratorList.add(colaborator);
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		
		return colaboratorList;
	}

}
