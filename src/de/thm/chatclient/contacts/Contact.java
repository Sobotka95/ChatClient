package de.thm.chatclient.contacts;

public abstract class Contact {
	
	private String name;

	public Contact(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
