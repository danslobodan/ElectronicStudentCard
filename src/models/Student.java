package models;

import java.util.ArrayList;

public class Student extends User {
	
	private String email;
	private ArrayList<String> phoneNumbers;
	private String index;

	public Student() {
		this.phoneNumbers = new ArrayList<String>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void addPhoneNumber(String phoneNumber) {
		this.phoneNumbers.add(phoneNumber);
	}
	
	public void removePhoneNumber(String phoneNumber) {
		this.phoneNumbers.remove(phoneNumber);
	}
	
	public ArrayList<String> getPhoneNumbers() {
		return new ArrayList<String>(phoneNumbers);
	}
}
