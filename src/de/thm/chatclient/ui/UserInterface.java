package de.thm.chatclient.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.GlyphsStack;
import de.jensd.fx.glyphs.fontawesome.*;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.messages.*;
import de.thm.chatclient.security.*;
import de.thm.chatclient.ui.builder.*;
import de.thm.chatclient.ui.fa.*;
import de.thm.chatclient.ui.dialogs.*;

public class UserInterface extends Application {
	
	Scene scene;
	
	BorderPane borderPane_root;
	
	HBox hBox_top;
	Label label_title;
	
	VBox vBox_left;	
	TabPane tabPane_chat;
	
	FxBuilder fxBuilder;
	
	VBox vBox_right;
	
	HBox hBox_userButtons;
	Label label_users;
	ListView<User> listView_users;
	Button button_openUserChat;
	Button button_addUserToGroup;
	Button button_showGroupsOfUser;
	
	HBox hBox_groupButtons;
	Label label_groups;
	ListView<Group> listView_groups;
	Button button_createGroup;
	Button button_sendGroupMessage;
	Button button_showGroupMembers;
	
	AuthenticationRepository userRepository;
	MessageRepository messageRepository;
	ContactRepository contactRepository;


	public static void main(String[] args) {
		launch(args);
	}
	
	private void initRootBox() {
		borderPane_root = new BorderPane();
	}
	
	private void initTopBox() {
		
		if(borderPane_root == null) {
			return;
		}
		
		/**
		 * Init HBox
		 */
		hBox_top = new HBox();
		
		/**
		 * Init Label Title
		 */
		label_title = new Label("Chat Client");
		hBox_top.getChildren().add(label_title);
	    
	    BorderPane.setMargin(hBox_top, new Insets(10, 10, 10, 10));
	    
		borderPane_root.setTop(hBox_top);
	    
	}
	
	private void initLeftBox() {
		
		if(borderPane_root == null) {
			return;
		}
		
		/**
		 * Init VBox
		 */
		vBox_left = new VBox();
		
		/**
		 * Init TabPane Chat
		 */
		tabPane_chat = new TabPane();
		tabPane_chat.setMaxWidth(300);
		vBox_left.getChildren().add(tabPane_chat);
	    
		borderPane_root.setLeft(vBox_left);
		
	}
	
	private void initRightBox() {
		
		if(borderPane_root == null) {
			return;
		}
		
		/**
		 * Init VBox
		 */
		vBox_right = new VBox();

		
		/**
		 * Init Label Users
		 */
		label_users = new Label();
		label_users.setText("Benutzer online");
		vBox_right.getChildren().add(label_users);
		
		/**
		 * Init ListView Users
		 */
		listView_users = new ListView<User>();
		/*ObservableList<Person> userItems = FXCollections.observableArrayList (
		    "User 1", "User 2", "User 3");
		listView_users.setItems(userItems);*/
		//listView_users.setId();
		listView_users.setMaxHeight(100);
		vBox_right.getChildren().add(listView_users);
		
		/**
		 * Init HBox
		 */
		hBox_userButtons = new HBox();
		
		/**
		 * Init Button Open User Chat
		 */
		button_openUserChat = fxBuilder.createButton(FontAwesome.ICON.WECHAT, "Chat öffnen", 1);
		button_openUserChat.setDisable(true);
		hBox_userButtons.getChildren().add(button_openUserChat);
		
		
		/**
		 * Init Button Add User To Group
		 */
		button_addUserToGroup = fxBuilder.createButton(FontAwesome.ICON.USER_PLUS, "Gruppe zuweisen", 1);
		button_addUserToGroup.setDisable(true);
		hBox_userButtons.getChildren().add(button_addUserToGroup);
		
		
		/**
		 * Init Button Show Groups From User To Group
		 */
		button_showGroupsOfUser = fxBuilder.createButton(FontAwesome.ICON.GROUP, "Gruppen anzeigen", 1);
		button_showGroupsOfUser.setDisable(true);
		hBox_userButtons.getChildren().add(button_showGroupsOfUser);
		
		vBox_right.getChildren().add(hBox_userButtons);
		
		/**
		 * Init Label Groups
		 */
		label_groups = new Label();
		label_groups.setText("Empfängergruppen");
		vBox_right.getChildren().add(label_groups);
		
		
		/**
		 * Init ListView Groups
		 */
		listView_groups = new ListView<Group>();
		listView_groups.setMaxHeight(100);
		vBox_right.getChildren().add(listView_groups);	
		
		/**
		 * Init HBox
		 */
		hBox_groupButtons = new HBox();
		
		/**
		 * Init Button Send Group Message
		 */
		button_sendGroupMessage = fxBuilder.createButton(FontAwesome.ICON.WECHAT, "Gruppennachricht senden", 1);
		button_sendGroupMessage.setDisable(true);
		hBox_groupButtons.getChildren().add(button_sendGroupMessage);
		
		
		/**
		 * Init Button Show Group Members Message
		 */
		button_showGroupMembers = fxBuilder.createButton(FontAwesome.ICON.GROUP, "Mitglieder anzeigen", 1);
		button_showGroupMembers.setDisable(true);
		hBox_groupButtons.getChildren().add(button_showGroupMembers);
		
		
		/**
		 * Init Button Create Group
		 */
		button_createGroup = fxBuilder.createButton(FontAwesome.ICON.PLUS, "Neue Gruppe", 1);	
		hBox_groupButtons.getChildren().add(button_createGroup);	
		
		vBox_right.getChildren().add(hBox_groupButtons);
		
		BorderPane.setMargin(vBox_right, new Insets(10, 10, 10, 10));
		
		borderPane_root.setRight(vBox_right);
		
	}
	
	private void initEventListeners() {
		
		/**
		 * 
		 * ListView Users Selection Event
		 * 
		 */
		listView_users.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
			
			@Override
			public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
				button_openUserChat.setDisable(false);
				button_addUserToGroup.setDisable(false);
				button_showGroupsOfUser.setDisable(false);
			}
			
        });
		
		
		/**
		 * 
		 * Button OpenUserChat Click Event
		 * 
		 */
		button_openUserChat.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {				
				newUserChat(listView_users.getSelectionModel().getSelectedItem().toString());				
			}
			
		});
		
		
		/**
		 * 
		 * Button AddUserToGroup Click Event
		 * 
		 */
		button_addUserToGroup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				User selectedPerson = listView_users.getSelectionModel().getSelectedItem();
				
				AddToGroupDialog addToGroupDialog = new AddToGroupDialog(contactRepository.getAllGroups());
				String selectedGroupName = addToGroupDialog.show();
				
				if(selectedGroupName != null) {
					try {
						contactRepository.getGroupByName(selectedGroupName).addMember(selectedPerson);
					} catch(Exception ex) {
						showAlert(AlertType.ERROR, "Fehler", "Fehler beim Hinzufügen zu einer Gruppe", ex.getMessage());
					}
				}
			}
			
		});
		
		/**
		 * 
		 * Button ShowGroupsOfUser Click Event
		 * 
		 */
		button_showGroupsOfUser.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				ShowGroupsOfUserDialog showGroupsOfUserDialog = new ShowGroupsOfUserDialog(
						listView_users.getSelectionModel().getSelectedItem().getName(),
						listView_users.getSelectionModel().getSelectedItem().getGroups()
						);
				showGroupsOfUserDialog.show();
				
			}
			
		});
		
		/**
		 * 
		 * ListView Groups Selection Event
		 * 
		 */
		listView_groups.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Group>() {
			
			@Override
			public void changed(ObservableValue<? extends Group> observable, Group oldValue, Group newValue) {
				button_sendGroupMessage.setDisable(false);
				button_showGroupMembers.setDisable(false);
			}
			
        });
		
		
		/**
		 * 
		 * Button Send Group Message Click Event
		 * 
		 */
		button_sendGroupMessage.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {				
				
			}
			
		});
		
		
		/**
		 * 
		 * Button Create Group Click Event
		 * 
		 */
		button_createGroup.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				CreateGroupDialog createGroupDialog = new CreateGroupDialog();
				Group group = createGroupDialog.show();			
				if(group != null) {
					try {
						contactRepository.addGroup(group);
						refreshGroupView();
					} catch(Exception ex) {
						showAlert(AlertType.ERROR, "Fehler", "Fehler beim Anlegen der Gruppe", ex.getMessage());
					}
				}			
			}
			
		});
		
		
		/**
		 * 
		 * Button ShowGroupMembers Click Event
		 * 
		 */
		button_showGroupMembers.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ShowGroupMembersDialog showGroupMembersDialog = new ShowGroupMembersDialog(
						listView_groups.getSelectionModel().getSelectedItem().getName(),
						listView_groups.getSelectionModel().getSelectedItem().getMembers()
						);
				showGroupMembersDialog.show();
			}
			
		});
		
		
	}
	
	private void initScene() {
		
		if(borderPane_root == null) {
			return;
		}
		
		scene = new Scene(borderPane_root);
		
	}
	
	private void initRepositories() {
		userRepository = new AuthenticationRepository();
		messageRepository = new MessageRepository();
		contactRepository = new ContactRepository();
	}
	
	private void newUserChat(String chatName) {
		
		boolean alreadyExists = false;
		
		for(Tab tab: tabPane_chat.getTabs()) {
			if(tab.getText().equals("Chat: " + chatName)) {
				alreadyExists = true;
			}
		}
		
		if(alreadyExists) {
			return;
		}
		
		Tab chatTab = new Tab();
		chatTab.setText("Chat: " + chatName);
		chatTab.setContent(new Rectangle(300,300, Color.WHITE));
		tabPane_chat.getTabs().add(chatTab);
		tabPane_chat.requestFocus();
		tabPane_chat.getSelectionModel().select(tabPane_chat.getTabs().size() - 1);		
		
	}
	
	private void showAlert(AlertType alertType, String title, String header, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private void refreshUserView() {
		try {
			ListProperty<User> listProperty_users = new SimpleListProperty<User>();
			listProperty_users.set(
					FXCollections.observableArrayList (
							contactRepository.getAllUsers(
									userRepository.getUser().getUsername(), 
									userRepository.getUser().getPassword()
									)
							)
					);
			listView_users.setItems(listProperty_users);
		} catch(Exception ex) {
			showAlert(AlertType.ERROR, "Fehler", "Fehler beim Aktualisieren der Benutzeransicht", ex.getMessage());
		}
	}
	
	private void refreshGroupView() {
		try {
			ListProperty<Group> listProperty_groups = new SimpleListProperty<Group>();
			listProperty_groups.set(FXCollections.observableArrayList (contactRepository.getAllGroups()));
			listView_groups.setItems(listProperty_groups);
		} catch(Exception ex) {
			showAlert(AlertType.ERROR, "Fehler", "Fehler beim Aktualisieren der Gruppenansicht", ex.getMessage());
		}	
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		fxBuilder = new FxBuilder();
		
		initRootBox();
		initTopBox();
		initLeftBox();
		initRightBox();	
		initScene();
		
		initEventListeners();
		
		initRepositories();
			
		stage.setScene(scene);
		stage.setMinWidth(600);
		stage.setMinHeight(600);
		stage.show();
		
		LoginDialog loginDialog = new LoginDialog();
		Authentication user = loginDialog.show();	
		
		if(user == null) {
			// If login was canceled exit application
			Platform.exit();
	        System.exit(0);
		} else {
			userRepository.setUser(user);
		}
		
		
		refreshGroupView();
		refreshUserView();
		
		/*
		// load the stylesheet
		String style = getClass().getResource("style.css").toExternalForm();

		// apply stylesheet to the scene graph
		scene.getStylesheets().addAll(style);

		// apply stylesheet to a node
		//parentPanel.getStylesheets().addAll(style);
		
		stage.show();
		*/
	}
	
}
