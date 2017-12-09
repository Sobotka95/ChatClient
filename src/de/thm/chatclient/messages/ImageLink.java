package de.thm.chatclient.messages;

/**
 * Administer the link from where the picture can be found.
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public class ImageLink extends Image {
	
	private String url;
	private String size;
	public ImageLink () {}
	
	public ImageLink (String url, String mimeType, String size) {
		setUrl(url);
		super.setMimeType(mimeType);
		setSize(size);
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ ", Größe: " + getSize()
				+ ", URL: " + getUrl();
	}
	
	//Getters and Setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
