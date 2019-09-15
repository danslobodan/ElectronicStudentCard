package controllers;

public interface ILoggedInUser {
	boolean isLoggedIn();
	AccessLevel getAccessLevel();
	String getDisplayName();
	String getUserName();
}
