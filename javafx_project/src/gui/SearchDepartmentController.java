package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class SearchDepartmentController implements Initializable{
	
	@FXML
	TextField searchDepartmentTextField;
	
	@FXML
	ListView<Department> departmentListView;
	
	@FXML
	Button editSelectedButton;
	
	@FXML
	public void onSearchDepartmentTextFieldChange(){
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentListView.getItems().setAll(departmentDao.getByParemeter(searchDepartmentTextField.getText()));
	}
	
	@FXML
	public void onEditSelectedButtonAction() { 
		Department department = departmentListView.getSelectionModel().getSelectedItem();
		if (department != null) {
			PageActions pageAction = new PageActions();
			pageAction.loadWithObject("EditDepartmentController", department);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentListView.getItems().setAll(departmentDao.getAll());
		
	}
}
