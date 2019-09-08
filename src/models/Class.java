package models;

import java.time.Year;

public class Class {
	
	private int id;
	private String name;
	private String syllabus;
	private Year calendarYear;
	private int curriculumYear;
	private int numberOfTheoryClasses;
	private int numberOfPracticeClasses;

	public Class() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyllabus() {
		return syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public Year getCalendarYear() {
		return calendarYear;
	}

	public void setCalendarYear(Year calendarYear) {
		this.calendarYear = calendarYear;
	}

	public int getCurriculumYear() {
		return curriculumYear;
	}

	public void setCurriculumYear(int curriculumYear) {
		this.curriculumYear = curriculumYear;
	}

	public int getNumberOfTheoryClasses() {
		return numberOfTheoryClasses;
	}

	public void setNumberOfTheoryClasses(int number) {
		this.numberOfTheoryClasses = number;
	}

	public int getNumberOfPracticeClasses() {
		return numberOfPracticeClasses;
	}

	public void setNumberOfPracticeClasses(int number) {
		this.numberOfPracticeClasses = number;
	}

}
