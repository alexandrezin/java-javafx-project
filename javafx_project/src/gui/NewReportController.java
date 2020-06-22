package gui;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.dao.CollaboratorDao;
import model.dao.DaoFactory;
import model.dao.ReportDao;
import model.entities.Collaborator;
import model.entities.Report;

public class NewReportController implements Initializable{
	
	@FXML
	private TextField reportTitleTextField;
	
	@FXML
	private ComboBox<Collaborator> collaboratorComboBox;
	
	@FXML
	private DatePicker reportDatePicker;
	
	@FXML
	private TextArea reportDescriptionTextArea;
	
	@FXML
	private Button saveButton;
	
	@FXML
	public void onSaveButtonAction() {
		try {
			ReportDao reportDao = DaoFactory.createReportDao();
			
			Report report = new Report();
			report.setTitle(reportTitleTextField.getText());
			report.setColaborator(collaboratorComboBox.getValue());
			
			report.setDate(Date.from(reportDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant())); 
			
			report.setDescription(reportDescriptionTextArea.getText());
			reportDao.insert(report);
			Alerts.showAlert("Data has been saved", "The report \"" + report.getTitle() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
			
			reportTitleTextField.clear();
			collaboratorComboBox.setValue(null);
			reportDatePicker.setValue(null);
			reportDescriptionTextArea.clear();
		}
		
		catch (DbException e){
			Alerts.showAlert("Data has NOT been saved", "The report couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CollaboratorDao collaboratorDao = DaoFactory.createCollaboratorDao();
		collaboratorComboBox.getItems().addAll(collaboratorDao.getAll());
	}

}
