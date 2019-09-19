package views;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.UniClass;

public class ClassesTable extends TableView<UniClass> {

	public ClassesTable() {
		
		var id = new TableColumn<UniClass, String>("Id");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		var name = new TableColumn<UniClass, String>("Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		var calYear = new TableColumn<UniClass, Integer>("Calendar Year");
		calYear.setCellValueFactory(new PropertyValueFactory<>("calendarYear"));
		
		var curYear = new TableColumn<UniClass, Integer>("Curriculum Year");
		curYear.setCellValueFactory(new PropertyValueFactory<>("curriculumYear"));
		
		getColumns().add(id);
		getColumns().add(name);
		getColumns().add(calYear);
		getColumns().add(curYear);
	}

}
