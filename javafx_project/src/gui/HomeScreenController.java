package gui;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.PageActions;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.ReportDao;
import model.entities.Department;
import model.entities.Report;

public class HomeScreenController implements Initializable{

	@FXML
	private TableView<Report> todaysReportTableView;
	
	@FXML
	private TableColumn<Report, String> reportTitleTableColumn;
	
	@FXML
	private TableColumn<Report, String> collaboratorNameTableColumn;
	
	@FXML
	private PieChart pieChart;
	
	@FXML
	private Button viewSelectedButton;
	
	@FXML
	public void onViewSelectedButtonAction() {
		
		Report report = todaysReportTableView.getSelectionModel().getSelectedItem();
		
		if (report != null) {
			PageActions pageAction = new PageActions();
			pageAction.loadWithObject("EditReportScreen", "HomeScreen", report);
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		List<Department> departmentList = departmentDao.getAll();
		

		for (int x = 0; x < departmentList.size(); x++) {
			
			ReportDao reportDao = DaoFactory.createReportDao();
			List<Report> reportList = reportDao.getByDepartment(departmentList.get(x));
			
			pieChartData.add(new PieChart.Data(departmentList.get(x).getName(), reportList.size()));
		}
		
		ReportDao reportDao = DaoFactory.createReportDao();
		List<Report> reportList = reportDao.getAll();
		
		int totalNumberOfReports = reportList.size();
		
		pieChartData.forEach(data -> data.nameProperty().bind(Bindings.concat(data.getName(), " ", String.format("%.2f", ((data.getPieValue() / totalNumberOfReports)*100)), "%")));

		pieChart.setData(pieChartData);
		pieChart.setTitle("Reports by Department");
		
		reportTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		collaboratorNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Collaborator"));
		
		//Get all today's Report
		List<Report> todaysReportList = reportDao.getByDate(new Date());
		
		//Convert to Observable List
		ObservableList<Report> todaysReportObsList = FXCollections.observableArrayList(todaysReportList);
		todaysReportTableView.setItems(todaysReportObsList);
	}
	

}
