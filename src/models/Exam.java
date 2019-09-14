package models;

import java.util.Date;

public class Exam implements IModel<Exam> {

	private int studentCard;
	private int grade;
	private Date date;
	private boolean isRevoked;
	private int classId;
	
	public Exam() {
	}
	
	public int getStudentCard() {
		return studentCard;
	}

	public void setStudentCard(int studentCard) {
		this.studentCard = studentCard;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isRevoked() {
		return isRevoked;
	}

	public void setRevoked(boolean isRevoked) {
		this.isRevoked = isRevoked;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	@Override
	public boolean modelIsValid() {
		return studentCard > 0 &&
			grade >= 5 && grade <= 10 &&
			classId > 0;
	}

	@Override
	public boolean isIdenticalTo(Exam model) {
		return studentCard == model.getStudentCard() &&
			grade == model.getGrade() &&
			date.equals(model.getDate()) &&
			classId == model.getClassId();
	}
}
