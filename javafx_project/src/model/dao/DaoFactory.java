package model.dao;

import db.DB;
import model.dao.impl.ColaboratorDaoJDBC;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.ReportDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDao createDepartmentDao(){
		return new DepartmentDaoJDBC(DB.openConnection());
	}
	public static ColaboratorDao createColaboratorDao(){
		return new ColaboratorDaoJDBC(DB.openConnection());
	}
	public static ReportDao createReportDao(){
		return new ReportDaoJDBC(DB.openConnection());
	}
}
