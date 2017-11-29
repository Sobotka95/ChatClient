package de.thm.chatclient.messages;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public abstract class Message {

	private String sender;
	
	private String receiver;
	
	private long timestamp;
	
	private String direction;
	
	public static Message Parse(String serializedMessage) throws Exception {
		
		String splitChar = "\\|";
		
		String[] messageTypeTest = serializedMessage.split(splitChar);
		messageTypeTest.toString();
		String messageType = serializedMessage.split(splitChar)[3];
		
		if(messageType.equals("txt")) {
			
			return TextMessage.Parse(serializedMessage);
			
		} else if(messageType.equals("img")) {
			
			return ImageMessage.Parse(serializedMessage);
			
		} else {
			
			throw new IllegalArgumentException("Serialized Message has invalid values (message type)");
			
		}
		
	}
	
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
	
	public String getDateString() {
		Date date = new Date(getTimestamp());
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z"); // the format of your date
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+1")); // give a timezone reference for formating (see comment at the bottom
		return sdf.format(date);
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		if(getDirection().equals("in")) {
			return "-------------------------------------------------------------------------------------------"
					+ System.lineSeparator() 
					+ "Empfangen von " + getSender() + " am " + getDateString() + ") : "
					+ System.lineSeparator() 
					+ System.lineSeparator();
		} else {
			return "-------------------------------------------------------------------------------------------"
					+ System.lineSeparator()  
					+ "Gesendet an " + getReceiver() + " am " + getDateString() + ") : " 
					+ System.lineSeparator()
					+ System.lineSeparator();
		}
		
	}
	
}
