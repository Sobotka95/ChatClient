package de.thm.chatclient.ui;

import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.messages.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	public static void main(String args[]) {
		
		ConsoleHelper.login();
		mainMenu();
		
	}
	
	private static void mainMenu() {
		
		boolean exit = false;
		
		do {
			
			int choice = 0;
			
			choice = ConsoleHelper.menu("Hauptmenü", new String[] {"Chatten", "Kontakte", "Neu anmelden", "Beenden"});
			
			switch(choice) {
				case 1: {
					chatMenu();
				} break;
				case 2: {
					contactsMenu();
				} break;
				case 3: {
					ConsoleHelper.login();
				} break;
				case 4: {
					exit = true;
					//TODO: exit();
				} break;
			}
			
		} while(!exit);

		
	}
	
	private static void chatMenu() {
	
		boolean back = false;
		
		do {
			
			int choice = 0;
			
			choice = ConsoleHelper.menu("Chatmenü", new String[] {"Chat öffnen", "Gruppennachricht senden", "Zurück"});
		
			switch(choice) {
				case 1: {
					openChat();
				} break;
				case 2: {
					sendGroupMessage();
				} break;
				case 3: {
					back = true;
				}
			}
			
		} while(!back);
		
	}
		
	private static void openChat() {
		
		try {
			
			List<Person> persons = ContactRepository.getInstance().getAllPersons(AuthenticationRepository.getInstance().getAuth());
			int choice = ConsoleHelper.listChoice("Benutzer auswählen", persons) ;
			
			if(choice <= 0) {
				return;
			}
				
			Person chatPartner = persons.get(choice - 1);
			
			List<Message> messages = MessageRepository.getInstance().getAllMessages(AuthenticationRepository.getInstance().getAuth(), 1);
			ConsoleHelper.listView("Nachrichtenverlauf", messages) ;	
			
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
	}
	
	private static void sendGroupMessage() {
		
		try {
			
			List<Group> groups = ContactRepository.getInstance().getAllGroups();
			int choice = ConsoleHelper.listChoice("Gruppe auswählen", groups) ;
			
			if(choice <= 0) {
				return;
			}
			
			Group group = groups.get(choice - 1);
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void contactsMenu() {
		
		int choice = 0;
		
		choice = ConsoleHelper.menu("Kontaktmenü", new String[] {"Kontakte anzeigen", "Neue Gruppe erstellen", "Gruppe löschen", "Teilnehmer hinzufügen", "Teilnehmer entfernen", "Zurück"});
		
	}
	
	
	
	
}
