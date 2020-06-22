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
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO tb_Department(name_Department) VALUES (?)");
			st.setString(1, department.getName());
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
	public void update(Department department) {
PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE tb_Department SET name_Department = ? WHERE id_Department = ?");
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
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
	public List<Department> getByParemeter(String parameter) {
		List<Department> departmentList = new  ArrayList<Department>();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_Department WHERE name_Department LIKE '%" + parameter + "%'");
			while(rs.next()) {
			departmentList.add(new Department(rs.getInt("id_Department"), rs.getString("name_Department")));
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return departmentList;
	}
	
	@Override
	public List<Department> getAll() {
		List<Department> departmentList = new  ArrayList<Department>();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_Department");
			while(rs.next()) {
				departmentList.add(new Department(rs.getInt("id_Department"), rs.getString("name_Department")));
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return departmentList;
	}
}
