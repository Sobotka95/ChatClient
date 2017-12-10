package de.thm.chatclient.messages;

import java.io.File;

public class RawImage extends Image {
	
	private File f;
	private String splitChar = "//.";
	
	public void setPath(String path) {
		f = new File(path);
		
		if(f.exists()) {
			String parts[] = path.split(splitChar);
			super.setType(parts[parts.length - 1]);
		
		} else {
			f = null;
			
		}
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return f;
	}


	
}