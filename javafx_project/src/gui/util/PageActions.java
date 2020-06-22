package gui.util;

import java.io.IOException;

import application.Main;
import gui.EditCollaboratorController;
import gui.EditDepartmentController;
import gui.EditReportController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.entities.Collaborator;
import model.entities.Department;
import model.entities.Report;

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
	
	public synchronized void loadWithObject (String targetPageName, String currentPageName, Object obj) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/" + targetPageName + ".fxml"));
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
			if(targetPageName.equals("EditDepartmentScreen")) {
				EditDepartmentController controller = loader.getController();
				controller.setDepartment((Department)obj);
				controller.setPreviousPage(currentPageName);
			}
			
			//EditDepartmentScreen
			if(targetPageName.equals("EditCollaboratorScreen")) {
				EditCollaboratorController controller = loader.getController();
				controller.setCollaborator((Collaborator)obj);
				controller.setPreviousPage(currentPageName);
			}
			
			//EditDepartmentScreen
			if(targetPageName.equals("EditReportScreen")) {
				EditReportController controller = loader.getController();
				controller.setReport((Report)obj);
				controller.setPreviousPage(currentPageName);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading page" + e.getMessage() + e.getCause());
		}
		
	}
}
