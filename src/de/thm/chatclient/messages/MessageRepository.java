package de.thm.chatclient.messages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.contacts.Contact;
import de.thm.chatclient.contacts.ContactRepository;
import de.thm.chatclient.contacts.Group;
import de.thm.chatclient.contacts.Person;
import de.thm.chatclient.security.Authentication;
import de.thm.chatclient.security.AuthenticationRepository;
import de.thm.oop.chat.base.server.*;

/**
 * Communication with server and parsing of messages.
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public class MessageRepository {
	
	private static final String SEPERATOR = "\\|";
	private static final String TEXT_MG = "txt";
	private static final String IMAGE_MG = "img";
	private static final String DIRECTION_IN = "in";
	private static final String DIRECTION_OUT = "out";
	private static final long TIMESTAMP = 1;
	
	private static MessageRepository instance;
	
	
	public static MessageRepository getInstance() {
		if(instance == null) {
			instance = new MessageRepository();
		}
		return instance;
	}
	
	/**
	 * Sends a message.
	 * @param auth 
	 * @param message 
	 * @throws Exception
	 */
	public void sendMessage(Authentication auth, Message message) throws Exception {
		

		
		if(message.getReceiver() instanceof Group) {
			
			for(Person member: ((Group)message.getReceiver()).getMembers()) {
				
				sendSingleMessage(auth, member, message);
				
			}
			
		} else {
			
			sendSingleMessage(auth, (Person)message.getReceiver(), message);
		}
		
		
	}
	
	public void sendSingleMessage(Authentication auth, Person receiver, Message message) throws Exception {
		
		if (message instanceof TextMessage) {
			sendTextMessage(auth, receiver.getName(), ((TextMessage) message).getText()); 		
		} else if (message instanceof ImageMessage) {
			sendImageMessage(auth, receiver.getName(), (RawImage)((ImageMessage) message).getImage());
		}
		
	}
	
	public List<Message> getAllMessages(Authentication auth) throws Exception {
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		
		ArrayList<Message> parsedMessenges = new ArrayList<Message>();
		String[] messages = thmServer.getMessages(auth.getUsername(), auth.getPassword(), TIMESTAMP);
		
		for (String message : messages) {
			parsedMessenges.add(parse(auth, message));
		}
					
		return parsedMessenges;
	}
	
	public List<Message> getMessagesByPerson(Authentication auth, String name) throws Exception {
		List<Message> allMessages = getAllMessages(auth);
		List<Message> userMessages = new ArrayList<Message>();
		for(int i=0; i<allMessages.size(); i++) {
			if(allMessages.get(i).getReceiver().getName().equals(name) || allMessages.get(i).getTransmitter().getName().equals(name)) {
				userMessages.add(allMessages.get(i));
			}
		}
		return userMessages;
	}
	
	private Message parse(Authentication auth, String inputString) {
		Message message = null;
		
		try {
			String parts[] = inputString.split(SEPERATOR);
			
			if  (parts[3].equals(TEXT_MG)) {
				TextMessage textMessage = new TextMessage();
			
				textMessage.setText(parts[4]);
				
				message = textMessage;
			
			} else if (parts[3].equals(IMAGE_MG)) {
				ImageMessage imageMessage = new ImageMessage();
				
				imageMessage.setImage(new ImageLink(parts[6], parts[4], parts[5])); 
				message = imageMessage;
			}
			
			message.setTimestamp(Long.parseLong(parts[0]));
			message.setDirection(parts[1]);
			
			Contact contact = ContactRepository
					.getInstance()
						.getPersonByName(
										auth, 
										parts[2]);
			if(contact == null) {
				contact = new Person(parts[2]);
			}

			if(message.getDirection().equals(DIRECTION_IN)) {
				message.setTransmitter(contact);
				message.setReceiver(ContactRepository
					.getInstance()
						.getPersonByName(
										auth, 
										auth.getUsername()));
			} else if (message.getDirection().equals(DIRECTION_OUT)) {
				message.setTransmitter(ContactRepository
						.getInstance()
							.getPersonByName(
											auth, 
											auth.getUsername()));
				message.setReceiver(contact);
			}
			
			
			
			
					
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return message; 
	}
	
	private void sendTextMessage(Authentication auth, String receiver, String text) throws IOException, IllegalArgumentException {
		

		
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		
		thmServer.sendTextMessage(auth.getUsername(), 
									auth.getPassword(), 
									receiver, 
									text);
	}
	
	private void sendImageMessage(Authentication auth, String receiver, RawImage image) throws Exception {
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		FileInputStream data; 
		try {
			data = new FileInputStream(image.getFile());
			
			thmServer.sendImageMessage(auth.getUsername(), auth.getPassword(), 
										receiver, image.getMimeType(),
										data);
			
			data.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			//Noch was machen
		}
	}
	
}
