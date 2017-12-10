package de.thm.chatclient.messages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import de.thm.chatclient.security.Authentication;
import de.thm.oop.chat.base.server.*;



public class MessageRepository {
	private static final String splitChar = "\\|";
	private static final String txt = "txt";
	private static final String img = "img";
	
	/**
	 * Sends a TextMessage or a ImageMessage
	 * @throws Exception 
	 */
	public void sendMessage(Authentication auth, Message message) throws Exception{
		
		if (message instanceof ImageMessage) {
			sendImageMessage(auth, (ImageMessage) message);
		}
		else if(message instanceof TextMessage) {
			//sendTextMessage
		}
		
	}
	
	/**
	 * gets all Messages of a user
	 * @param auth
	 * @param since
	 * @return
	 * @throws Exception
	 */
	public List<Message> getAllMessages(Authentication auth, long since) throws Exception {
		BasicTHMChatServer Server = new BasicTHMChatServer();
		
		ArrayList<Message> allMessages = new ArrayList<Message>();
		String[] messages = Server.getMessages(auth.getUsername(), auth.getPassword(), since);
		
		for(int i = 0 ; i < messages.length ; i++) {
			allMessages.add(parse(messages[i]));
		}
					
		return allMessages;
	}
	

	/**
	 * 
	 * @param inputString
	 * @return
	 */
	public Message parse(String inputString) {
		Message message = null;
		
		try {
			String parts[] = inputString.split(splitChar);
			
			if  (parts[3].equals(txt)) {
				TextMessage textMessage = new TextMessage(txt);
			
				textMessage.setText(parts[4]);
				
				message = textMessage;
			
			} else if (parts[3].equals(img)) {
				ImageMessage imageMessage = new ImageMessage();
				
				imageMessage.setImage(new ImageLink(parts[6], parts[4], parts[5])); 
				message = imageMessage;
			}
			
			message.setTimestamp(Long.parseLong(parts[0]));
			message.setDirection(parts[1]);
			message.setReceiver(parts[2]);
					
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return message; 
	}
	
	
	private void sendTextMessage(Authentication auth, TextMessage textMessage){
		//...
	}
	
	
	private void sendImageMessage(Authentication auth, ImageMessage imageMessage) throws Exception {
		BasicTHMChatServer Server = new BasicTHMChatServer();
		FileInputStream img; 
		try {
			img = new FileInputStream(((RawImage)imageMessage.getImage()).getFile());
			
			Server.sendImageMessage(auth.getUsername(), auth.getPassword(), imageMessage.getReceiver(), imageMessage.getImage().getType(),img);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			
		}
	}
}