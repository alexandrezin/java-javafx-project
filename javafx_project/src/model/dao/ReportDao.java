package model.dao;

import java.util.List;

import model.entities.Report;

public interface ReportDao {
	void insert (Report report);
	void update (Report report);
	void delete (int id);
	Report getById (int id);
	List<Report> getAll();
}
