package views;


import controllers.IUsersController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Administrator;
import utilities.StringExtensions;

public class UserView extends Stage {

	public UserView(IUsersController usersController) {
		
		var firstNameText = new Text("First Name");
		var firstNameField = new TextField();
		
		var lastNameText = new Text("Last Name");
		var lastNameField = new TextField();
		
		var usernameText = new Text("Username");
		var usernameField = new TextField();
		
		var passwordText = new Text("Password");
		var passwordField = new PasswordField();
		
		var confirmText = new Text("Confirm Text");
		var confirmField = new PasswordField();
		
		var deletedText = new Text("Deleted");
		var deleted = new CheckBox();
		
		var errorText = new Text();
		errorText.setFont(Font.font("Arial", FontWeight.BOLD, errorText.getFont().getSize()));
		errorText.setFill(Color.INDIANRED);
		
		var submit = new Button("Submit");
		submit.setOnAction(action -> {
			
			if (StringExtensions.IsNullOrWhitespace(firstNameField.getText()) &&
					StringExtensions.IsNullOrWhitespace(lastNameField.getText()) ||
					StringExtensions.IsNullOrWhitespace(usernameField.getText()) ||
					StringExtensions.IsNullOrWhitespace(passwordField.getText()) ||
					StringExtensions.IsNullOrWhitespace(confirmField.getText())) {
				
				errorText.setText("All fields are required.");
				return;
			}
			
			if (!(passwordField.getText().equals(confirmField.getText()))) {
				errorText.setText("Passwords do not match.");
				return;
			}

			Administrator admin = new Administrator();
			admin.setDeleted(deleted.isPressed());
			admin.setFirstName(firstNameField.getText());
			admin.setLastName(lastNameField.getText());
			admin.setUserName(usernameField.getText());
			admin.setPassword(passwordField.getText());
				
			if(!usersController.add(admin)) {
				errorText.setText("Failed to add admin.");
				return;
			}
		});
		
		var grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		grid.add(firstNameText, 0, 0);
		grid.add(firstNameField, 1, 0);
		
		grid.add(lastNameText, 0, 1);
		grid.add(lastNameField, 1, 1);
		
		grid.add(usernameText, 0, 2);
		grid.add(usernameField, 1, 2);
		
		grid.add(passwordText, 0, 3);
		grid.add(passwordField, 1, 3);
		
		grid.add(confirmText, 0, 4);
		grid.add(confirmField, 1, 4);
		
		grid.add(deletedText, 0, 5);
		grid.add(deleted, 1, 5);
		
		grid.add(submit, 0, 6);
		
		grid.add(errorText, 0, 7, 1, 2);
		
		var scene = new Scene(grid);
		this.setTitle("Add admin");
		this.setScene(scene);
	}
}
