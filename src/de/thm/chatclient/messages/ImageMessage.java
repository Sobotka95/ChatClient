package de.thm.chatclient.messages;

public class ImageMessage extends Message {

	private Image image;
	
	public static ImageMessage Parse(String serialized) {
		return new ImageMessage();
	}

	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		if(getImage() instanceof ImageLink) {
			return super.toString() 
					+ "\t\t" 
					+ ((ImageLink)getImage()).getUrl() + "(" + ((ImageLink)getImage()).getMimeType() + ")"
					+ System.lineSeparator()
					+ System.lineSeparator()
					+ "-------------------------------------------------------------------------------------------";
		} else {
			return "ImageMessage [Wrong object type for casting]";
		}
	}
		
}
