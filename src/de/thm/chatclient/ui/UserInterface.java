package de.thm.chatclient.ui;

import de.thm.chatclient.messages.*;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	private static final String menuLine = "-------------------------------";
	private static final String errorLine = "- ! - ! - ! - ! - ! - ! - ! - ! -";
	
	private static AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	private static ContactRepository contactRepository = new ContactRepository();
	private static MessageRepository messageRepository = new MessageRepository();
	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		loginDialog();
		mainMenu();
	}
	
	private static void mainMenu() {
		
		boolean exit = false;

		do {
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println("Hauptmenü (Angemeldet als: " + authenticationRepository.getAuth().getUsername() + ")");
			System.out.println();
			System.out.println("[1] Chatten");
			System.out.println("[2] Kontakte");
			System.out.println("[3] Neu anmelden");
			System.out.println("[4] Anwendung beenden");
			System.out.println();

			int choice = 0;
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();
				
				if(choice < 1 || choice > 4) {
					throw new InputMismatchException();
				}
				
				
				switch(choice) {
					case 1: {
						chatMenu();
					} break;
					case 2: {
						contactMenu();
					} break;
					case 3: {
						loginDialog();	
					} break;
					case 4: {
						exit = exitDialog();
					} break;
				}
						
			} catch (InputMismatchException ex) {
				error("Ungültige Eingabe");			
			} catch (NoSuchElementException ex) {		
				error("NoSuchElementException: " + ex.getMessage());			
			} catch (IllegalStateException ex) {		
				error("IllegalStateException: " + ex.getMessage());		
			} catch (Exception ex) {		
				error("Exception: " + ex.getMessage());			
			}
			
		} while(!exit);
		
		exit();
		
	}

	private static void chatMenu() {
		
		boolean exit = false;

		do {
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println("Menü - Chatten (Angemeldet als: " + authenticationRepository.getAuth().getUsername() + ")");
			System.out.println();
			System.out.println("[1] Nachricht schreiben");
			System.out.println("[2] Nachrichten abrufen");
			System.out.println("[3] Zurück");
			System.out.println();

			int choice = 0;
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();
				
				if(choice < 1 || choice > 3) {
					throw new InputMismatchException();
				}
				
				switch(choice) {
					case 1: {
						newMessageDialog();
					} break;
					case 2: {
						showMessagesDialog();
					} break;
					case 3: {
						exit = true;
					} break;
				}
						
			} catch (InputMismatchException ex) {
				error("Ungültige Eingabe");			
			} catch (NoSuchElementException ex) {		
				error("NoSuchElementException: " + ex.getMessage());			
			} catch (IllegalStateException ex) {		
				error("IllegalStateException: " + ex.getMessage());		
			} catch (Exception ex) {		
				error("Exception: " + ex.getMessage());			
			}
			
		} while(!exit);
		
	}
	
	private static void contactMenu() {
		
		boolean exit = false;

		do {
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println("Menü - Kontakte (Angemeldet als: " + authenticationRepository.getAuth().getUsername() + ")");
			System.out.println();
			System.out.println("[1] Alle Kontakte anzeigen");
			System.out.println("[2] Nur Personen anzeigen");
			System.out.println("[3] Nur Gruppen anzeigen");
			System.out.println("[4] Gruppe erstellen");
			System.out.println("[5] Gruppenmitglieder bearbeiten");
			System.out.println("[6] Gruppe löschen");
			System.out.println("[7] Zurück");
			System.out.println();

			int choice = 0;
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();
				
				if(choice < 1 || choice > 7) {
					throw new InputMismatchException();
				}
				
				switch(choice) {
					case 1: {
						showContactsDialog();
					} break;
					case 2: {
						showPersonsDialog();
					} break;
					case 3: {
						showGroupsDialog();
					} break;
					case 4: {
						createGroupDialog();
					} break;
					case 5: {
						editGroupMembersDialog();
					} break;
					case 6: {
						removeGroupDialog();
					} break;
					case 7: {
						exit = true;
					} break;
				}
						
			} catch (InputMismatchException ex) {
				error("Ungültige Eingabe");			
			} catch (NoSuchElementException ex) {		
				error("NoSuchElementException: " + ex.getMessage());			
			} catch (IllegalStateException ex) {		
				error("IllegalStateException: " + ex.getMessage());		
			} catch (Exception ex) {		
				error("Exception: " + ex.getMessage());			
			}
			
		} while(!exit);
		
	}
	
	private static void loginDialog() {

		boolean error = false;

		do {
			
			error = false;
			
			try {
				
				System.out.print("Benutzername: ");
				String username = in.next();
				System.out.print("Passwort: ");
				String password = in.next();
				authenticationRepository.setAuth(new Authentication(username, password));
				
			} catch (InputMismatchException ex) {
				error = true;
				error("Ungültige Eingabe");		
			} catch (NoSuchElementException ex) {	
				error = true;
				error("NoSuchElementException: " + ex.getMessage());	
			} catch (IllegalStateException ex) {	
				error = true;
				error("IllegalStateException: " + ex.getMessage());		
			} catch (Exception ex) {
				error = true;
				error("Exception: " + ex.getMessage());			
			}
			
		} while(error);
		
	}
	
	private static boolean exitDialog() {
		
		boolean exit = false;
		boolean error = false;
		
		do {
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println("Möchten Sie das Programm wirklich verlassen?");
			System.out.println();
			System.out.println("[J]a oder [N]ein ?");
			System.out.println();
			
			String choice = "";
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.next();
					
				if(choice.toLowerCase().equals("j")) {
					exit = true;
				} else if(choice.toLowerCase().equals("n")) {
					exit = false;
				} else {
					throw new InputMismatchException();
				}
						
			} catch (InputMismatchException ex) {	
				error = true;
				error("Ungültige Eingabe");							
			} catch (NoSuchElementException ex) {		
				error = true;	
				error("NoSuchElementException: " + ex.getMessage());	
			} catch (IllegalStateException ex) {		
				error = true;
				error("IllegalStateException: " + ex.getMessage());					
			} catch (Exception ex) {		
				error = true;	
				error("Exception: " + ex.getMessage());				
			}
			
		} while(error);
		
		return exit;
		
	}
	
	private static void newMessageDialog() {
		
		System.out.println();
		System.out.println(menuLine);
		System.out.println("Nachricht schreiben");
		System.out.println();
		
		try {
			
			List<Contact> contacts = contactRepository.getAllContacts(authenticationRepository.getAuth());
			
			System.out.println("Kontakte:");
			System.out.println();
			
			for(int i=0; i<contacts.size(); i++) {
				System.out.println("[" + (i+1) + "] " + contacts.get(i));
			}
			
			System.out.println();
			System.out.println("An welchen Kontakt möchten Sie eine Nachricht senden?");
			System.out.println();
			
			int choice = 0;
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();

				if(choice < 1  || choice > contacts.size()) {
					throw new InputMismatchException();
				}				
						
			} catch (InputMismatchException ex) {			
				error("Ungültige Eingabe");					
			} catch (NoSuchElementException ex) {					
				error("NoSuchElementException: " + ex.getMessage());				
			} catch (IllegalStateException ex) {			
				error("IllegalStateException: " + ex.getMessage());			
			} catch (Exception ex) {				
				error("Exception: " + ex.getMessage());				
			}
			
			Contact selectedContact = contacts.get(choice - 1);
			System.out.println("Ausgewählter Kontakt: " + selectedContact);
			System.out.println();
			
		} catch (Exception ex) {	
			error("Fehler beim Laden der Kontakte: " + ex.getMessage());
		}
		
	}
	
	private static void showMessagesDialog() {
		
	}
	
	private static void showContactsDialog() {
		
		System.out.println();
		System.out.println(menuLine);
		System.out.println("Kontakte:");
		System.out.println();
		
		try {
			List<Contact> contacts = contactRepository.getAllContacts(authenticationRepository.getAuth());
			
			for(int i=0; i<contacts.size(); i++) {
				System.out.println("[" + (i+1) + "] " + contacts.get(i));
			}
			
			if(contacts.size() == 0) {
				System.out.println("Keine Kontakte vorhanden...");
			}
			
			consoleWait();
			
		} catch (Exception ex) {
			error("Fehler beim Abrufen der Kontakte: " + ex.getMessage());
		}
		
	}
	
	private static void showPersonsDialog() {
		
		System.out.println();
		System.out.println(menuLine);
		System.out.println("Kontaktpersonen:");
		System.out.println();
		
		try {
			List<Person> persons = contactRepository.getAllPersons(authenticationRepository.getAuth());
			
			for(int i=0; i<persons.size(); i++) {
				System.out.println("[" + (i+1) + "] " + persons.get(i));
			}
			
			if(persons.size() == 0) {
				System.out.println("Keine Personen vorhanden...");
			}
			
			consoleWait();
			
		} catch (Exception ex) {
			error("Fehler beim Abrufen der Kontaktpersonen: " + ex.getMessage());
		}
		
	}
	
	private static void showGroupsDialog() {
		
		System.out.println();
		System.out.println(menuLine);
		System.out.println("Kontaktgruppen:");
		System.out.println();
		
		try {
			List<Group> groups = contactRepository.getAllGroups();
			
			for(int i=0; i<groups.size(); i++) {
				System.out.println("[" + (i+1) + "] " + groups.get(i));
			}
			
			if(groups.size() == 0) {
				System.out.println("Keine Gruppen vorhanden...");
			}
			
			consoleWait();
			
		} catch (Exception ex) {
			error("Fehler beim Abrufen der Kontaktgruppen: " + ex.getMessage());
		}
		
	}
	
	private static void createGroupDialog() {
		
	}
	
	private static void editGroupMembersDialog() {
		
	}
	
	private static void removeGroupDialog() {
		
	}
		
	private static void error(String message) {
		
		System.out.println();
		System.out.println(errorLine);
		System.out.println(message);
		System.out.println(errorLine);
		System.out.println();
		
	}
	
	private static void consoleWait() {
		try {
			System.out.println();
			System.out.println("Beliebige Taste zum Fortfahren: ");
			System.in.read();
		} catch(IOException ex) {
			error("IOException: " + ex.getMessage());
		}
	}
	
	private static void exit() {
		
		System.out.println();
		System.out.println(menuLine);
		System.out.println();
		System.out.println("Bis zum nächsten Mal :)");
		System.out.println();
		System.out.println(menuLine);
		System.out.println();
		
		if(in != null) {
			in.close();
		}
		
		System.exit(0);
		
	}
	
}
