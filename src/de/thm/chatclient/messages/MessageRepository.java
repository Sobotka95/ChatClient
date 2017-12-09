package de.thm.chatclient.messages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.thm.chatclient.security.Authentication;
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
	
	/**
	 * Sends a message.
	 * @param auth 
	 * @param message 
	 * @throws Exception
	 */
	public void sendMessage(Authentication auth, Message message) throws Exception {
		
		if (message instanceof TextMessage) {
			sendTextMessage(auth, (TextMessage) message); 		
		} else if (message instanceof ImageMessage) {
			sendImageMessage(auth, (ImageMessage) message);
		}
	}
	
	
	public List<Message> getAllMessages(Authentication auth, long since) throws Exception {
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		
		ArrayList<Message> parsedMessenges = new ArrayList<Message>();
		String[] messages = thmServer.getMessages(auth.getUsername(), auth.getPassword(), since);
		
		for (String message : messages) {
			parsedMessenges.add(parse(message));
		}
					
		return parsedMessenges;
	}
	

	
	public Message parse(String inputString) {
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
			
			message.setTimstamp(Long.parseLong(parts[0]));
			message.setDirection(parts[1]);
			message.setReceiver(parts[2]);
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return message; 
	}
	
	
	private void sendTextMessage(Authentication auth, TextMessage textMessage) throws IOException, IllegalArgumentException {
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		
		thmServer.sendTextMessage(auth.getUsername(), 
									auth.getPassword(), 
									textMessage.getReceiver(), 
									textMessage.getText());
	}
	
	
	private void sendImageMessage(Authentication auth, ImageMessage imageMessage) throws Exception {
		BasicTHMChatServer thmServer = new BasicTHMChatServer();
		FileInputStream data; 
		try {
			data = new FileInputStream(((RawImage)imageMessage.getImage()).getFile());
			
			thmServer.sendImageMessage(auth.getUsername(), auth.getPassword(), 
										imageMessage.getReceiver(), imageMessage.getImage().getMimeType(),
										data);
			data.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			//Noch was machen
		}
	}
}
