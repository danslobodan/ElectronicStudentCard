package views;

import controllers.ILoginController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView extends Stage {

	public LoginView(Stage primaryStage, ILoginController loginController) {		
		
		var userNameText = new Text("Username");
		var userNameField = new TextField();
		
		var passwordText = new Text("Password");
		var passwordField = new PasswordField();
		
		var errorText = new Text();
		errorText.setFont(Font.font("Arial", FontWeight.BOLD, errorText.getFont().getSize()));
		errorText.setFill(Color.INDIANRED);
		
		var button = new Button("Login");
		button.setOnAction(actionEvent -> {
			
			if (loginController.login(userNameField.getText(), passwordField.getText())) {
				errorText.setText("");
				primaryStage.show();
				this.hide();			
			}
			
			errorText.setText("Invalid username or password");
		});
		
		var grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		grid.add(userNameText, 0, 0);
		grid.add(userNameField, 1, 0);
		grid.add(passwordText, 0, 1);
		grid.add(passwordField, 1, 1);
		grid.add(errorText, 0, 3, 3, 1);
		grid.add(button, 1, 2);
		
		var scene = new Scene(grid);
		scene.setFill(Color.DARKGRAY);
		
		this.setTitle("Login");
		this.setScene(scene);
	}
}
