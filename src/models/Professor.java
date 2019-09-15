package models;

import utilities.StringExtensions;

public class Professor extends User implements IModel<Professor> {

	private String email;
	private Title title;
	
	public Professor() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	@Override
	public boolean modelIsValid() {
		return super.modelIsValid() &&
			StringExtensions.IsNullOrWhitespace(email) &&
			title != null;
	}
}
