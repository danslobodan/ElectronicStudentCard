package models;

import java.util.Date;

import utilities.StringExtensions;

public class Exam implements IModel<Exam> {

	private int studentCard;
	private int points;
	private int grade;
	private Date date;
	private ExamTerm examTerm;
	private boolean isRevoked;
	private String classId;
	
	public Exam() {
	}
	
	public int getStudentCard() {
		return studentCard;
	}

	public void setStudentCard(int studentCard) {
		this.studentCard = studentCard;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public ExamTerm getExamTerm() {
		return examTerm;
	}

	public void setExamTerm(ExamTerm examTerm) {
		this.examTerm = examTerm;
	}

	public boolean isRevoked() {
		return isRevoked;
	}

	public void setRevoked(boolean isRevoked) {
		this.isRevoked = isRevoked;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Override
	public boolean modelIsValid() {
		return studentCard > 0 &&
			examTerm != null &&
			!StringExtensions.IsNullOrWhitespace(classId);
	}
}
