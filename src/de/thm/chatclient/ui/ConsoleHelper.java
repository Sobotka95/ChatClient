package de.thm.chatclient.ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleHelper {
	
	private static final String menuLine = "-------------------------------";
	private static final String errorLine = "- ! - ! - ! - ! - ! - ! - ! - ! -";
	
	private static Scanner in = new Scanner(System.in);
	
	public static int buildMenu(String title, String[] entries) {
		
		int choice = 0;
		boolean success = false;

		do {
			
			success = true;
			
			System.out.println();
			System.out.println(menuLine);
			System.out.println(title);
			System.out.println();
			
			for(int i=0; i<entries.length; i++) {
				System.out.println("[" + (i+1) + "] " + entries[i]);
			}
	
			System.out.println();
			
			try {
				
				System.out.print("Eingabe: ");
				choice = in.nextInt();
				
				if(choice < 1 || choice > 4) {
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

	public static void buildList(String title, String listname, String[] entries) {
		
	}
	
private static void error(String message) {
		
		System.out.println();
		System.out.println(errorLine);
		System.out.println(message);
		System.out.println(errorLine);
		System.out.println();
		
	}
	
	private static void sleep() {
		try {
			System.out.println();
			System.out.println("Beliebige Taste zum Fortfahren: ");
			System.in.read();
		} catch(IOException ex) {
			error("IOException: " + ex.getMessage());
		}
	}
	
}
