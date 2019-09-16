package controllers;

public interface ILoginController {
	boolean login(String username, String password);
	void logout();
}
