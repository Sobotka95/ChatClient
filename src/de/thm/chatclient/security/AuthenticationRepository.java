package de.thm.chatclient.security;

public class AuthenticationRepository {
	
	private Authentication auth;
	
	public void setAuth(Authentication user) {
		this.auth = user;
	}
	
	public Authentication getAuth() {
		return this.auth;
	}

}
