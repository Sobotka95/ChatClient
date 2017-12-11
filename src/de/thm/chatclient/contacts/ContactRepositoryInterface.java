package de.thm.chatclient.contacts;

import java.util.List;

import de.thm.chatclient.security.Authentication;

public interface ContactRepositoryInterface {
	
	public List<Contact> getAllContacts(Authentication auth) throws Exception;
	
	public List<Person> getAllPersons(Authentication auth) throws Exception;
	
	public Person getPersonByName(Authentication auth, String name) throws Exception;
	
	public List<Group> getAllGroups();
	
	public Group getGroupByName(String name);
	
	public void removeGroup(String name) throws Exception;
	
	public void addGroup(Group group) throws Exception;

}
