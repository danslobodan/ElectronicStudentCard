package controllers;

import models.Administrator;
import models.Professor;
import models.Student;

public class LoggedInUser implements ILogin {

	private static final String anonymous = "Anonymous";
	private String displayName;
	private String userName;
	private AccessLevel accessLevel;
	
	public LoggedInUser() {
		logout();
	}

	public String getDisplayName() {
		System.out.println(String.format("Currently logged in: %s", displayName));
		return displayName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public boolean isLoggedIn() {
		return this.displayName != anonymous;
	}

	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void login(Student student) {
		this.displayName = String.format("%s %s", 
			student.getFirstName(), student.getLastName());
		this.userName = student.getUserName();
		this.accessLevel = AccessLevel.student;
	}
	
	public void login(Professor professor) {
		this.displayName = String.format("%s %s %s", 
			professor.getTitle(), professor.getFirstName(), professor.getLastName());
		this.userName = professor.getUserName();
		this.accessLevel = AccessLevel.professor;
	}
	
	public void login(Administrator administrator) {
		this.displayName = String.format("%s %s", 
			administrator.getFirstName(), administrator.getLastName());
		this.userName = administrator.getUserName();
		this.accessLevel = AccessLevel.administrator;
	}
	
	public void logout() {
		this.userName = "";
		this.displayName = anonymous;
		this.accessLevel = AccessLevel.noAccess;
	}
}
