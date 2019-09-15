package controllers;

import models.Administrator;
import models.Professor;
import models.Student;

public interface ILogin extends ILoggedInUser {
	void login(Student student);
	void login(Professor professor);
	void login(Administrator administrator);
	void logout();
}
