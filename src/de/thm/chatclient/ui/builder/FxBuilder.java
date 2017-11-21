package de.thm.chatclient.ui.builder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import de.thm.chatclient.ui.fa.*;

public class FxBuilder {
	
	private FontAwesome fontAwesome;
	
	public FxBuilder() {
		fontAwesome = new FontAwesome();
	}
	
	/**
	 * Create Button
	 * @return
	 */
	public Button createButton() {
		Button button = new Button(); 
		return button;
	}
	
	/**
	 *  Create Button Overload 1
	 * @param text
	 * @return
	 */
	public Button createButton(String text) {
		Button button = new Button(text); 
		return button;
	}
	
	/**
	 * Create Button Overload 2
	 * @param text
	 * @param size
	 * @return
	 */
	public Button createButton(String text, double size) {
		Button button = new Button(text);
		button.setMinSize(size, size);
		button.setStyle("-fx-font-size: " + size + "em;");
		return button;
	}
	
	/**
	 * Create Button Overload 3
	 * @param icon
	 * @param text
	 * @return
	 */
	public Button createButton(FontAwesome.ICON icon, String text) {
		Button button;
		if(text.length() == 0) {
			 button = new Button(FontAwesome.resolveIconToString(icon));
		} else {
			 button = new Button(FontAwesome.resolveIconToString(icon) + " " + text);
		}
		button.setFont(fontAwesome.getFont());
		button.setStyle("-fx-font-family: 'FontAwesome'");
		return button;
	}
	
	/**
	 * Create Button Overload 4
	 * @param icon
	 * @param text
	 * @param size
	 * @return
	 */
	public Button createButton(FontAwesome.ICON icon, String text, int size) {
		Button button; 
		if(text.length() == 0) {
			 button = new Button(FontAwesome.resolveIconToString(icon));
		} else {
			 button = new Button(FontAwesome.resolveIconToString(icon) + " " + text);
		}
		//button.setMinSize(size, size);
		button.setFont(fontAwesome.getFont());
		button.setStyle("-fx-font-family: 'FontAwesome'; -fx-font-size: " + size + "em;");
		return button;
	}
	
	

}
