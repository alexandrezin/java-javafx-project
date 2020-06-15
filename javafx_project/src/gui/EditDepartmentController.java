package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class EditDepartmentController implements Initializable{
	
	private Department department;
	
	public void setDepartment (Department department) {
		this.department = department;
		departmentNameTextField.setText(department.getName());
	}
	
	@FXML
	TextField departmentNameTextField;
	
	@FXML
	Button departmentUptadeNameButton;
	
	@FXML
	public void onDepartmentUpdateButtonAction() {
		try {
			department.setName(departmentNameTextField.getText());
			DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
			departmentDao.update(department);
			Alerts.showAlert("Data has been saved", "The department \"" + department.getName() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			PageActions pageAction = new PageActions();
			pageAction.load("/gui/SearchDepartmentScreen.fxml");
		}
		catch(DbException e) {
			Alerts.showAlert("Data has NOT been saved", "The department couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}
