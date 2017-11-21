package de.thm.chatclient.ui.fa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.text.Font;

public class FontAwesome {
	
	public static enum ICON {
		REFRESH,
		USER_PLUS,
		GROUP,
		WECHAT,
		PLUS
	}
	
	private Font font;
	
	public static String resolveIconToString(FontAwesome.ICON icon) {
		switch(icon) {
			case REFRESH: {
				return "\uf021";
			}
			case USER_PLUS: {
				return "\uf234";
			}
			case GROUP: {
				return "\uf0c0";
			}
			case WECHAT: {
				return "\uf1d7";
			}
			case PLUS: {
				return "\uf067";
			}
			default: {
				return "";
			}
		}
	}
	
	public FontAwesome() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("fontawesome-webfont.ttf");
			font = Font.loadFont(inputStream, 10);
			inputStream.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
	}
	
	public Font getFont() {
		return this.font;
	}
	
}
