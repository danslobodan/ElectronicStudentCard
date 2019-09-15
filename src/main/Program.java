package main;

import java.time.Year;
import java.util.Date;

import controllers.ExamsController;
import controllers.LoggedInUser;
import controllers.LoginController;
import controllers.StudentCardsController;
import controllers.StudentsController;
import controllers.UniClassesController;
import controllers.UsersController;
import dataAccessLayer.Repository;
import models.Administrator;
import models.Exam;
import models.ExamTerm;
import models.Professor;
import models.Student;
import models.StudentCard;
import models.Title;
import models.UniClass;
import utilities.Logger;

public class Program {

	public Program() {		
	}

	public static void main(String[] args) {

		var students = new Repository<Student>(Student.class);
		var professors = new Repository<Professor>(Professor.class);
		var administrators = new Repository<Administrator>(Administrator.class);
		var classes = new Repository<UniClass>(UniClass.class);
		var studentCards = new Repository<StudentCard>(StudentCard.class);
		var exams = new Repository<Exam>(Exam.class);
		
		var loggedInUser = new LoggedInUser();
		
		var loginController = new LoginController(students, professors, administrators, loggedInUser);
		var usersController = new UsersController(students, professors, administrators, studentCards, loggedInUser);
		var uniClassesController = new UniClassesController(classes, loggedInUser);
		var examsController = new ExamsController(studentCards, students, professors, classes, exams, loggedInUser);
		var studentsController = new StudentsController(students, loggedInUser);
		var studentCardsController = new StudentCardsController(studentCards, students, exams, loggedInUser);

		loginController.login("s.dan", "s.dan");
		
		var myCard = studentCardsController.getStudentCard();
		for(var ex : myCard.getExams()) {
			System.out.println(ex.getClassId());
		}
	}

	private static Student getStudent() {
		
		Student student = new Student();
		student.setUserName("s.dan");
		student.setPassword("volimsokodkruske");
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
		professor.setPassword("alganigdenema");
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
