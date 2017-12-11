package de.thm.chatclient.messages;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public class RawImage extends Image {
	private static final String SEPERATOR = "\\.";
	private File file;

	public RawImage (String path) throws FileNotFoundException{
		setPath(path);
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ ", Pfad" + file.getPath();
	}
	
	/**
	 * Set the path of image and check if it exists.
	 * @param path
	 * @throws FileNotFoundException
	 */
	public void setPath(String path) throws FileNotFoundException {
		file = new File(path);
		
		if (file.exists()) {
			String parts[] = path.split(SEPERATOR);
			super.setMimeType(parts[parts.length - 1]);
		} else {
			file = null;
			throw new FileNotFoundException();
		}
	}
	
	//Getters and Setters
	public File getFile() {
		return file;
	}
}
