package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert (Department department);
	void update (Department department);
	void delete (int id);
	List<Department> getByParemeter(String parameter);
	List<Department> getAll();
}
