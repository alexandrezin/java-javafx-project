package gui;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.dao.ColaboratorDao;
import model.dao.DaoFactory;
import model.dao.ReportDao;
import model.entities.Colaborator;
import model.entities.Report;

public class NewReportController implements Initializable{
	
	@FXML
	TextField reportTitleTextField;
	
	@FXML
	ComboBox<Colaborator> colaboratorComboBox;
	
	@FXML
	DatePicker reportDatePicker;
	
	@FXML
	TextArea reportDescriptionTextArea;
	
	@FXML
	Button saveButton;
	
	@FXML
	public void onSaveButtonAction() {
		try {
			ReportDao reportDao = DaoFactory.createReportDao();
			
			Report report = new Report();
			report.setTitle(reportTitleTextField.getText());
			report.setColaborator(colaboratorComboBox.getValue());
			SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
			report.setDate(date.parse(reportDatePicker.getValue().toString()));
			report.setDescription(reportDescriptionTextArea.getText());
			reportDao.insert(report);
			Alerts.showAlert("Data has been saved", "The report \"" + report.getTitle() + "\" has been succesfully  saved on database!", AlertType.INFORMATION);
		}
		
		catch (DbException e){
			Alerts.showAlert("Data has NOT been saved", "The report couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
		
		catch (ParseException e) {
			Alerts.showAlert("Data has NOT been saved", "The department couldn't been saved! \nError: " + e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ColaboratorDao colaboratorDao = DaoFactory.createColaboratorDao();
		colaboratorComboBox.getItems().addAll(colaboratorDao.getAll());	
	}

}
