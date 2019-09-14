package models;

import utilities.StringExtensions;

public abstract class User {

	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private boolean isDeleted;

	protected User() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public boolean modelIsValid() {
		return StringExtensions.IsNullOrWhitespace(getFirstName()) && 
			StringExtensions.IsNullOrWhitespace(getLastName()) &&
			StringExtensions.IsNullOrWhitespace(getUserName()) &&
			StringExtensions.IsNullOrWhitespace(getPassword());
	}

	public boolean isIdenticalTo(User model) {
		return model.getUserName().equals(getUserName());
	}
}
