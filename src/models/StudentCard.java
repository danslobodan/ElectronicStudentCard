package models;

import java.time.Year;
import java.util.ArrayList;

public class StudentCard {

	private int cardId;
	private String faculty;
	private String university;
	private String curriculum;
	private Year enrollmentYear;
	private int curriculumYear;
	private ArrayList<Exam> exams;
	private Student student;
	
	public StudentCard() {
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(String curriculum) {
		this.curriculum = curriculum;
	}

	public Year getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(Year enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}

	public int getCurriculumYear() {
		return curriculumYear;
	}

	public void setCurriculumYear(int curriculumYear) {
		this.curriculumYear = curriculumYear;
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
}
