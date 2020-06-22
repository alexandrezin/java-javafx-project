package gui;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.DaoFactory;
import model.dao.ReportDao;
import model.entities.Report;

public class SearchReportController implements Initializable{
	
	@FXML
	TextField searchReportTextField;
	
	@FXML
	DatePicker reportDatePicker;
	
	@FXML
	Button removeDatePickerSelection;
	
	@FXML
	TableView<Report> reportTableView;
	
	@FXML
	TableColumn<Report, String> titleTableColumn;
	
	@FXML
	TableColumn<Report, String> dateTableColumn;
	
	@FXML
	TableColumn<Report, String> collaboratorTableColumn;
	
	@FXML
	Button editSelectedButton;
	
	@FXML
	public void onSearchReportTextFieldChange() {	
		
		System.out.println(reportDatePicker.getValue());
		
		//Get all Reports
		ReportDao reportDao = DaoFactory.createReportDao();
		
		Date date = null;
		if(reportDatePicker.getValue() != null) date = Date.from(reportDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Report> reportList = reportDao.getByParemeter(searchReportTextField.getText(), date);

		//Convert to Observable List
		ObservableList<Report> reportObsList = FXCollections.observableArrayList(reportList);
		reportTableView.setItems(reportObsList);

	}
	
	@FXML
	public void onRemoveDatePickerSelectionAction() {
		reportDatePicker.setValue(null);
	}
	
	@FXML
	public void onEditSelectedButtonAction() {
		Report report = reportTableView.getSelectionModel().getSelectedItem();
		if (report != null) {
			PageActions pageAction = new PageActions();
			pageAction.loadWithObject("EditReportScreen", report);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		collaboratorTableColumn.setCellValueFactory(new PropertyValueFactory<>("Collaborator"));
		
		//Get all Reports
		ReportDao reportDao = DaoFactory.createReportDao();
		List<Report> reportList = reportDao.getAll();
		
		//Convert to Observable List
		ObservableList<Report> reportObsList = FXCollections.observableArrayList(reportList);
		reportTableView.setItems(reportObsList);
		
	}
	
	
}
