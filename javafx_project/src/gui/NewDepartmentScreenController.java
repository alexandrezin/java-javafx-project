package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class NewDepartmentScreenController implements Initializable{
	
	@FXML
	private TextField departmentNameTextField;
	
	@FXML
	private Button saveButton;
	
	@FXML 
	public void onSaveAction() {
		System.out.println(departmentNameTextField.getText());
		Alerts.showAlert("Data has been saved", "The department \"" + departmentNameTextField.getText() + "\" has been succesfully  saved on database!", AlertType.CONFIRMATION);
		departmentNameTextField.clear();
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
