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
	
	private static final String seperationLine = "-------------------------------";
	private static final String errorLine = "- ! - ! - ! - ! - ! - ! - ! - ! -";
	
	private static AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	private static ContactRepository contactRepository = new ContactRepository();
	private static MessageRepository messageRepository = new MessageRepository();
	
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		loginDialog();
		menuDialog();
	}
	
	private static void menuDialog() {
		
		boolean exit = false;

		do {
			
			System.out.println();
			System.out.println(seperationLine);
			System.out.println("Hauptmenü (Angemeldet als: " + authenticationRepository.getAuth().getUsername() + ")");
			System.out.println();
			System.out.println("[1] Nachricht schreiben");
			System.out.println("[2] Nachrichten abrufen");
			System.out.println("[3] Kontakte verwalten");
			System.out.println("[4] Neu anmelden");
			System.out.println("[5] Anwendung beenden");
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
						newMessageDialog();
					} break;
					case 2: {
						showMessagesDialog();
					} break;
					case 3: {
						manageContactsDialog();	
					} break;
					case 4: {
						loginDialog();
					} break;
					case 5: {
						exit = exitDialog();
					} break;
				}
						
			} catch (InputMismatchException ex) {
				
				showError("Ungültige Eingabe");
						
			} catch (NoSuchElementException ex) {
					
				showError("NoSuchElementException: " + ex.getMessage());
					
			} catch (IllegalStateException ex) {
					
				showError("IllegalStateException: " + ex.getMessage());
					
			} catch (Exception ex) {
					
				showError("Exception: " + ex.getMessage());
					
			}
			
		} while(!exit);
		
		exit();
		
	}

	private static void newMessageDialog() {
		
		System.out.println();
		System.out.println(seperationLine);
		System.out.println("Nachricht schreiben");
		System.out.println();
		
		try {
			
			List<Person> contactPersons = contactRepository.getAllPersons(authenticationRepository.getAuth());
			
			System.out.println("Kontaktpersonen:");
			System.out.println();
			
			for(int i=0; i<contactPersons.size(); i++) {
				System.out.println("[" + (i+1) + "] " + contactPersons.get(i));
			}
			
			System.out.println();
			System.out.println("Bitte wählen Sie Ihren gewünschten Chatpartner aus:");
			System.out.println();
			
			int choice = 0;
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();

				if(choice < 1  || choice > contactPersons.size()) {
					throw new InputMismatchException();
				}				
						
			} catch (InputMismatchException ex) {			
				showError("Ungültige Eingabe");					
			} catch (NoSuchElementException ex) {					
				showError("NoSuchElementException: " + ex.getMessage());				
			} catch (IllegalStateException ex) {			
				showError("IllegalStateException: " + ex.getMessage());			
			} catch (Exception ex) {				
				showError("Exception: " + ex.getMessage());				
			}
			
			Person chatPartner = contactPersons.get(choice - 1);
			System.out.println("Ausgewählter Chatpartner: " + chatPartner);
			System.out.println();
			
		} catch (Exception ex) {	
			showError("Fehler beim Laden der Kontakte: " + ex.getMessage());
		}
		
	}
	
	private static void showMessagesDialog() {
		
	}
	
	private static void manageContactsDialog() {
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
				showError("Ungültige Eingabe");		
			} catch (NoSuchElementException ex) {	
				error = true;
				showError("NoSuchElementException: " + ex.getMessage());	
			} catch (IllegalStateException ex) {	
				error = true;
				showError("IllegalStateException: " + ex.getMessage());		
			} catch (Exception ex) {
				error = true;
				showError("Exception: " + ex.getMessage());			
			}
			
		} while(error);
		
	}
	
	private static boolean exitDialog() {
		
		boolean exit = false;
		boolean error = false;
		
		do {
			
			System.out.println();
			System.out.println(seperationLine);
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
				showError("Ungültige Eingabe");							
			} catch (NoSuchElementException ex) {		
				error = true;	
				showError("NoSuchElementException: " + ex.getMessage());	
			} catch (IllegalStateException ex) {		
				error = true;
				showError("IllegalStateException: " + ex.getMessage());					
			} catch (Exception ex) {		
				error = true;	
				showError("Exception: " + ex.getMessage());				
			}
			
		} while(error);
		
		return exit;
		
	}

	private static void exit() {
		
		System.out.println();
		System.out.println(seperationLine);
		System.out.println();
		System.out.println("Bis zum nächsten Mal :)");
		System.out.println();
		System.out.println(seperationLine);
		System.out.println();
		
		if(in != null) {
			in.close();
		}
		
		System.exit(0);
		
	}
	
	private static void showError(String message) {
		
		System.out.println();
		System.out.println(errorLine);
		System.out.println(message);
		System.out.println(errorLine);
		System.out.println();
		
	}
	
}
