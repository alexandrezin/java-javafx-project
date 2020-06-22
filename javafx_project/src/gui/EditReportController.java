package gui;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.PageActions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.dao.CollaboratorDao;
import model.dao.DaoFactory;
import model.dao.ReportDao;
import model.entities.Collaborator;
import model.entities.Report;

public class EditReportController implements Initializable{
	
	private Report report;
	
	public void setReport(Report report) {
		
		this.report = report;
		
		reportTitleTextField.setText(report.getTitle());
		reportDatePicker.setValue(new java.sql.Date(report.getDate().getTime()).toLocalDate());
		reportDescriptionTextArea.setText(report.getDescription());
		
		CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
		collaboratorComboBox.getItems().addAll(collaboratorDao.getAll());
		
		collaboratorComboBox.setValue(report.getCollaborator());
	}
	
	@FXML
	TextField reportTitleTextField;
	
	@FXML
	ComboBox<Collaborator> collaboratorComboBox;
	
	@FXML
	DatePicker reportDatePicker;
	
	@FXML
	TextArea reportDescriptionTextArea;
	
	@FXML
	Button updateButton;
	
	@FXML 
	Button cancelButton;
	
	@FXML
	public void onCancelButtonAction() {
		PageActions pageAction = new PageActions();
		pageAction.load("SearchReportScreen");
	}
	
	@FXML
	public void onUpdateButtonAction() {
		
		try {
			report.setTitle(reportTitleTextField.getText());
			report.setColaborator(collaboratorComboBox.getValue());
			report.setDate(Date.from(reportDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())); 
			report.setDescription(reportDescriptionTextArea.getText());
			
			ReportDao reportDao = DaoFactory.createReportDao();
			reportDao.update(report);
			
			//Done Alert
			Alerts.showAlert("Data has been saved", "The report \"" + report.getTitle() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			
			//Return to Main Screen
			PageActions pageAction = new PageActions();
			pageAction.load("SearchReportScreen");
		}
		catch(DbException e) {
			Alerts.showAlert("Data has NOT been saved", "The report couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
	}

}
