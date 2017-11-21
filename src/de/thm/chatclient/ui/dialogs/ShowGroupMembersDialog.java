package de.thm.chatclient.ui.dialogs;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import de.thm.chatclient.contacts.*;

public class ShowGroupMembersDialog {
	
	private Dialog dialog;
	private VBox vBox_root;
	private HBox hBox_header;
	private HBox hBox_body;
	private Label label_members;
	private ListView<User> listView_members;
	private ButtonType buttonType_apply;
	private Node node_apply;
	
	public ShowGroupMembersDialog(String groupName, List<User> members) {
		
		// Create the custom dialog.
		dialog = new Dialog();	
		dialog.setTitle("Gruppenmitglieder");
		dialog.setHeaderText(groupName);
				
		// Set the button types.
		buttonType_apply = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_apply);
				
		vBox_root = new VBox();
		hBox_header = new HBox();
		hBox_body = new HBox();
				
		label_members = new Label("Gruppenmitglieder: ");
		hBox_header.getChildren().add(label_members);
				
		listView_members = new ListView<User>();
		listView_members.setMaxHeight(100);
		listView_members.setEditable(false);   
		ListProperty<User> listProperty_members = new SimpleListProperty<User>();
		listProperty_members.set(FXCollections.observableArrayList (members));
		listView_members.setItems(listProperty_members);
		hBox_body.getChildren().add(listView_members);
				
		vBox_root.getChildren().add(hBox_header);
		vBox_root.getChildren().add(hBox_body);
		dialog.getDialogPane().setContent(vBox_root);	
		
	}
	
	public void show() {
		dialog.showAndWait();
	}

}
