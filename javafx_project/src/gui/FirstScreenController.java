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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FirstScreenController implements Initializable{
	
	@FXML
	private MenuItem menuItemNewReport;
	
	@FXML
	private MenuItem menuItemNewColaborator;
	
	@FXML
	private MenuItem menuItemNewDepartment;
	
	@FXML
	public void onMenuItemNewReportAction() {
		System.out.println("onMenuItemNewReportAction");
	}
	
	@FXML
	public void onMenuItemNewColaboratorAction() {
		System.out.println("onMenuItemNewColaboratorAction");
	}
	
	@FXML
	public void onMenuItemNewDepartmentAction() {
		System.out.println("onMenuItemNewDepartmentAction");
		loadView("/gui/NewDepartmentScreen.fxml");
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
			AnchorPane mainAnchorPane = (AnchorPane) ((ScrollPane)mainScene.getRoot()).getContent();
			
			Node mainMenu = mainAnchorPane.getChildren().get(0);
			mainAnchorPane.getChildren().clear();
			mainAnchorPane.getChildren().add(mainMenu);
			mainAnchorPane.getChildren().addAll(newVBox.getChildren());
			
			
		} catch (IOException e) {
			System.out.println("Error loading page" + e.getMessage());
		}
		
	}

	
}
