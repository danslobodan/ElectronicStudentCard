package controllers;

import dataAccessLayer.IRepository;
import models.Administrator;
import models.Professor;
import models.Student;
import models.User;

public class LoginController {
	
	private IRepository<Student> students;
	private IRepository<Professor> professors;
	private IRepository<Administrator> administrators;

	public LoginController(IRepository<Student> students,
			IRepository<Professor> professors,
			IRepository<Administrator> administrators) {
		this.students = students;
		this.professors = professors;
		this.administrators = administrators;
	}
	
	public boolean login(String username, String password) {
		
		if (students.exists(user -> canLogin(user, username, password))) {
			
			System.out.println("Logging in student.");
			return true;
		}
		
		if (professors.exists(user -> canLogin(user, username, password))) {
			
			System.out.println("Logging in professor.");
			return true;
		}
		
		if (administrators.exists(user -> canLogin(user, username, password))) {
			
			System.out.println("Logging in administrator.");
			return true;
		}
		
		System.out.println("Username and password do not match for any active user.");
		return false;
	}
	
	private boolean canLogin(User user, String username, String password) {
		return !user.isDeleted() && 
				user.getUserName().equals(username) &&
				user.getPassword().equals(password);
	}
}
