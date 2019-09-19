package persistence;

import java.util.List;

import org.json.JSONObject;

import models.Professor;
import models.Title;

public class ProfessorRepository extends Repository<Professor> {

	public ProfessorRepository(List<Professor> items) {
		super(items, Professor.class);
	}

	@Override
	protected Professor getModel(JSONObject obj) {
		var professor = new Professor();
		professor.setDeleted(obj.getBoolean("deleted"));
		professor.setEmail(obj.getString("email"));
		professor.setFirstName(obj.getString("firstName"));
		professor.setLastName(obj.getString("lastName"));
		professor.setPassword(obj.getString("password"));
		professor.setUserName(obj.getString("userName"));
		professor.setTitle(Enum.valueOf(Title.class, obj.getString("title")));
		return professor;
	}

}
