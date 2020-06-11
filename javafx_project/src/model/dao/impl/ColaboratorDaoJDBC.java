package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import db.DbException;
import model.dao.ColaboratorDao;
import model.entities.Colaborator;

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
		// TODO Auto-generated method stub
		return null;
	}

}
