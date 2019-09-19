package main;

import controllers.*;
import models.*;
import persistence.*;
import views.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Program extends Application {

	public Program() {		
	}
	
	public void start(Stage primaryStage) {

		var studentsTable = new StudentsTable();
		var students = new StudentRepository(studentsTable.getItems());

		var professorsTable = new ProfessorsTable();
		var professors = new ProfessorRepository(professorsTable.getItems());
		
		var adminsTable = new AdminsTable();
		var administrators = new AdministratorRepository(adminsTable.getItems());
		
		var classesTable = new ClassesTable();
		var classes = new ClassesRepository(classesTable.getItems());
		
		var examsTable = new ExamsTable();
		var exams = new ExamsRepository(examsTable.getItems());
		
		var loggedInUser = new LoggedInUser();
		var loginController = new LoginController(students, professors, administrators, loggedInUser);		
		
		var loginStage = new LoginView(primaryStage, loginController);
		loginStage.show();
		
		var mainView = new MainView(adminsTable, professorsTable, studentsTable, classesTable, examsTable, loggedInUser);
		var mainScene = new Scene(mainView, 400, 300);
		mainView.showView();
		
		primaryStage.setOnShown(actionEvent -> {
			primaryStage.setTitle(
					String.format("Electronic Index. User: %s", loggedInUser.getDisplayName()));
			mainView.showView();
		});
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Electronic Student Card");
		primaryStage.getIcons().add(new Image("file:resources/icon.png"));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
