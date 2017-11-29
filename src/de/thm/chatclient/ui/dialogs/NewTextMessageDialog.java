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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class NewTextMessageDialog {
	
	private Dialog<String> dialog;
	private VBox vBox_textMessage;
	private TextArea textArea_message;
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
		
		vBox_textMessage = new VBox();
		vBox_textMessage.getChildren().add(new Label("Nachricht:"));
		
		textArea_message = new TextArea();
		textArea_message.setMinHeight(25);
		textArea_message.setMaxHeight(25);
		BorderPane.setMargin(textArea_message, new Insets(15, 15, 15, 15));
		textArea_message.textProperty().addListener(new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue ov, String oldValue, String newValue) {
	        		message = newValue;
	        		button_send.setDisable(false);
	        }    
	    });
		vBox_textMessage.getChildren().add(textArea_message);

		dialog.getDialogPane().setContent(vBox_textMessage);

		Platform.runLater(() -> textArea_message.requestFocus());

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
