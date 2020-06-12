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
import model.dao.ColaboratorDao;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Colaborator;
import model.entities.Department;

public class NewColaboratorController implements Initializable{
	
	@FXML
	TextField colaboratorNameField;
	
	@FXML
	TextField colaboratorEmailField;
	
	@FXML
	Button saveButton;
	
	@FXML
	ComboBox<Department> departmentComboBox;
	
	@FXML
	public void onSaveButtonAction(){
		try {
			Colaborator colaborator = new Colaborator();
			Department department = departmentComboBox.getValue();
			
			colaborator.setName(colaboratorNameField.getText());
			colaborator.setEmail(colaboratorEmailField.getText());
			colaborator.setRegisterDate(new Date());
			colaborator.setDepartment(department);
			
			ColaboratorDao colaboratorDao = DaoFactory.createColaboratorDao();
			colaboratorDao.insert(colaborator);
			Alerts.showAlert("Data has been saved", "The colaborator \"" + colaborator.getName() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			colaboratorNameField.clear();
			colaboratorEmailField.clear();
			departmentComboBox.setValue(null);
		}
		
		catch (DbException e){
			Alerts.showAlert("Data has NOT been saved", "The colaborator couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentComboBox.getItems().addAll(departmentDao.getAll());
	}
}
