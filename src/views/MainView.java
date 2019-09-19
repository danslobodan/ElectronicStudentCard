package views;

import controllers.ILoggedInUser;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends VBox {
	
	private ILoggedInUser loggedInUser;
	private HBox buttonBox;
	
	private ToggleButton studentsButton;
	private ToggleButton professorsButton;
	private ToggleButton adminsButton;
	private ToggleButton classesButton;
	private ToggleButton examsButton;
	
	private AdminsTable adminsTable;
	private StudentsTable studentsTable;
	private ExamsTable examsTable;
	
	private Button addUserButton; 
	
	public MainView(AdminsTable adminsTable,
			ProfessorsTable professorsTable,
			StudentsTable studentsTable,
			ClassesTable classesTable,
			ExamsTable examsTable,
			ILoggedInUser loggedInUser) {
		
		this.adminsTable = adminsTable;
		this.studentsTable = studentsTable;
		this.examsTable = examsTable;
		
		this.loggedInUser = loggedInUser;
		
		adminsButton = new ToggleButton("Admins");
		adminsButton.setOnAction(action -> setTable(adminsTable));
		
		studentsButton = new ToggleButton("Students");
		studentsButton.setOnAction(action -> setTable(studentsTable));
		
		professorsButton = new ToggleButton("Professors");
		professorsButton.setOnAction(action -> setTable(professorsTable));

		classesButton = new ToggleButton("Classes");
		classesButton.setOnAction(action -> setTable(classesTable));
		
		examsButton = new ToggleButton("Exams");
		examsButton.setOnAction(action -> setTable(examsTable));
		
		addUserButton = new Button("Add user");

		
		var tabs = new ToggleGroup();
		adminsButton.setToggleGroup(tabs);
		studentsButton.setToggleGroup(tabs);
		professorsButton.setToggleGroup(tabs);
		classesButton.setToggleGroup(tabs);
		examsButton.setToggleGroup(tabs);
		
		buttonBox = new HBox();
		
		this.getChildren().add(buttonBox);
		this.setPadding(new Insets(10, 10, 10, 10));
	}
	
	public void setAddUserStage(Stage stage) {
		addUserButton.setOnAction(action -> stage.show());		
	}
	
	public void showView() {
		
		this.getChildren().clear();
		buttonBox.getChildren().clear();
		
		switch(loggedInUser.getAccessLevel()) {
		
		case administrator:
			showAdminView();
			break;
		case professor:
			showProfessorView();
			break;
		case student:
			showStudentView();
		default:
			break;
		}
	}
	
	private void showAdminView() { 
		
		buttonBox.getChildren().addAll(
				adminsButton,
				professorsButton,
				studentsButton,
				classesButton);

		this.getChildren().addAll(buttonBox,
				adminsTable,
				addUserButton);
	}
	
	private void showProfessorView() {
		
		buttonBox.getChildren().addAll(
				studentsButton,
				examsButton);

		this.getChildren().addAll(buttonBox,
				studentsTable,
				addUserButton);
	}
	
	private void showStudentView() {
		
		buttonBox.getChildren().addAll(
				examsButton);
		this.getChildren().addAll(buttonBox,
				examsTable,
				addUserButton);
	}
	
	private void setTable(TableView<?> table) {
		
		if (this.getChildren().size() == 2)
			this.getChildren().remove(1);
		
		this.getChildren().add(table);
	}
}
