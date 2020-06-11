package model.dao;

import db.DB;
import model.dao.impl.ColaboratorDaoJDBC;
import model.dao.impl.DepartmentDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDao createDepartmentDao(){
		return new DepartmentDaoJDBC(DB.openConnection());
	}
	public static ColaboratorDao createColaboratorDao(){
		return new ColaboratorDaoJDBC(DB.openConnection());
	}
}
