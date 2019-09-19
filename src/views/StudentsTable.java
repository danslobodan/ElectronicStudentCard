package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Student;

public class StudentsTable extends TableView<Student>{

	public StudentsTable() {

		var firstNameColumn = new TableColumn<Student, String>("First Name");
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		var lastNameColumn = new TableColumn<Student, String>("Last Name");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		var userNameColumn = new TableColumn<Student, String>("Username");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		
		var isDeletedColumn = new TableColumn<Student, Boolean>("Is Deleted");
		isDeletedColumn.setCellValueFactory(new PropertyValueFactory<>("deleted"));
		
		getColumns().add(userNameColumn);
		getColumns().add(firstNameColumn);
		getColumns().add(lastNameColumn);
		getColumns().add(isDeletedColumn);
	}

}
