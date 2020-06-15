package model.dao;

import java.util.List;

import model.entities.Collaborator;

public interface CollaboratorDao {
	void insert (Collaborator collaborator);
	void update (Collaborator collaborator);
	void delete (int id);
	Collaborator getById (int id);
	List<Collaborator> getAll();
}
