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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class NewMessageDialog {
	
	private Dialog<String> dialog;
	private VBox vBox_root;
	private HBox hBox_header;
	private VBox vBox_message;
	private Label label_messageType;
	private ComboBox<String> comboBox_messageType;
	private ButtonType buttonType_send;
	private Node button_send;
	
	
	public NewMessageDialog() {
		// Create the custom dialog.
		dialog = new Dialog<String>();	
		dialog.setTitle("Neue Nachricht");
		dialog.setHeaderText("Test");
		
		// Set the button types.
		buttonType_send = new ButtonType("Senden", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_send, ButtonType.CANCEL);

		vBox_root = new VBox();
		hBox_header = new HBox();
		vBox_message = new VBox();
		
		label_messageType = new Label("Nachrichtentyp:");
		hBox_header.getChildren().add(label_messageType);
		
		comboBox_messageType = new ComboBox<String>();
		comboBox_messageType.getItems().addAll("Textnachricht", "Bildnachricht");
		comboBox_messageType.setPromptText("Nachrichtentyp");
		comboBox_messageType.setEditable(true);   
		hBox_header.getChildren().add(comboBox_messageType);
		
		vBox_root.getChildren().add(hBox_header);	
		vBox_root.getChildren().add(vBox_message);
		
		button_send = dialog.getDialogPane().lookupButton(buttonType_send);
		button_send.setDisable(true);

		dialog.getDialogPane().setContent(vBox_root);
		
		comboBox_messageType.valueProperty().addListener(new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue ov, String oldString, String newString) {
	        	
	        		if(newString.equals("Textnachricht")) {
	        			
	        			vBox_message.getChildren().clear();
		        		vBox_message.getChildren().add(addTextMessageGrid());
		        		dialog.setWidth(350);
		        		dialog.setHeight(200);
	        			
	        		} else if(newString.equals("Bildnachricht")) {
			
					vBox_message.getChildren().clear();
			
	        		}
	        		
	        }    
	    });

		Platform.runLater(() -> comboBox_messageType.requestFocus());

		dialog.setResultConverter(dialogButton -> {
		if (dialogButton == buttonType_send) {
			return comboBox_messageType.getSelectionModel().getSelectedItem().toString();
		} else
			return null;
		});
	}
	
	private HBox addTextMessageGrid() {
		HBox hBox_textMessage = new HBox();
		hBox_textMessage.getChildren().add(new Label("Nachricht:"));
		
		TextField textField_message = new TextField();
		textField_message.textProperty().addListener(new ChangeListener<String>() {
	        @Override 
	        public void changed(ObservableValue ov, String t, String t1) {
	        		button_send.setDisable(false);
	        }    
	    });
		hBox_textMessage.getChildren().add(textField_message);
		
		return hBox_textMessage;
	}
	
	/*private void addImageMessageGrid() {
		GridPane gridPane_imageMessage = new GridPane();
		gridPane_imageMessage.setHgap(10);
		gridPane_imageMessage.setVgap(10);
		gridPane_imageMessage.setPadding(new Insets(20, 150, 10, 10));
		gridPane_imageMessage.add(new Label("Bild:"), 0, 0);
		
	}*/
	
	public void show() {
		Optional<String> result = dialog.showAndWait();
	}

}
