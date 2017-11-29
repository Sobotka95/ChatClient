package de.thm.chatclient.messages;

import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.security.Authentication;
import de.thm.oop.chat.base.server.*;

public class MessageRepository {
	
	public void sendMessage(Authentication auth, Message message) throws Exception {
		
	}
	
	public List<Message> getAllMessages(Authentication auth, long since) {
		
		return new ArrayList<Message>();
	}

}
