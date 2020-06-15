package model.dao;

import db.DB;
import model.dao.impl.CollaboratorDaoJDBC;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.ReportDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDao createDepartmentDao(){
		return new DepartmentDaoJDBC(DB.openConnection());
	}
	public static CollaboratorDao createCollaboratorDao(){
		return new CollaboratorDaoJDBC(DB.openConnection());
	}
	public static ReportDao createReportDao(){
		return new ReportDaoJDBC(DB.openConnection());
	}
}
