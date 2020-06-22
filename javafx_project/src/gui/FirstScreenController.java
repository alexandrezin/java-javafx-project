package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class FirstScreenController implements Initializable{
	
	@FXML
	private MenuItem homeScreenMenuItem;
	
	@FXML
	private MenuItem newReportMenuItem;
	
	@FXML
	private MenuItem newCollaboratorMenuItem;
	
	@FXML
	private MenuItem newDepartmentMenuItem;
	
	@FXML
	private MenuItem searchReportMenuItem;
	
	@FXML
	private MenuItem searchCollaboratorMenuItem;
	
	@FXML
	private MenuItem searchDepartmentMenuItem;
	
	@FXML
	public void onHomeScreenMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("HomeScreen");
	}
	
	@FXML
	public void onNewReportMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewReportScreen");
	}
	
	@FXML
	public void onNewCollaboratorMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewCollaboratorScreen");
	}
	
	@FXML
	public void onNewDepartmentMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("NewDepartmentScreen");
	}
	
	@FXML
	public void onSearchReportMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchReportScreen");
	}
	
	@FXML
	public void onSearchCollaboratorMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchCollaboratorScreen");
	}
	
	@FXML
	public void onSearchDepartmentMenuItemAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchDepartmentScreen");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}
}
