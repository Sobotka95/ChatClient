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
				choice = Integer.parseInt(in.next() + in.nextLine());
				System.out.println();
				
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
			}
			
			System.out.println("[" + (elements.size() + 1) + "] Abbrechen");
			System.out.println();
			
			try {
				
				System.out.print("Eingabe: ");
				choice = Integer.parseInt(in.next() + in.nextLine());
				System.out.println();
				
				// input validation
				if(choice < 1 || choice > elements.size() + 1) {
					throw new InputMismatchException();
				}
				
				// Check cancel element
				if(choice == elements.size() + 1) { 
					choice = 0;
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
	
	public static String enterString(String description) {
		
		String input = "";
		try {
			System.out.print(description + ": ");
			input = in.next() + in.nextLine();
			System.out.println();
		} catch (Exception ex) {
			error(ex.getMessage());
		} finally {
			return input;
		}
		
	}
	
	public static void login() {
		
		System.out.println("Anmeldung");
		System.out.println();
		
		try {
			boolean loginSucceeded;
			
			do{
				System.out.print("Benutzername: ");
				String username = in.next() + in.nextLine();
				System.out.print("Passwort: ");
				String password = in.next() + in.nextLine();
				AuthenticationRepository.getInstance().setAuth(new Authentication(username, password));
			
				loginSucceeded = AuthenticationRepository.getInstance().loginCheck();
			
				if (!loginSucceeded) System.out.println("Unter den eigegeben Daten konnte kein Benutzer gefunden werden �berpr�fen sie die Eingabe und die Internet Verbindung.");
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
	
	public static boolean exit() {
		
		boolean exit = false;
		boolean success = false;
		
		do {
			
			success = true;		
				
			try {
				
				System.out.println("Wollen Sie wirklich das Programm beenden?");
				System.out.println();
				System.out.print("[J]a oder [N]ein : ");
				
				String input = in.next() + in.nextLine();
				
				if(input.toLowerCase().equals("j")) {
					exit = true;
				} else if(input.toLowerCase().equals("n")) {
					exit = false;
				} else {
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
		
		return exit;
		
	}
	
}
