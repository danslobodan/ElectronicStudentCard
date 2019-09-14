package controllers;

import dataAccessLayer.IRepository;
import models.User;

public class LoginController {
	
	private IRepository<? extends User> students;
	private IRepository<? extends User> professors;

	public LoginController(IRepository<? extends User> students,
			IRepository<? extends User> professors) {
		this.students = students;
		this.professors = professors;
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
		
		System.out.println("Username and password do not match for any active user.");
		return false;
	}
	
	private boolean canLogin(User user, String username, String password) {
		return !user.isDeleted() && 
				user.getUserName().equals(username) &&
				user.getPassword().equals(password);
	}
}
