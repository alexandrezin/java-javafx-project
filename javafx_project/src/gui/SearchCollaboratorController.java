package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.CollaboratorDao;
import model.dao.DaoFactory;
import model.entities.Collaborator;

public class SearchCollaboratorController implements Initializable{
	
	@FXML
	TextField searchCollaboratorTextField;
	
	@FXML
	TableView<Collaborator> collaboratorTableView;
	
	@FXML
	TableColumn<Collaborator, String> nameTableColumn;
	
	@FXML
	TableColumn<Collaborator, String> emailTableColumn;
	
	@FXML
	TableColumn<Collaborator, String> departmentTableColumn;
	
	@FXML
	Button editSelectedButton;
	
	@FXML
	public void onSearchCollaboratorTextFieldChange(){
		
		//Get all Collaborators
		CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
		List<Collaborator> collaboratorList = collaboratorDao.getByParemeter(searchCollaboratorTextField.getText());
		
		//Convert to Observable List
		ObservableList<Collaborator> collaboratorObsList = FXCollections.observableArrayList(collaboratorList);
		collaboratorTableView.setItems(collaboratorObsList);
	}
	
	@FXML
	public void onEditSelectedButtonAction() { 
		Collaborator collaborator = collaboratorTableView.getSelectionModel().getSelectedItem();
		if (collaborator != null) {
			PageActions pageAction = new PageActions();
			pageAction.loadWithObject("EditCollaboratorScreen", collaborator);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Initialize Table
		nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		departmentTableColumn.setCellValueFactory(new PropertyValueFactory<>("Department"));
		
		//Get all Collaborators
		CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
		List<Collaborator> collaboratorList = collaboratorDao.getAll();
		
		//Convert to Observable List
		ObservableList<Collaborator> collaboratorObsList = FXCollections.observableArrayList(collaboratorList);
		collaboratorTableView.setItems(collaboratorObsList);
		
	}
}
