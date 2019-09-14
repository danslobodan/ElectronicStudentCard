package models;

public class Professor extends User {

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
}
