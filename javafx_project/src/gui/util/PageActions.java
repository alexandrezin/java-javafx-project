package gui.util;

import java.io.IOException;

import application.Main;
import gui.EditCollaboratorController;
import gui.EditDepartmentController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.entities.Collaborator;
import model.entities.Department;

public class PageActions {
	
	public synchronized void load (String pageName) {
		try {			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + pageName + ".fxml"));
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
	
	public synchronized void loadWithObject (String pageName, Object obj) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + pageName + ".fxml"));
			VBox newVBox = loader.load();
			//
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
			//
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
				
			//EditDepartmentScreen
			if(pageName.equals("EditDepartmentScreen")) {
				EditDepartmentController controller = loader.getController();
				controller.setDepartment((Department)obj);
			}
			
			//EditDepartmentScreen
			if(pageName.equals("EditCollaboratorScreen")) {
				EditCollaboratorController controller = loader.getController();
				controller.setCollaborator((Collaborator)obj);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading page" + e.getMessage() + e.getCause());
		}
		
	}
}
