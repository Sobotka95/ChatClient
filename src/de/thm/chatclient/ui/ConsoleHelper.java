package de.thm.chatclient.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.thm.chatclient.contacts.Person;
import de.thm.chatclient.security.Authentication;
import de.thm.chatclient.security.AuthenticationRepository;

public class ConsoleHelper {
	
	private static final String menuLine = "-------------------------------";
	private static final String errorLine = "- ! - ! - ! - ! - ! - ! - ! - ! -";
	
	private static Scanner in = new Scanner(System.in);
	
	public static int menu(String title, String[] entries) {
		
		int choice = 0;
		boolean success = false;
		
		do {
			
			success = true;
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println(title + " (Angemeldet als " + AuthenticationRepository.getInstance().getAuth().getUsername() + ")");
			System.out.println();
			
			for(int i=0; i<entries.length; i++) {
				System.out.println("[" + (i+1) + "] " + entries[i]);
			}
	
			System.out.println();
			
			try {
				
				System.out.print("Eingabe: ");
			
				choice = Integer.parseInt(in.next());
				
				if(choice < 1 || choice > entries.length) {
					throw new InputMismatchException();
				}
								
			} catch (InputMismatchException ex) {
				error("Ungültige Eingabe");	
				success = false;
			} catch (NoSuchElementException ex) {		
				error("NoSuchElementException: " + ex.getMessage());		
				success = false;
			} catch (IllegalStateException ex) {		
				error("IllegalStateException: " + ex.getMessage());		
				success = false;
			} catch (Exception ex) {		
				error("Exception: " + ex.getMessage());			
				success = false;
			}
			
		} while(!success);	
		
		return choice;		
	}
	
	public static int listChoice(String title, List<?> elements) {
		
		int choice = 0;
		boolean success = false;
		
		do {
			
			success = true;
			
			System.out.println();
			System.out.println(title);
			System.out.println();
			
			for(int i=0; i<elements.size(); i++) {
				System.out.println("[" + (i+1) + "] " + elements.get(i));
			}
			
			if(elements.isEmpty()) {
				System.out.println("Keine Elemente vorhanden...");
				sleep();
				return 0;
			}
	
			System.out.println();
			
			try {
				
				System.out.print("Eingabe: ");
			
				choice = Integer.parseInt(in.next());
				
				if(choice < 1 || choice > elements.size()) {
					throw new InputMismatchException();
				}
								
			} catch (InputMismatchException ex) {
				error("Ungültige Eingabe");	
				success = false;
			} catch (NoSuchElementException ex) {		
				error("NoSuchElementException: " + ex.getMessage());		
				success = false;
			} catch (IllegalStateException ex) {		
				error("IllegalStateException: " + ex.getMessage());		
				success = false;
			} catch (Exception ex) {		
				error("Exception: " + ex.getMessage());			
				success = false;
			}
			
		} while(!success);	
		
		return choice;		
		
	}
	
	public static void listView(String title, List<?> elements) {
		
		System.out.println();
		System.out.println(title);
		System.out.println();
	
		for(int i=0; i<elements.size(); i++) {
			System.out.println(elements.get(i));
		}
		
		if(elements.isEmpty()) {
			System.out.println("Keine Elemente vorhanden...");
		}

		System.out.println();
		
		sleep();
							
	}
	
	
	public static void login() {
		System.out.println("Anmeldung");
		
		try {
			boolean loginSucceeded;
			
			do{
				System.out.print("Benutzername: ");
				String username = in.next();
				System.out.print("Passwort: ");
				String password = in.next();
				AuthenticationRepository.getInstance().setAuth(new Authentication(username, password));
			
				loginSucceeded = AuthenticationRepository.getInstance().loginCheck();
			
				if (!loginSucceeded) System.out.println("Unter den eigegeben Daten konnte kein Benutzer gefunden werden überprüfen sie die Eingabe und die Internet Verbindung.");
			}
			while (!loginSucceeded);
			
		} catch (Exception ex) {
			
		}
	}
	
	public static void error(String message) {
		
		System.out.println();
		System.out.println(errorLine);
		System.out.println(message);
		System.out.println(errorLine);
		System.out.println();
		
	}
	
	public static void sleep() {
		try {
			System.out.println();
			System.out.println("Beliebige Taste zum Fortfahren...");
			System.in.read();
		} catch(IOException ex) {
			error("IOException: " + ex.getMessage());
		}
	}
	
}
