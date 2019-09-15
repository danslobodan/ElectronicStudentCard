package controllers;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.IRepository;
import models.Exam;
import models.ExamTerm;
import models.Professor;
import models.Student;
import models.StudentCard;
import models.Title;
import models.UniClass;
import utilities.Logger;

public class ExamsController {
	
	private Logger logger = Logger.GetLogger(this);
	
	private IRepository<StudentCard> studentCards;
	private IRepository<Student> students;
	private IRepository<Professor> professors;
	private IRepository<UniClass> classes;
	private IRepository<Exam> exams;
	private ILoggedInUser login;

	public ExamsController(
			IRepository<StudentCard> studentCards,
			IRepository<Student> students,
			IRepository<Professor> professors,
			IRepository<UniClass> classes, 
			IRepository<Exam> exams, 
			ILoggedInUser login) {
		this.studentCards = studentCards;
		this.students = students;
		this.professors = professors;
		this.classes = classes;
		this.exams = exams;
		this.login = login;
	}

	public void addExam(UniClass uniClass, ExamTerm examTerm) {
		
		logger.debug("Adding exam.");
		
		if (!(login.getAccessLevel() == AccessLevel.student)) {
			logger.debug("Access denied.");
			return;
		}
		
		if (uniClass == null || !uniClass.modelIsValid() ||
			!classes.exists(cls -> cls.getId().equals(uniClass.getId()))) {
			logger.debug("Class does not exist.");
			return;
		}
		
		if (examTerm == null) {
			logger.debug("Exam term not set.");
			return;
		}
		
		var student = students.get(stud -> stud.getUserName().equals(login.getUserName())).get(0);
		var studentCard = studentCards.get(card -> card.getCardId() == student.getCardId()).get(0);
		
		if (!(uniClass.getCurriculumYear() <= studentCard.getCurriculumYear())) {
			logger.debug("User not eligible for this class.");
			return;
		}
		
		var exam = new Exam();
		exam.setClassId(uniClass.getId());
		exam.setStudentCard(studentCard.getCardId());
		exam.setExamTerm(examTerm);
		
		
		if (exams.exists(ex -> ex.getClassId().equals(exam.getClassId()) && 
			ex.getExamTerm() == exam.getExamTerm())) {
			logger.debug("Exam already added in %s term.", examTerm);
			return;
		}
		
		logger.debug("Student added an exam.");
		exams.add(exam);
	}
	
	public void revokeExam(Exam exam) {
				
		exam.setRevoked(true);
		exams.update(ex -> 
			ex.getClassId().equals(exam.getClassId()) 
			&& ex.getExamTerm() == exam.getExamTerm(), exam);
	}
	
	public List<Exam> getStudentExams() {
		
		if (login.getAccessLevel() == AccessLevel.student) {
			var student = students.get(stud -> stud.getUserName().equals(login.getUserName())).get(0);			
			return new ArrayList<Exam>(exams.get(ex -> ex.getStudentCard() == (student.getCardId())));
		}
		
		return new ArrayList<Exam>();
	}
	
	public List<Exam> getExamByExamTerm(ExamTerm examTerm) {
		
		if (examTerm != null && login.getAccessLevel() == AccessLevel.professor) {
			logger.debug("Getting exams for exam term %s.", examTerm);
			return new ArrayList<Exam>(exams.get(ex -> ex.getExamTerm() == examTerm));
		}
		
		return new ArrayList<Exam>();
	}
	
	public void enterGrade(Exam exam) {
		
		if (exam == null) {
			logger.debug("Cannot enter grade - exam is null.");
			return;
		}
		
		if (!(login.getAccessLevel() == AccessLevel.professor)) {
			logger.debug("Cannot enter grade. Access Denied.");
			return;
		}
		
		var professor = professors.get(prof -> prof.getUserName().equals(login.getUserName())).get(0);
		var title = professor.getTitle();
		
		if (title != Title.AssistantProfessor && title != Title.VisitingProfessor && title != Title.FullProfessor) {
			logger.debug("%s cannot enter grades.", title);
			return;
		}
		
		exams.update(ex -> ex.getClassId().equals(exam.getClassId()) && ex.getExamTerm() == exam.getExamTerm(), exam);
	}
}
