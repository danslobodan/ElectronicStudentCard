package models;

import java.util.ArrayList;
import utilities.StringExtensions;

public class Student extends User implements IModel<Student> {
	
	private String email;
	private ArrayList<String> phoneNumbers;
	private int cardId;

	public Student() {
		this.phoneNumbers = new ArrayList<String>();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
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

	@Override
	public boolean modelIsValid() {
		return super.modelIsValid() &&
			StringExtensions.IsNullOrWhitespace(email) &&
			cardId > 0;
	}
}
