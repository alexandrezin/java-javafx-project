package model.entities;

import java.util.Date;

public class Report {
	
	private int id;
	private String title;
	private String description;
	private Date date;
	
	private Collaborator collaborator;
	
	public Report() {
	}

	public Report(String title, String description, Date date, Collaborator collaborator) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.collaborator = collaborator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Collaborator getCollaborator() {
		return collaborator;
	}

	public void setColaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	@Override
	public String toString() {
		return date + ": " + title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
