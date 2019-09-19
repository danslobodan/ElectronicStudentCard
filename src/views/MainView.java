package views;

import controllers.ILoggedInUser;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	
	
	public void showView() {
		
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
		setTable(adminsTable);
	}
	
	private void showProfessorView() {
		
		buttonBox.getChildren().addAll(
				studentsButton,
				examsButton);
		setTable(studentsTable);
	}
	
	private void showStudentView() {
		
		buttonBox.getChildren().addAll(
				examsButton);
		setTable(examsTable);
	}
	
	private void setTable(TableView<?> table) {
		
		if (this.getChildren().size() == 2)
			this.getChildren().remove(1);
		
		this.getChildren().add(table);
	}
}
