package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class NewDepartmentController implements Initializable{
	
	@FXML
	private TextField departmentNameTextField;
	
	@FXML
	private Button saveButton;
	
	@FXML 
	public void onSaveAction() {
		try {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		Department department = new Department(departmentNameTextField.getText());
		departmentDao.insert(department);
		Alerts.showAlert("Data has been saved", "The department \"" + department.getName() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
		departmentNameTextField.clear();
		}
		catch (DbException e) {
			Alerts.showAlert("Data has NOT been saved", "The department couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
