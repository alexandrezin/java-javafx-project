package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

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
		loadView("/gui/NewReportScreen.fxml");
	}
	
	@FXML
	public void onMenuItemNewCollaboratorAction() {
		loadView("/gui/NewCollaboratorScreen.fxml");
	}
	
	@FXML
	public void onMenuItemNewDepartmentAction() {
		loadView("/gui/NewDepartmentScreen.fxml");
	}
	
	@FXML
	public void onMenuItemSearchReportAction() {
		loadView("/gui/SearchReportScreen.fxml");
	}
	
	@FXML
	public void onMenuItemSearchCollaboratorAction() {
		loadView("/gui/SearchCollaboratorScreen.fxml");
	}
	
	@FXML
	public void onMenuItemSearchDepartmentAction() {
		loadView("/gui/SearchDepartmentScreen.fxml");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private synchronized void loadView (String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading page" + e.getMessage() + e.getCause());
		}
		
	}

	
}
