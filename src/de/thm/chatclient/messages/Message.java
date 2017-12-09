package de.thm.chatclient.messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This is the upper class message.
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public abstract class Message {

	private String sender;
	private String receiver;
	private long timstamp;
	private String direction;
		

	public static String timstampConvert(long timestamp) {
		Date date = new Date(timestamp); 
		
		SimpleDateFormat sdf;
		sdf	= new SimpleDateFormat("HH:mm dd.MM.yyyy");
		sdf.setTimeZone(TimeZone.getDefault());
		
		return sdf.format(date);
	}
	
	@Override
	public String toString() {
		return  "Zeit: " + timstampConvert(getTimstamp()) 
				+ ", Richtung: " + getDirection() 
				+ ", Empfänger: " + getReceiver();
	}
	
	
	//Getters and Setters
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public long getTimstamp() {
		return timstamp;
	}

	public void setTimstamp(long timstamp) {
		this.timstamp = timstamp;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
}