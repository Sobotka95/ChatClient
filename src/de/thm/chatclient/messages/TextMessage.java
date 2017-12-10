package de.thm.chatclient.messages;

public class TextMessage extends Message {
	private String text;
	
	
	public TextMessage (String text) {
		this.text = text;
	}
				
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
