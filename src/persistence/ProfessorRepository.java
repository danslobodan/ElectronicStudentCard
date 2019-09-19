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
		professor.setFirstName("firstName");
		professor.setLastName("lastName");
		professor.setPassword("password");
		professor.setUserName("userName");
		professor.setTitle(Enum.valueOf(Title.class, obj.getString("title")));
		return professor;
	}

}
