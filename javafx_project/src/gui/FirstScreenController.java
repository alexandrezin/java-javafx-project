package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class FirstScreenController implements Initializable{
	
	@FXML
	private MenuItem menuItemNewReport;
	
	@FXML
	private MenuItem menuItemNewCollaborator;
	
	@FXML
	private MenuItem menuItemNewDepartment;
	
	@FXML
	private MenuItem menuItemSearchReport;
	
	@FXML
	private MenuItem menuItemSearchCollaborator;
	
	@FXML
	private MenuItem menuItemSearchDepartment;
	
	@FXML
	public void onMenuItemNewReportAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewReportScreen");
	}
	
	@FXML
	public void onMenuItemNewCollaboratorAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewCollaboratorScreen");
	}
	
	@FXML
	public void onMenuItemNewDepartmentAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewDepartmentScreen");
	}
	
	@FXML
	public void onMenuItemSearchReportAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchReportScreen");
	}
	
	@FXML
	public void onMenuItemSearchCollaboratorAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchCollaboratorScreen");
	}
	
	@FXML
	public void onMenuItemSearchDepartmentAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchDepartmentScreen");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}
}
