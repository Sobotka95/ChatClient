package de.thm.chatclient.ui.dialogs;

import java.util.List;
import java.util.Optional;

import de.thm.chatclient.contacts.Group;
import de.thm.chatclient.security.Authentication;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddToGroupDialog {
	
	private Dialog<String> dialog;
	private VBox vBox_root;
	private HBox hBox_header;
	private Label label_groupSelection;
	private ComboBox<Group> comboBox_groupSelection;
	private ButtonType buttonType_apply;
	private Node node_apply;
	
	
	public AddToGroupDialog(List<Group> groups) {
		
		// Create the custom dialog.
		dialog = new Dialog<String>();	
		dialog.setTitle("Benutzer zu Gruppe hinzufügen");
		dialog.setHeaderText("Bitte wählen Sie eine Gruppe aus");
		
		// Set the button types.
		buttonType_apply = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonType_apply, ButtonType.CANCEL);
		
		vBox_root = new VBox();
		hBox_header = new HBox();
		
		label_groupSelection = new Label("Gruppe: ");
		hBox_header.getChildren().add(label_groupSelection);
		
		comboBox_groupSelection = new ComboBox<Group>();
		ListProperty<Group> listProperty_groups = new SimpleListProperty<Group>();
		listProperty_groups.set(FXCollections.observableArrayList (groups));
		comboBox_groupSelection.setItems(listProperty_groups);
		comboBox_groupSelection.setEditable(true);   
		hBox_header.getChildren().add(comboBox_groupSelection);
		
		vBox_root.getChildren().add(hBox_header);
		dialog.getDialogPane().setContent(vBox_root);	
		
		node_apply = dialog.getDialogPane().lookupButton(buttonType_apply);
		node_apply.setDisable(true);
		
		// Do some validation
		comboBox_groupSelection.getSelectionModel().selectedItemProperty().asString().addListener( (options, oldValue, newValue) -> {
			System.out.println(newValue);
			node_apply.setDisable(false);
		}); 
		
		
		Platform.runLater(() -> comboBox_groupSelection.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == buttonType_apply) {
		        return  comboBox_groupSelection.getItems().get(comboBox_groupSelection.getSelectionModel().getSelectedIndex()).getName();
		    } else {
		    		return "";
		    }	    
		});
		
	}

	public String show() {
		
		Optional<String> selectedGroupName = dialog.showAndWait();
		return selectedGroupName.get();
		
	}
	
}
