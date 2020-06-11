package model.dao;

import java.util.List;

import model.entities.Colaborator;

public interface ColaboratorDao {
	void insert (Colaborator colaborator);
	void update (Colaborator colaborator);
	void delete (int id);
	Colaborator getById (int id);
	List<Colaborator> getAll();
}
