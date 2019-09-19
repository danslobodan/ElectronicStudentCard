package views;

import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
	
	public MainView(AdminsTable adminsTable,
			ProfessorsTable professorsTable,
			StudentsTable studentsTable) {
		
		var adminsButton = new ToggleButton("Admins");
		adminsButton.setOnAction(action -> setTable(adminsTable));
		
		var studentsButton = new ToggleButton("Students");
		studentsButton.setOnAction(action -> setTable(studentsTable));
		
		var professorsButton = new ToggleButton("Professors");
		professorsButton.setOnAction(action -> setTable(professorsTable));

		var classesButton = new ToggleButton("Classes");
		
		var tabs = new ToggleGroup();
		adminsButton.setToggleGroup(tabs);
		studentsButton.setToggleGroup(tabs);
		professorsButton.setToggleGroup(tabs);
		classesButton.setToggleGroup(tabs);
		
		var hbox = new HBox(adminsButton, studentsButton, professorsButton, classesButton);
		
		this.getChildren().add(hbox);
		this.setPadding(new Insets(10, 10, 10, 10));
	}
	
	private void setTable(TableView<?> table) {
		
		if (this.getChildren().size() == 2)
			this.getChildren().remove(1);
		
		this.getChildren().add(table);
	}
}
