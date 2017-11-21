package de.thm.chatclient.ui.dialogs;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

import de.thm.chatclient.contacts.*;

public class ShowGroupsOfUserDialog {
	
	private Dialog dialog;
	private VBox vBox_root;
	private HBox hBox_header;
	private HBox hBox_body;
	private Label label_groups;
	private ListView<Group> listView_groups;
	private ButtonType buttonType_apply;
	
	public ShowGroupsOfUserDialog(String userName, List<Group> groups) {
		
		// Create the custom dialog.
		dialog = new Dialog();	
		dialog.setTitle("Gruppen");
		dialog.setHeaderText(userName);
						
		// Set the button types.
		buttonType_apply = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_apply);
						
		vBox_root = new VBox();
		hBox_header = new HBox();
		hBox_body = new HBox();
						
		label_groups = new Label("Gruppen: ");
		hBox_header.getChildren().add(label_groups);
						
		listView_groups = new ListView<Group>();
		listView_groups.setMaxHeight(100);
		listView_groups.setEditable(false);   
		ListProperty<Group> listProperty_groups = new SimpleListProperty<Group>();
		listProperty_groups.set(FXCollections.observableArrayList (groups));
		listView_groups.setItems(listProperty_groups);
		hBox_body.getChildren().add(listView_groups);
						
		vBox_root.getChildren().add(hBox_header);
		vBox_root.getChildren().add(hBox_body);
		dialog.getDialogPane().setContent(vBox_root);	
		
	}
	
	public void show() {
		dialog.showAndWait();
	}

}
