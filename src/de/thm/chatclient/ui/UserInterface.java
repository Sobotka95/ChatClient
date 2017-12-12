package de.thm.chatclient.ui;

import java.util.Arrays;
import java.util.List;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.messages.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	public static void main(String args[]) {
		
		ConsoleHelper.message("Willkommen im ChatClient der Gruppe F");
		
		ConsoleHelper.login();
		mainMenu();
		
		ConsoleHelper.message("Anwendung wurde beendet");
		
	}
	
	private static void mainMenu() {
		
		boolean exit = false;
		
		do {
			
			int choice = 0;
			
			choice = ConsoleHelper.menu("Hauptmenü", new String[] {"Nachrichten", "Kontakte", "Neu anmelden", "Beenden"});
			
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
					exit = ConsoleHelper.exit();
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
	
	private static void contactsMenu() {
		
		boolean back = false;
		
		do {
			
			int choice = 0;
			
			choice = ConsoleHelper.menu("Kontaktmenü", new String[] {"Alle Kontakte anzeigen", "Nur Personen anzeigen", "Nur Gruppen anzeigen", "Gruppen verwalten", "Zurück"});
		
			switch(choice) {
				case 1: {
					showContacts();
				} break;
				case 2: {
					showPersons();
				} break;
				case 3: {
					showGroups();
				} break;
				case 4: {
					groupMenu();
				} break;
				case 5: {
					back = true;
				}
			}
			
		} while(!back);
		
	}
	
	private static void groupMenu() {
		
		boolean back = false;
		
		do {
			
			int choice = 0;
			
			choice = ConsoleHelper.menu("Gruppen verwalten", new String[] {"Neue Gruppe", "Gruppe bearbeiten", "Gruppe löschen", "Zurück"});
		
			switch(choice) {
				case 1: {
					createGroup();
				} break;
				case 2: {
					editGroup();
				} break;
				case 3: {
					removeGroup();
				} break;
				case 4: {
					back = true;
				} break;
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
			
			choice = ConsoleHelper.listChoice("Text- oder Bildnachricht?", Arrays.asList("Textnachricht", "Bildnachricht"));
			
			Message message;
			
			if(choice == 1) {
				
				String text = ConsoleHelper.enterString("Geben Sie die Nachricht ein");
				
				TextMessage textMessage = new TextMessage();
				textMessage.setText(text);
				message = textMessage;
				
			} else if(choice == 2) {
				
				String imagePath = ConsoleHelper.enterString("Geben Sie den Bildpfad an");
				
				ImageMessage imageMessage = new ImageMessage();
				imageMessage.setImage(new RawImage(imagePath));
				message = imageMessage;		
				
			} else {
				return;
			}
			
			message.setReceiver(receiver);		
			MessageRepository.getInstance().sendMessage(AuthenticationRepository.getInstance().getAuth(), message);
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}

	private static void showContacts() {
		
		try {
			
			List<Contact> contacts = ContactRepository.getInstance().getAllContacts(AuthenticationRepository.getInstance().getAuth());
			ConsoleHelper.listView("Kontakte:", contacts) ;
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void showPersons() {
		
		try {
			
			List<Person> persons = ContactRepository.getInstance().getAllPersons(AuthenticationRepository.getInstance().getAuth());
			ConsoleHelper.listView("Personen:", persons) ;
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void showGroups() {
		
		try {
			
			List<Group> groups = ContactRepository.getInstance().getAllGroups();
			ConsoleHelper.listView("Gruppen:", groups) ;
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void createGroup() {
		
		try {
			
			String groupname = ConsoleHelper.enterString("Geben Sie einen Gruppennamen ein");
			Group group = new Group(groupname);
			
			ContactRepository.getInstance().addGroup(group);
			
			System.out.println();
			System.out.println("Gruppe wurde erfolgreich angelegt.");
			System.out.println();
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void editGroup() {
		
		int choice = 0;
		
		List<Group> groups = ContactRepository.getInstance().getAllGroups();
		choice = ConsoleHelper.listChoice("Gruppe auswählen", groups) ;
		
		if(choice <= 0) {
			return;
		}
		
		Group group = groups.get(choice - 1);
		
		boolean back = false;
		
		do {

			choice = ConsoleHelper.menu("Gruppe: " + group.getName(), new String[] {"Mitglieder anzeigen", "Mitglied hinzufügen", "Mitglied entfernen", "Gruppe löschen", "Zurück"});
		
			switch(choice) {
				case 1: {
					showMembers(group);
				} break;
				case 2: {
					addMember(group);
				} break;
				case 3: {
					removeMember(group);
				} break;
				case 4: {
					removeGroup(group);
					back = true;
				} break;
				case 5: {
					back = true;
				} break;
			}
			
		} while(!back);
	}
	
	private static void removeGroup() {
		
		try {
			
			int choice = 0;
			
			List<Group> groups = ContactRepository.getInstance().getAllGroups();
			choice = ConsoleHelper.listChoice("Gruppe auswählen", groups) ;
			
			if(choice <= 0) {
				return;
			}
				
			Group group = groups.get(choice - 1);
				
			ContactRepository.getInstance().removeGroup(group.getName());
			
			System.out.println();
			System.out.println("Gruppe wurde erfolgreich gelöscht.");
			System.out.println();
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void removeGroup(Group group) {
		
		try {
				
			ContactRepository.getInstance().removeGroup(group.getName());
			
			System.out.println();
			System.out.println("Gruppe wurde erfolgreich gelöscht.");
			System.out.println();
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void showMembers(Group group) {
		
		try {
			
			ConsoleHelper.listView("Mitglieder:", group.getMembers()) ;
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void addMember(Group group) {
		
		try {
			
			int choice = 0;
				
			List<Person> persons = ContactRepository.getInstance().getAllPersons(AuthenticationRepository.getInstance().getAuth());
			choice = ConsoleHelper.listChoice("Neues Mitglied auswählen", persons) ;
			
			if(choice <= 0) {
				return;
			}
				
			Person member = persons.get(choice - 1);
			
			group.addMember(member);
			
			System.out.println();
			System.out.println("Teilnehmer wurde erfolgreich hinzugefügt.");
			System.out.println();
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
	private static void removeMember(Group group) {
		
		try {

			List<Person> members = group.getMembers();
			int choice = ConsoleHelper.listChoice("Mitglied auswählen", members) ;
			
			if(choice <= 0) {
				return;
			}
				
			Person member = members.get(choice - 1);
			
			group.removeMember(member.getName());
			
			System.out.println();
			System.out.println("Mitglied wurde erfolgreich entfernt.");
			System.out.println();
			
		} catch (Exception e) {
			ConsoleHelper.error(e.getMessage());
		}
		
	}
	
}
