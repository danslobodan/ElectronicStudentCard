package controllers;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.IRepository;
import models.Student;
import utilities.Logger;

public class StudentsController {

	private Logger logger = Logger.GetLogger(this);
	
	private IRepository<Student> students;
	private ILoggedInUser login;

	public StudentsController(
			IRepository<Student> students,
			ILoggedInUser login) {
		this.students = students;
		this.login = login;
	}
	
	public List<Student> getStudents(String searchCriteria) {
		
		if (!(login.getAccessLevel() == AccessLevel.professor) && !(login.getAccessLevel() == AccessLevel.administrator)) {
			logger.debug("Access denied. %s cannot search for students.", login.getAccessLevel());
			return new ArrayList<Student>();
		}
		
		if (searchCriteria == null || searchCriteria.length() < 3) {
			logger.debug("Search criteria too short. Minimum three characters required.");
			return new ArrayList<Student>();
		}
		
		var searchLower = searchCriteria.toLowerCase();
		logger.debug("Returning search result for criteria %s.", searchLower);
		return students.get(stud -> stud.getFirstName().toLowerCase().contains(searchLower) ||
				stud.getLastName().toLowerCase().contains(searchLower) ||
				Integer.toString(stud.getCardId()).contains(searchLower));
	}
}
