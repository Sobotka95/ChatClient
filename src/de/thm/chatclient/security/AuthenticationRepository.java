package de.thm.chatclient.security;

public class AuthenticationRepository {
	
	private Authentication auth;
	
	public void setAuth(Authentication auth) {
		this.auth = auth;
	}
	
	public Authentication getAuth() {
		return this.auth;
	}

}
