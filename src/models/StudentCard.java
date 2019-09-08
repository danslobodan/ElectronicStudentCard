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

	
}
