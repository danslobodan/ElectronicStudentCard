package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Administrator;

public class AdminsTable extends TableView<Administrator> {

	public AdminsTable() {
		
		var firstNameColumn = new TableColumn<Administrator, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		var lastNameColumn = new TableColumn<Administrator, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		var userNameColumn = new TableColumn<Administrator, String>("Username");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		var isDeletedColumn = new TableColumn<Administrator, Boolean>("Is Deleted");
		isDeletedColumn.setCellValueFactory(new PropertyValueFactory<>("deleted"));
		
		getColumns().add(userNameColumn);
		getColumns().add(firstNameColumn);
		getColumns().add(lastNameColumn);
		getColumns().add(isDeletedColumn);
	}
}
