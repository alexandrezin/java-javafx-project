package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class SearchDepartmentController implements Initializable{

	@FXML
	ListView<Department> departmentListView;
	
	@FXML
	TextField searchDepartmentTextField;
	
	@FXML
	public void onSearchDepartmentTextFieldChange(){
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentListView.getItems().setAll(departmentDao.getByParemeter(searchDepartmentTextField.getText()));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		departmentListView.getItems().setAll(departmentDao.getAll());
		
	}

}
