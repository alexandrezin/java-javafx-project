package gui.util;

import java.io.IOException;

import application.Main;
import gui.EditDepartmentController;
import gui.NewReportController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.entities.Department;

public class PageActions {
	
	public synchronized void load (String absoluteName) {
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
	
	public synchronized void loadWithObject (String targetController, Object obj) {
		try {
			//EditDepartmentScreen
			if(targetController.equals("EditDepartmentController")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/EditDepartmentScreen.fxml"));
				VBox newVBox = loader.load();
				
				Scene mainScene = Main.getMainScene();
				VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
				
				Node mainMenu = mainVBox.getChildren().get(0);
				mainVBox.getChildren().clear();
				mainVBox.getChildren().add(mainMenu);
				mainVBox.getChildren().addAll(newVBox.getChildren());
				
				EditDepartmentController controller = loader.getController();
				controller.setDepartment((Department)obj);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading page" + e.getMessage() + e.getCause());
		}
		
	}
}
