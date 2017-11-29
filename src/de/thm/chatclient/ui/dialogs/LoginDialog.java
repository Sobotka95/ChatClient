package de.thm.chatclient.ui.dialogs;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import de.thm.chatclient.security.*;

public class LoginDialog {
	
	private Dialog<Authentication> dialog;
	private ButtonType buttonType_login;
	private Node node_login;
	private GridPane gridPane_root;
	private TextField textField_username;
	private PasswordField passwordField_password;
	
	public LoginDialog() {
		// Create the custom dialog.
		dialog = new Dialog<Authentication>();
		dialog.setTitle("Anmelden");
		dialog.setHeaderText("Bitte geben Sie Ihre Benutzerdaten ein");

		// Set the icon (must be included in the project).
		//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		buttonType_login = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_login, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		gridPane_root = new GridPane();
		gridPane_root.setHgap(10);
		gridPane_root.setVgap(10);
		gridPane_root.setPadding(new Insets(20, 150, 10, 10));

		textField_username = new TextField();
		textField_username.setPromptText("Benutzername");
		passwordField_password = new PasswordField();
		passwordField_password.setPromptText("Passwort");

		gridPane_root.add(new Label("Benutzername:"), 0, 0);
		gridPane_root.add(textField_username, 1, 0);
		gridPane_root.add(new Label("Passwort:"), 0, 1);
		gridPane_root.add(passwordField_password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		node_login = dialog.getDialogPane().lookupButton(buttonType_login);
		node_login.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		textField_username.textProperty().addListener((observable, oldValue, newValue) -> {
			node_login.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(gridPane_root);

		// Request focus on the username field by default.
		Platform.runLater(() -> textField_username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == buttonType_login) {
		        return new Authentication(textField_username.getText(), passwordField_password.getText());
		    } else {
		    		return null;
		    }	    
		});
	}
	
	public Authentication show() {
		
		Optional<Authentication> user = dialog.showAndWait();

		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
		
		/*result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});*/
	}

}
