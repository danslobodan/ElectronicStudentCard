package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Administrator;
import models.Professor;
import models.Student;
import models.StudentCard;
import models.User;
import persistence.IRepository;
import utilities.Logger;

public class UsersController implements IUsersController {
	
	private Logger logger = Logger.GetLogger(this);

	private IRepository<Student> students;
	private IRepository<Professor> professors;
	private IRepository<Administrator> administrators;
	private IRepository<StudentCard> studentCards;
	private ILogin login;
	
	public UsersController(
			IRepository<Student> students,
			IRepository<Professor> professors,
			IRepository<Administrator> administrators,
			ILogin login) {
		this.students = students;
		this.professors = professors;
		this.administrators = administrators;
		// this.studentCards = studentCards;
		this.login = login;
	}

	public boolean add(Student student, StudentCard studentCard) {
		
		if (canAdd(student) &&
			studentCard != null && studentCard.modelIsValid() &&
			!studentCards.exists(card -> card.getCardId() == studentCard.getCardId())) {
			
			logger.debug("Adding student user %s", student.getUserName());
			student.setCardId(studentCard.getCardId());
			
			studentCards.add(studentCard);
			return students.add(student);
		}
		
		return false;
	}
	
	public boolean update(Student student) {
		
		if (canUpdate(student)) {
			logger.debug("Updating student user %s", student.getUserName());			
			return students.update(stud -> findUser(student, stud), student);
		}
		
		return false;
	}
	
	public Student getStudent(String username) {
		
		return students.get(stud -> findUser(username, stud)).get(0);
	}
	
	public List<Student> getStudents() {
		
		return new ArrayList<Student>(students.getAll());
	}
	
	public void delete(Student student) {
		
		if (canUpdate(student)) {
			logger.debug("Deleting student user %s", student.getUserName());
			student.setDeleted(true);
			students.update(stud -> findUser(student, stud), student);
		}
	}
	
	public void add(Professor professor) {
		
		if (canAdd(professor)) {
			logger.debug("Adding professor user %s", professor.getUserName());
			professors.add(professor);
		}
	}
	
	public void update(Professor professor) {
		
		if (canUpdate(professor)) {
			logger.debug("Updating professor user %s", professor.getUserName());
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
			logger.debug("Deleting professor user %s", professor.getUserName());
			professor.setDeleted(true);
			professors.update(prof -> findUser(professor, prof), professor);
		}
	}
	
	public boolean add(Administrator administrator) {
		
		if (canAdd(administrator)) {
			logger.debug("Adding administrator %s", administrator.getUserName());
			return administrators.add(administrator);
		}
		
		return false;
	}
	
	public boolean update(Administrator administrator) {
		
		if (canUpdate(administrator) && !login.getUserName().equals(administrator.getUserName())) {
			logger.debug("Updating administrator %s", administrator.getUserName());
			return administrators.update(admin -> findUser(admin, administrator), administrator);
		}
		
		return false;
	}
	
	public Administrator getAdmin(String username) {
		
		return administrators.get(admin ->  findUser(username, admin)).get(0);
	}
	
	public List<Administrator> getAdmins() {
		
		return new ArrayList<Administrator>(administrators.getAll());
	}
	
	public void delete(Administrator administrator) {
		
		if (canUpdate(administrator) && !login.getUserName().equals(administrator.getUserName())) {
			logger.debug("Deleting administrator %s", administrator.getUserName());
			administrator.setDeleted(true);
			administrators.update(admin -> findUser(admin, administrator), administrator);
		}
	}
	
	public boolean usernameAvailable(User user) {
		var available =
			!students.exists(student -> findUser(user, student)) &&
			!professors.exists(professor -> findUser(user, professor)) &&
			!administrators.exists(admin -> findUser(user, admin));
		logger.debug("Username %s is available: %b", user.getUserName(), available);
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
