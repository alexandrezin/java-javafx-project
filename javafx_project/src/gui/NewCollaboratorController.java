package gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
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

public class NewCollaboratorController implements Initializable{
	
	@FXML
	private TextField collaboratorNameField;
	
	@FXML
	private TextField collaboratorEmailField;
	
	@FXML
	private Button saveButton;
	
	@FXML
	private ComboBox<Department> departmentComboBox;
	
	@FXML
	public void onSaveButtonAction(){
		try {
			Collaborator collaborator = new Collaborator();
			Department department = departmentComboBox.getValue();
			
			collaborator.setName(collaboratorNameField.getText());
			collaborator.setEmail(collaboratorEmailField.getText());
			collaborator.setRegisterDate(new Date());
			collaborator.setDepartment(department);
			
			CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
			collaboratorDao.insert(collaborator);
			Alerts.showAlert("Data has been saved", "The collaborator \"" + collaborator.getName() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			collaboratorNameField.clear();
			collaboratorEmailField.clear();
			departmentComboBox.setValue(null);
		}
		
		catch (DbException e){
			Alerts.showAlert("Data has NOT been saved", "The collaborator couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentComboBox.getItems().addAll(departmentDao.getAll());
	}
}
