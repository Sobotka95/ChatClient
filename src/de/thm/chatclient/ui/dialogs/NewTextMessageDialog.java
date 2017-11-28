package de.thm.chatclient.ui.dialogs;

import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class NewTextMessageDialog {
	
	private Dialog<String> dialog;
	private HBox hBox_textMessage;
	private TextField textField_message;
	private ButtonType buttonType_send;
	private Node button_send;
	
	private String message;
	
	
	public NewTextMessageDialog() {
		// Create the custom dialog.
		dialog = new Dialog<String>();	
		dialog.setTitle("Neue Textnachricht");
		dialog.setHeaderText("Textnachricht");
		
		// Set the button types.
		buttonType_send = new ButtonType("Senden", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_send, ButtonType.CANCEL);
		
		button_send = dialog.getDialogPane().lookupButton(buttonType_send);
		button_send.setDisable(true);
		
		hBox_textMessage = new HBox();
		hBox_textMessage.getChildren().add(new Label("Nachricht:"));
		
		textField_message = new TextField();
		BorderPane.setMargin(textField_message, new Insets(15, 15, 15, 15));
		textField_message.textProperty().addListener(new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue ov, String oldValue, String newValue) {
	        		message = newValue;
	        		button_send.setDisable(false);
	        }    
	    });
		hBox_textMessage.getChildren().add(textField_message);

		dialog.getDialogPane().setContent(hBox_textMessage);

		Platform.runLater(() -> textField_message.requestFocus());

		dialog.setResultConverter(dialogButton -> {
		if (dialogButton == buttonType_send) {
			return message;
		} else
			return "";
		});
	}
	
	public String show() {
		Optional<String> result = dialog.showAndWait();
		return result.get();
	}

}
