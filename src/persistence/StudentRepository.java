package persistence;

import java.util.List;

import org.json.JSONObject;

import models.Student;

public class StudentRepository extends Repository<Student> {

	public StudentRepository(List<Student> items) {
		super(items, Student.class);
	}

	@Override
	protected Student getModel(JSONObject obj) {
		var student = new Student();
		student.setCardId(obj.getInt("cardId"));
		student.setDeleted(obj.getBoolean("deleted"));
		student.setEmail(obj.getString("email"));
		student.setFirstName(obj.getString("firstName"));
		student.setLastName(obj.getString("lastName"));
		student.setPassword(obj.getString("password"));
		student.setUserName(obj.getString("userName"));
		return student;
	}

}
