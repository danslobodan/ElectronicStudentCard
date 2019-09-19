package controllers;

import models.Administrator;
import models.Student;
import models.StudentCard;

public interface IUsersController {
	boolean add(Student student, StudentCard studentCard);
	boolean update(Student student);
	boolean add(Administrator administrator);
	boolean update(Administrator administrator);
}
