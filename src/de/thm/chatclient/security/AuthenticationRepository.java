package de.thm.chatclient.security;

public class AuthenticationRepository {
	
	private Authentication user;
	
	public void setUser(Authentication user) {
		this.user = user;
	}
	
	public Authentication getUser() {
		return this.user;
	}

}
