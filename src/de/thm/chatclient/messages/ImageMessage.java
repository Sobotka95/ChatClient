package de.thm.chatclient.messages;

/**
 * Image message
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public class ImageMessage extends Message {

	private Image image;

	public Image getImage() {
		return image;
	}
	
	@Override
	public String toString() {
		return "Bild Nachicht "
				+ super.toString() 
				+ image.toString();
	}

	//Getters and Setters
	public void setImage(Image image) {
		this.image = image;
	}	
}
