package controllers;

import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.IRepository;
import models.Administrator;
import models.Professor;
import models.Student;
import models.User;
import utilities.Logger;

public class UsersController {
	
	private Logger logger = Logger.GetLogger(this);

	private IRepository<Student> students;
	private IRepository<Professor> professors;
	private IRepository<Administrator> administrators;
	private ILogin login;
	
	public UsersController(IRepository<Student> students,
			IRepository<Professor> professors,
			IRepository<Administrator> administrators,
			ILogin login) {
		this.students = students;
		this.professors = professors;
		this.administrators = administrators;
		this.login = login;
	}

	public void add(Student student) {
		
		if (canAdd(student) && !students.exists(stud -> stud.getCardId() == student.getCardId())) {
			logger.debug("Adding user %s", student.getUserName());
			students.add(student);
		}
	}
	
	public void update(Student student) {
		
		if ( canUpdate(student)) {
			logger.debug("Updating user %s", student.getUserName());			
			students.update(stud -> findUser(student, stud), student);
		}
	}
	
	public Student getStudent(String username) {
		
		return students.get(stud -> findUser(username, stud)).get(0);
	}
	
	public List<Student> getStudents() {
		
		return new ArrayList<Student>(students.getAll());
	}
	
	public void delete(Student student) {
		
		if (canUpdate(student)) {
			logger.debug("Deleting user %s", student.getUserName());
			student.setDeleted(true);
			students.update(stud -> findUser(student, stud), student);
		}
	}
	
	public void add(Professor professor) {
		
		if (canAdd(professor))
			professors.add(professor);
	}
	
	public void update(Professor professor) {
		
		if (canUpdate(professor)) {
			professors.update(prof -> findUser(professor, prof), professor);
		}
	}
	
	public Professor getProfessor(String username) {
		
		return professors.get(prof -> findUser(username, prof)).get(0);
	}
	
	public List<Professor> getProfessors() {
		
		return new ArrayList<Professor>(professors.getAll());
	}
	
	public void delete(Professor professor) {
		
		if (canUpdate(professor)) {
			professor.setDeleted(true);
			professors.update(prof -> findUser(professor, prof), professor);
		}
	}
	
	public void add(Administrator administrator) {
		
		if (canAdd(administrator))
			administrators.add(administrator);
	}
	
	public void update(Administrator administrator) {
		
		if (canUpdate(administrator) && !login.getUserName().equals(administrator.getUserName())) {
			administrators.update(admin -> findUser(admin, administrator), administrator);
		}
	}
	
	public Administrator getAdmin(String username) {
		
		return administrators.get(admin ->  findUser(username, admin)).get(0);
	}
	
	public List<Administrator> getAdmins() {
		
		return new ArrayList<Administrator>(administrators.getAll());
	}
	
	public void delete(Administrator administrator) {
		
		if (canUpdate(administrator) && !login.getUserName().equals(administrator.getUserName())) {
			administrator.setDeleted(true);
			administrators.update(admin -> findUser(admin, administrator), administrator);
		}
	}
	
	public boolean usernameAvailable(User user) {
		var available =
			!students.exists(student -> findUser(user, student)) &&
			!professors.exists(professor -> findUser(user, professor)) &&
			!administrators.exists(admin -> findUser(user, admin));
		logger.debug("Username %s available: %b", user.getUserName(), available);
		return available;
	}
	
	private boolean hasAccess() {
		return login.getAccessLevel() == AccessLevel.administrator;
	}
	
	private boolean canAdd(User user) {
		return user != null && hasAccess() && usernameAvailable(user);
	}
	
	private boolean canUpdate(User user) {
		return user != null && hasAccess();
	}
	
	private boolean findUser(User userToFind, User user) {
		return userToFind != null && findUser(userToFind.getUserName(), user);
	}
	
	private boolean findUser(String username, User user) {
		return user.getUserName().equals(username);
	}
}
