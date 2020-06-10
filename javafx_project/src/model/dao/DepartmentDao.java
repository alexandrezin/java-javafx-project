package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert (Department department);
	void update (Department department);
	void delete (int id);
	Department getById (int id);
	List<Department> getAll();
}
