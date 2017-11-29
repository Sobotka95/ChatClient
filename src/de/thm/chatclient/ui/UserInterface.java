package de.thm.chatclient.ui;

import de.thm.chatclient.messages.*;

import java.util.Scanner;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	static AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	static MessageRepository messageRepository = new MessageRepository();

	public static void main(String[] args) {
		login();
		showAuth();
	}
	
	private static void showAuth() {
		System.out.println(authenticationRepository.getAuth());
	}
	
	private static void login() {
		
		System.out.println("Anmeldung");
		Scanner in = new Scanner(System.in);
		
		try {
			
			System.out.print("Benutzername: ");
			String username = in.next();
			System.out.print("Passwort: ");
			String password = in.next();
			authenticationRepository.setAuth(new Authentication(username, password));
			
			// TODO: Check authentication
			
		} catch (Exception ex) {
			
		} finally {
			in.close();
		}
		
	}

}
