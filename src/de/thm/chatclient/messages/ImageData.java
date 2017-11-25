package de.thm.chatclient.messages;

import java.io.InputStream;

public class ImageData extends Image {
	
	private InputStream dataStream;

	public InputStream getDataStream() {
		return dataStream;
	}

	public void setDataStream(InputStream dataStream) {
		this.dataStream = dataStream;
	}

}
