package de.thm.chatclient.messages;

import java.util.Date;

public abstract class Message {

	
	private String sender;
	private String receiver;
	private String direction;
	private long timestamp;
		
	/**
	 * converts the timestamp into a Date format.
	 * @param timestamp
	 * @return
	 */
	public static Date convertTimestamp(long timestamp) {
		
		Date date = new Date(timestamp); 
		
		return date;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
}