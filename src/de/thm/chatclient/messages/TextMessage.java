package de.thm.chatclient.messages;

import de.thm.chatclient.security.AuthenticationRepository;

public class TextMessage extends Message {
	
	private String text;
	
	public static TextMessage Parse(String serializedTextMessage) throws Exception {

		String splitChar = "\\|";
		
		String[] messageElements = serializedTextMessage.split(splitChar);
		
		TextMessage textMessage = new TextMessage();
		
		textMessage.setTimestamp(Long.parseLong(messageElements[0]));
		textMessage.setDirection(messageElements[1]);
		
		if(textMessage.getDirection().equals("in")) {
			textMessage.setReceiver(AuthenticationRepository.getInstance().getAuth().getUsername());
			textMessage.setSender(messageElements[2]);
		} else if(textMessage.getDirection().equals("out")) {
			textMessage.setReceiver(messageElements[2]);
			textMessage.setSender(AuthenticationRepository.getInstance().getAuth().getUsername());
		} else {
			throw new IllegalArgumentException("Serialized Message has invalid values (direction)");
		}
		
		textMessage.setText(messageElements[4]);
		
		return textMessage;
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return super.toString() 
				+ "\t\t" 
				+ getText()
				+ System.lineSeparator()
				+ System.lineSeparator()
				+ "-------------------------------------------------------------------------------------------";
	}	

}
