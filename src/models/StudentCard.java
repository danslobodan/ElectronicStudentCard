package models;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import utilities.StringExtensions;

@JsonIgnoreProperties(value = { "exams" })
public class StudentCard implements IModel<StudentCard> {

	private int cardId;
	private String faculty;
	private String university;
	private String curriculum;
	private int enrollmentYear;
	private int curriculumYear;
	private ArrayList<Exam> exams;
	
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

	public int getEnrollmentYear() {
		return enrollmentYear;
	}

	public void setEnrollmentYear(int enrollmentYear) {
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

	@Override
	public boolean modelIsValid() {
		return cardId > 0 &&
			!StringExtensions.IsNullOrWhitespace(faculty) &&
			!StringExtensions.IsNullOrWhitespace(university) &&
			!StringExtensions.IsNullOrWhitespace(curriculum) &&
			!StringExtensions.IsNullOrWhitespace(faculty) &&
			enrollmentYear > 1900 && enrollmentYear < 2100 &&
			curriculumYear > 0 && curriculumYear <= 5;
	}
}
