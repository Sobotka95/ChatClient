package de.thm.chatclient.ui;

import de.thm.chatclient.messages.*;

import java.awt.font.TextMeasurer;
import java.io.FileNotFoundException;
import java.util.Scanner;

import de.thm.chatclient.contacts.*;
import de.thm.chatclient.security.*;

public class UserInterface {
	
	static AuthenticationRepository authenticationRepository = new AuthenticationRepository();
	static MessageRepository messageRepository = new MessageRepository();

	public static void main(String[] args) throws Exception {
		login();
		//showAuth();

		/*
		TextMessage testMessage = new TextMessage("Nochmal Test");
		testMessage.setReceiver("simon.wand");
		messageRepository.sendMessage(authenticationRepository.getAuth(), testMessage);
		*/
		
		
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setReceiver("julian.sobotka");
		try {
			imageMessage.setImage(new RawImage("C:\\Users\\Merhof\\Desktop\\Test.bmp"));
			messageRepository.sendMessage(authenticationRepository.getAuth(), imageMessage);
		} catch (FileNotFoundException e) {
			System.out.println("Das Bild konnte nicht gefunden werden!");
		}
				
				
		for (Message message : messageRepository.getAllMessages(authenticationRepository.getAuth(), 1)){
			System.out.println(message.toString());
		}
	}
	
	
		
	
	private static void login() {
		System.out.println("Anmeldung");
		Scanner in = new Scanner(System.in);
		
		try {
			boolean loginSucceeded;
			
			do{
				System.out.print("Benutzername: ");
				String username = in.next();
				System.out.print("Passwort: ");
				String password = in.next();
				authenticationRepository.setAuth(new Authentication(username, password));
			
				// TODO: Check authentication
				loginSucceeded = authenticationRepository.loginCheck();
			
				if (!loginSucceeded) System.out.println("Unter den eigegeben Daten konnte kein Benutzer gefunden werden überprüfen sie die Eingabe und die Internet Verbindung.");
			}
			while (!loginSucceeded);
			
		} catch (Exception ex) {
			
		} finally {
			in.close();
		}
	}
}
