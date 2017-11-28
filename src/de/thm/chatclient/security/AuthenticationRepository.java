package de.thm.chatclient.security;

import de.thm.chatclient.contacts.ContactRepository;

public class AuthenticationRepository {
	
	// Singleton instance
	private static AuthenticationRepository instance;
	
	private Authentication auth;
	
	public static AuthenticationRepository getInstance() {
		
		if(instance == null) {
			instance = new AuthenticationRepository();		
		}
		
		return instance;
		
	}
	
	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
	
	public Authentication getAuth() {
		return this.auth;
	}

}
