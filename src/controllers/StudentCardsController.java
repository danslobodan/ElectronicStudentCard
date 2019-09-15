package controllers;

import dataAccessLayer.IRepository;
import models.Exam;
import models.Professor;
import models.Student;
import models.StudentCard;
import models.UniClass;
import utilities.Logger;

public class StudentCardsController {
	
	private Logger logger = Logger.GetLogger(this);
	
	private IRepository<StudentCard> studentCards;
	private IRepository<Student> students;
	private IRepository<Exam> exams;
	private ILoggedInUser login;

	public StudentCardsController() {
		// TODO Auto-generated constructor stub
	}
	
	public StudentCardsController(
			IRepository<StudentCard> studentCards,
			IRepository<Student> students,
			IRepository<Exam> exams, 
			ILoggedInUser login) {
		this.studentCards = studentCards;
		this.students = students;
		this.exams = exams;
		this.login = login;
	}

	public StudentCard getStudentCard() {
		
		if (login.getAccessLevel() != AccessLevel.student) {
			logger.debug("Get student card aplicable only for students.");
			return null;
		}
		
		var student = students.get(stud -> stud.getUserName().equals(login.getUserName())).get(0);
		var studentCard = studentCards.get(card -> card.getCardId() == student.getCardId()).get(0);
		var studentExams = exams.get(ex -> ex.getStudentCard() == student.getCardId());
		studentCard.setExams(studentExams);
		
		return studentCard;
	}
}
