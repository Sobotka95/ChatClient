package de.thm.chatclient.messages;

import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.contacts.Contact;
import de.thm.chatclient.contacts.Group;
import de.thm.chatclient.contacts.User;
import de.thm.chatclient.security.Authentication;
import de.thm.oop.chat.base.server.*;

public class MessageRepository {
	
	private static MessageRepository instance;
	
	public static MessageRepository getInstance() {
		
		if(instance == null) {
			instance = new MessageRepository();		
		}
		
		return instance;
		
	}
	
	public void sendMessage(Authentication auth, Message message) throws Exception {
		
		if(message instanceof TextMessage) {
			
			sendTextMessage(auth, (TextMessage)message);
			
		} else if(message instanceof ImageMessage) {
			
			sendImageMessage(auth, (ImageMessage)message);
			
		}
		
	}

	public List<Message> getAllMessages(Authentication auth, long since) throws Exception {
		
		BasicTHMChatServer basicTHMChatServer = new BasicTHMChatServer();
		
		List<Message> messages = new ArrayList<Message>();
	
		String[] serializedMessages = basicTHMChatServer.getMessages(
				auth.getUsername(),
                	auth.getPassword(),
                	since
        );
		
		for(String serializedMessage: serializedMessages) {
			
			try {
				messages.add(Message.Parse(serializedMessage));
			} catch(Exception ex) {
				System.out.println("One message could not be parsed, error:" + ex.getMessage());
			}			
					
		}
		
		return messages;
	}
	
	private void sendTextMessage(Authentication auth, TextMessage textMessage) throws Exception {
		
		BasicTHMChatServer basicTHMChatServer = new BasicTHMChatServer();
			
		basicTHMChatServer.sendTextMessage(
				auth.getUsername(), 
				auth.getPassword(), 
				textMessage.getReceiver(), 
				textMessage.getText()
		);
		
		/*if(receiver instanceof Group) { // Group message
			
			for(User member: ((Group)receiver).getMembers()) {
				
				basicTHMChatServer.sendTextMessage(
						auth.getUsername(), 
						auth.getPassword(), 
						member.getName(), 
						textMessage.getText()
				);
				
			}
				
		} else if(receiver instanceof User) { // User message
			
			basicTHMChatServer.sendTextMessage(
					auth.getUsername(), 
					auth.getPassword(), 
					((User)receiver).getName(), 
					textMessage.getText()
			);
			
		} else { // Illegal instanceof result of receiver
			
			throw new IllegalArgumentException("Invalid Object Type of receiver");
			
		}*/
		
	}
	
	private void sendImageMessage(Authentication auth, ImageMessage imageMessage) throws Exception {
		
		// First of all check the image object type and throw an error if it has the wrong type
		// This check is very important for safe casting of the image data
		/*if(!(imageMessage.getImage() instanceof ImageData)) { 
			throw new IllegalArgumentException("Invalid Object Type of image");			
		}
		
		
		BasicTHMChatServer basicTHMChatServer = new BasicTHMChatServer();		
		
		if(receiver instanceof Group) { // Group message
			
			for(User member: ((Group)receiver).getMembers()) {
				
				basicTHMChatServer.sendImageMessage(
						auth.getUsername(), 
						auth.getPassword(), 
						member.getName(), 
						imageMessage.getImage().getMimeType(), 
						((ImageData)imageMessage.getImage()).getDataStream()
				);
				
			}
				
		} else if(receiver instanceof User) { // User message
			
			basicTHMChatServer.sendImageMessage(
					auth.getUsername(), 
					auth.getPassword(), 
					((User)receiver).getName(), 
					imageMessage.getImage().getMimeType(), 
					((ImageData)imageMessage.getImage()).getDataStream()
			);
			
		} else { // Illegal instanceof result of receiver
			
			throw new IllegalArgumentException("Invalid Object Type of receiver");
			
		}*/
		
	}

}
