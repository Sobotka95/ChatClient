package de.thm.chatclient.messages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import de.thm.chatclient.contacts.Contact;

/**
 * This is the upper class message.
 * @author Team_F (Julian S, Ralf M, Simon W)
 *
 */
public abstract class Message {

	private Contact transmitter;
	private Contact receiver;
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
				+ ", Sender: " + getTransmitter()
				+ ", Empfänger: " + getReceiver();
	}
	
	
	//Getters and Setters
	public Contact getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(Contact transmitter) {
		this.transmitter = transmitter;
	}

	public Contact getReceiver() {
		return receiver;
	}

	public void setReceiver(Contact receiver) {
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