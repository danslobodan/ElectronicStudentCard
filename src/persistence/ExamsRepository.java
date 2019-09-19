package persistence;

import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;

import models.Exam;
import models.ExamTerm;

public class ExamsRepository extends Repository<Exam> {

	public ExamsRepository(List<Exam> items) {
		super(items, Exam.class);
	}

	@Override
	protected Exam getModel(JSONObject obj) {
		var exam = new Exam();
		exam.setClassId(obj.getString("classId"));
		var cal = Calendar.getInstance();
		cal.setTimeInMillis(obj.getInt("date"));
		exam.setDate(cal.getTime());
		exam.setExamTerm(Enum.valueOf(ExamTerm.class, obj.getString("examTerm")));
		exam.setGrade(obj.getInt("grade"));
		exam.setPoints(obj.getInt("points"));
		exam.setRevoked(obj.getBoolean("revoked"));
		exam.setStudentCard(obj.getInt("studentCard"));
		return exam;
	}

}
