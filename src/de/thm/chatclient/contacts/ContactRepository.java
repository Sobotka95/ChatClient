package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.security.Authentication;
import de.thm.oop.chat.base.server.*;

public class ContactRepository {
	
	private static ContactRepository instance;
	
	BasicTHMChatServer basicTHMChatServer;
	
	private List<Group> groups;
	
	private List<Person> persons;
	
	public static ContactRepository getInstance() {
		if(instance == null) {
			instance = new ContactRepository();
		}
		return instance;
	}
	
	private ContactRepository() {
		basicTHMChatServer = new BasicTHMChatServer();
		groups = new ArrayList<Group>();
	}
	
	public List<Contact> getAllContacts(Authentication auth) throws Exception {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.addAll(getAllPersons(auth));
		contacts.addAll(getAllGroups());	
		return contacts;
	}
	
	public List<Person> getAllPersons(Authentication auth) throws Exception {
		
		if(persons == null) {
			persons = new ArrayList<Person>();
			for(String name: basicTHMChatServer.getUsers(auth.getUsername(), auth.getPassword())) {
				persons.add(new Person(name));
			}
		}
		return persons;
	}
	
	public Person getPersonByName(Authentication auth, String name) throws Exception {
		List<Person> persons = getAllPersons(auth);
		for(int i=0; i<persons.size(); i++) {
			if(persons.get(i).getName().equals(name)) {
				return persons.get(i);
			}
		}
		return null;
	}
	
	public List<Group> getAllGroups() {
		return groups;
	}
	
	public Group getGroupByName(String name) {
		for(int i=0; i<groups.size(); i++) {
			if(groups.get(i).getName().equals(name)) {
				return groups.get(i);
			}
		}
		return null;
	}
	
	public void removeGroup(String name) throws Exception {
		
		Group group = this.getGroupByName(name);
		
		if(group == null) {
			// Group with this name does not exist
			throw new Exception("Eine Gruppe mit diesem Namen existiert nicht");
		}
		
		groups.remove(group);
	}
	
	public void addGroup(Group group) throws Exception {
		
		if(this.getGroupByName(group.getName()) != null) {
			// Group with this name already exists
			throw new Exception("Eine Gruppe mit diesem Namen existiert bereits");
		}
		groups.add(group);
	}
	
}
