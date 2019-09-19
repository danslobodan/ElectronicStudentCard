package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Professor;

public class ProfessorsTable extends TableView<Professor> {

	public ProfessorsTable() {

		var firstNameColumn = new TableColumn<Professor, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		var lastNameColumn = new TableColumn<Professor, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		var userNameColumn = new TableColumn<Professor, String>("Username");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		var isDeletedColumn = new TableColumn<Professor, Boolean>("Is Deleted");
		isDeletedColumn.setCellValueFactory(new PropertyValueFactory<>("deleted"));
		
		getColumns().add(userNameColumn);
		getColumns().add(firstNameColumn);
		getColumns().add(lastNameColumn);
		getColumns().add(isDeletedColumn);
	}

}
