package model.dao;

import java.util.Date;
import java.util.List;

import model.entities.Department;
import model.entities.Report;

public interface ReportDao {
	void insert (Report report);
	void update (Report report);
	void delete (int id);
	List<Report> getByParemeter(String parameter, Date date);
	List<Report> getByDepartment(Department department);
	List<Report> getByDate(Date date);
	List<Report> getAll();
}
