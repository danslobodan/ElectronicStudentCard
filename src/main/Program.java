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

		// var classes = new Repository<UniClass>(UniClass.class);
		// var studentCards = new Repository<StudentCard>(StudentCard.class);
		// var exams = new Repository<Exam>(Exam.class);
		
		var loggedInUser = new LoggedInUser();
		
		primaryStage.setOnShown(actionEvent -> primaryStage.setTitle(
				String.format("Electronic Index. User: %s", loggedInUser.getDisplayName())));		
		
		var loginController = new LoginController(students, professors, administrators, loggedInUser);
		// var usersController = new UsersController(students, professors, administrators, studentCards, loggedInUser);
		// var uniClassesController = new UniClassesController(classes, loggedInUser);
		// var examsController = new ExamsController(studentCards, students, professors, classes, exams, loggedInUser);
		// var studentsController = new StudentsController(students, loggedInUser);
		// var studentCardsController = new StudentCardsController(studentCards, students, exams, loggedInUser);
		
		
		var adminView = new MainView(adminsTable, professorsTable, studentsTable);
		var adminScene = new Scene(adminView, 400, 300);
		primaryStage.setScene(adminScene);
		primaryStage.show();
		
		var loginStage = new LoginView(primaryStage, loginController);
		// loginStage.show();
		
		primaryStage.setTitle("Electronic Student Card");
		primaryStage.getIcons().add(new Image("file:resources/icon.png"));
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static Student getStudent() {
		
		Student student = new Student();
		student.setUserName("s.dan");
		student.setPassword("s.dan");
		student.setFirstName("Slobodan");
		student.setLastName("Dan");
		student.setCardId(2018271337);
		student.setEmail("slobodan.dan.18@singimail.rs");
		return student;
	}
	
	private static StudentCard getStudentCard() {
		
		var studentCard = new StudentCard();
		studentCard.setCurriculum("SII");
		studentCard.setCardId(2018271337);
		studentCard.setCurriculumYear(2);
		studentCard.setEnrollmentYear(2018);
		studentCard.setFaculty("Technical Sciences");
		studentCard.setUniversity("Singidunum");
		return studentCard;
	}
	
	private static Professor getProfessor() {
		
		var professor = new Professor();
		professor.setUserName("a.mitrovic");
		professor.setPassword("a.mitrovic");
		professor.setFirstName("Aleksandra");
		professor.setLastName("Mitrovic");
		professor.setTitle(Title.AssistantProfessor);
		professor.setEmail("a.mitrovic@singimail.rs");
		return professor;
	}
	
	private static Administrator getAdministrator() {
		
		var administrator = new Administrator();
		administrator.setUserName("admin");
		administrator.setPassword("admin");
		administrator.setFirstName("Admin");
		administrator.setLastName("Adminovic");
		return administrator;
	}
	
	private static UniClass getUniClass() {
		
		var cls = new UniClass();		
		cls.setName("Objektno Orijentisano Programiranje 1");
		cls.setNumberOfPracticeClasses(10);
		cls.setNumberOfTheoryClasses(10);
		cls.setSyllabus("SII");
		cls.setCalendarYear(2019);
		cls.setCurriculumYear(1);
		
		return cls;
	}
}
