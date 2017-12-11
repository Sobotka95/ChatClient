package de.thm.chatclient.security;

import de.thm.oop.chat.base.server.*;

public class AuthenticationRepository {
	
	private static AuthenticationRepository instance;
	
	private Authentication auth;
	
	public static AuthenticationRepository getInstance() {
		if(instance == null) {
			instance = new AuthenticationRepository();
		}
		return instance;
	}
	
	/**
	 * Function checks the connection to the server with the given parameters.
	 * @return "true" if the connection is correct.
	 */
	public boolean loginCheck() {
		boolean isOk = true;
		
		BasicTHMChatServer thmServer = new BasicTHMChatServer();

		try {
			thmServer.getUsers(auth.getUsername(), auth.getPassword());
		} catch (java.io.IOException e) {
			isOk = false;
		}
		return  isOk;
	}
	
	
	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
	
	public Authentication getAuth() {
		return this.auth;
	}

}
