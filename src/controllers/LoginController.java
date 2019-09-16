package controllers;

import models.Administrator;
import models.Professor;
import models.Student;
import models.User;
import persistence.IRepository;
import utilities.Logger;

public class LoginController implements ILoginController {
	
	private Logger logger = Logger.GetLogger(this);
	
	private IRepository<Student> students;
	private IRepository<Professor> professors;
	private IRepository<Administrator> administrators;
	private ILogin login;

	public LoginController(IRepository<Student> students,
			IRepository<Professor> professors,
			IRepository<Administrator> administrators,
			ILogin login) {
		this.students = students;
		this.professors = professors;
		this.administrators = administrators;
		this.login = login;
	}
	
	public boolean login(String username, String password) {
		
		if (students.exists(user -> canLogin(user, username, password))) {
			
			logger.debug("Logging in student.");
			var student = students
				.get(user -> user.getUserName().equals(username))
				.iterator().next();
			login.login(student);
			return true;
		}
		
		if (professors.exists(user -> canLogin(user, username, password))) {
			
			logger.debug("Logging in professor.");
			var professor = professors
				.get(user -> user.getUserName().equals(username))
				.iterator().next();
			login.login(professor);
			return true;
		}
		
		if (administrators.exists(user -> canLogin(user, username, password))) {
			
			logger.debug("Logging in administrator.");
			var admin = administrators
				.get(user -> user.getUserName().equals(username))
				.iterator().next();
			login.login(admin);
			return true;
		}
		
		logger.debug("Username and password do not match for any active user.");
		return false;
	}
	
	public void logout() {
		login.logout();
	}
	
	private boolean canLogin(User user, String username, String password) {
		return !user.isDeleted() && 
				user.getUserName().equals(username) &&
				user.getPassword().equals(password);
	}
}
