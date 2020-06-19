package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.dao.CollaboratorDao;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Collaborator;
import model.entities.Department;

public class EditCollaboratorController implements Initializable{

	private Collaborator collaborator;
	
	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
		
		collaboratorNameField.setText(this.collaborator.getName());
		collaboratorEmailField.setText(this.collaborator.getEmail());
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentComboBox.getItems().setAll(departmentDao.getAll());
		departmentComboBox.setValue(this.collaborator.getDepartment());
	}
	
	@FXML
	TextField collaboratorNameField;
	
	@FXML
	TextField collaboratorEmailField;
	
	@FXML
	Button updateButton;
	
	@FXML
	Button cancelButton;
	
	@FXML
	ComboBox<Department> departmentComboBox;
	
	@FXML
	public void onCancelButtonAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchCollaboratorScreen");
	}
	
	
	@FXML
	public void onUpdateButtonAction(){
		try {
			//Update collaborator info
			collaborator.setName(collaboratorNameField.getText());
			collaborator.setEmail(collaboratorEmailField.getText());
			collaborator.setDepartment(departmentComboBox.getValue());
			//Update Database
			CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
			collaboratorDao.update(collaborator);
			//Done Alert
			Alerts.showAlert("Data has been saved", "The collaborator \"" + collaborator.getName() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			//Return to Main Screen
			PageActions pageAction = new PageActions();
			pageAction.load("SearchCollaboratorScreen");
		}
		
		catch (DbException e){
			Alerts.showAlert("Data has NOT been saved", "The collaborator couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
