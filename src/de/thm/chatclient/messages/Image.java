package de.thm.chatclient.messages;

/**
 * This is the upper class image.
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public abstract class Image {
	
	private String mimeType;
	
	
	@Override
	public String toString() {
		return ", Typ: " + getMimeType();
	}
	
	//Getters and Setters
	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
