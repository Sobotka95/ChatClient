package de.thm.chatclient.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.messages.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	public static void main(String args[]) {
		
		ConsoleHelper.login();
		
		try {
			ContactRepository.getInstance().addGroup(new Group("Testgruppe"));
			ContactRepository.getInstance().getGroupByName("Testgruppe").addMember(ContactRepository.getInstance().getPersonByName(AuthenticationRepository.getInstance().getAuth(), "julian.sobotka"));
			ContactRepository.getInstance().getGroupByName("Testgruppe").addMember(ContactRepository.getInstance().getPersonByName(AuthenticationRepository.getInstance().getAuth(), "ralf.merhof"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ConsoleHelper.error(e.getMessage());
		}
		
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
			
			choice = ConsoleHelper.menu("Chatmenü", new String[] {"Nachrichten anzeigen", "Nachricht schreiben", "Zurück"});
		
			switch(choice) {
				case 1: {
					showMessages();
				} break;
				case 2: {
					sendMessage();
				} break;
				case 3: {
					back = true;
				}
			}
			
		} while(!back);
		
	}
		
	private static void showMessages() {
		
		try {
			
			List<Person> persons = ContactRepository.getInstance().getAllPersons(AuthenticationRepository.getInstance().getAuth());
			int choice = ConsoleHelper.listChoice("Benutzer auswählen", persons) ;
			
			if(choice <= 0) {
				return;
			}
				
			Person person = persons.get(choice - 1);
			
			List<Message> messages = MessageRepository.getInstance().getMessagesByPerson(AuthenticationRepository.getInstance().getAuth(), person.getName());
			ConsoleHelper.listView("Nachrichtenverlauf", messages) ;	
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
	}
	
	private static void sendMessage() {
	
		try {
			
			int choice = 0;
			
			List<Contact> contacts = ContactRepository.getInstance().getAllContacts(AuthenticationRepository.getInstance().getAuth());
			choice = ConsoleHelper.listChoice("Empfänger auswählen", contacts) ;
			
			if(choice <= 0) {
				return;
			}
			
			Contact receiver = contacts.get(choice - 1);
			
			choice = ConsoleHelper.listChoice("Bild oder Textnachricht?", Arrays.asList("Bildnachricht", "Textnachricht"));
			
			Message message;
			
			if(choice == 1) {
				
				String imagePath = ConsoleHelper.enterString("Geben Sie den Bildpfad an");
				
				ImageMessage imageMessage = new ImageMessage();
				imageMessage.setImage(new RawImage(imagePath));
				message = imageMessage;
				
			} else if(choice == 2) {
				
				String text = ConsoleHelper.enterString("Geben Sie die Nachricht ein");
				
				TextMessage textMessage = new TextMessage();
				textMessage.setText(text);
				message = textMessage;
				
			} else {
				return;
			}
			
			message.setReceiver(receiver);		
			MessageRepository.getInstance().sendMessage(AuthenticationRepository.getInstance().getAuth(), message);
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void contactsMenu() {
		
		int choice = 0;
		
		choice = ConsoleHelper.menu("Kontaktmenü", new String[] {"Kontakte anzeigen", "Neue Gruppe erstellen", "Gruppe löschen", "Teilnehmer hinzufügen", "Teilnehmer entfernen", "Zurück"});
		
	}
	
	
	
	
}
