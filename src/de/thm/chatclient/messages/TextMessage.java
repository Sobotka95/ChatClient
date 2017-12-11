package de.thm.chatclient.messages;

/**
 * Text message
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public class TextMessage extends Message {
	private String text;
	
	public TextMessage () {}
	
	public TextMessage (String text) {
		this.text = text;
	}
				
	@Override
	public String toString() {
		return "Text Nachicht "
				+ super.toString()
				+ ", Nachichten inhalt: " + getText();
	}

	//Getters and Setters
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
