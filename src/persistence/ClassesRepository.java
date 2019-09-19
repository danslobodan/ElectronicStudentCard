package persistence;

import java.util.List;

import org.json.JSONObject;

import models.UniClass;

public class ClassesRepository extends Repository<UniClass> {

	public ClassesRepository(List<UniClass> items) {
		super(items, UniClass.class);
	}

	@Override
	protected UniClass getModel(JSONObject obj) {
		var cls = new UniClass();
		cls.setId(obj.getString("id"));
		cls.setName(obj.getString("name"));
		cls.setCalendarYear(obj.getInt("calendarYear"));
		cls.setCurriculumYear(obj.getInt("curriculumYear"));
		cls.setNumberOfPracticeClasses(obj.getInt("numberOfPracticeClasses"));
		cls.setNumberOfTheoryClasses(obj.getInt("numberOfTheoryClasses"));
		cls.setSyllabus(obj.getString("syllabus"));
		return cls;
	}
	
	
}