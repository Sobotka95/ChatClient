package de.thm.chatclient.ui.dialogs;

import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import de.thm.chatclient.contacts.*;

public class CreateGroupDialog {
	
	private Dialog<Group> dialog;
	private VBox vBox_root;
	private HBox hBox_header;
	private Label label_groupName;
	private TextField textField_groupName;
	private ButtonType buttonType_apply;
	private Node node_apply;
	
	public CreateGroupDialog() {
		
		// Create the custom dialog.
		dialog = new Dialog<Group>();	
		dialog.setTitle("Gruppe erstellen");
		dialog.setHeaderText("Bitte geben Sie einen Gruppennamen an");
				
		// Set the button types.
		buttonType_apply = new ButtonType("Erstellen", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_apply, ButtonType.CANCEL);
		
		
		
		vBox_root = new VBox();
		hBox_header = new HBox();
				
		label_groupName = new Label("Gruppenname: ");
		hBox_header.getChildren().add(label_groupName);
				
		textField_groupName = new TextField();
		hBox_header.getChildren().add(textField_groupName);
				
		vBox_root.getChildren().add(hBox_header);
		dialog.getDialogPane().setContent(vBox_root);
				
		node_apply = dialog.getDialogPane().lookupButton(buttonType_apply);
		node_apply.setDisable(true);
				
		// Do some validation (using the Java 8 lambda syntax).
		textField_groupName.textProperty().addListener((observable, oldValue, newValue) -> {
			node_apply.setDisable(newValue.trim().isEmpty());
		});
				
		Platform.runLater(() -> textField_groupName.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == buttonType_apply) {
				return new Group(textField_groupName.getText());
			} else {
				return null;
			}
		});
	}
	
	public Group show() {
		Optional<Group> group = dialog.showAndWait();
		
		if(group.isPresent()) {
			return group.get();
		} else {
			return null;
		}
	}
	
}
