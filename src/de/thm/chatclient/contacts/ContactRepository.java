package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

import de.thm.chatclient.security.Authentication;
import de.thm.oop.chat.base.server.*;

public class ContactRepository implements ContactRepositoryInterface {
	
	BasicTHMChatServer basicTHMChatServer;
	
	private List<Group> groups;
	
	public ContactRepository() {
		basicTHMChatServer = new BasicTHMChatServer();
		groups = new ArrayList<Group>();
	}
	
	public List<Contact> getAllContacts(Authentication auth) throws Exception {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.addAll(getAllGroups());
		contacts.addAll(getAllPersons(auth));
		return contacts;
	}
	
	public List<Person> getAllPersons(Authentication auth) throws Exception {
		List<Person> persons = new ArrayList<Person>();
		for(String name: basicTHMChatServer.getUsers(auth.getUsername(), auth.getPassword())) {
			persons.add(new Person(name));
		}
		return persons;
	}
	
	public Person getPersonByName(Authentication auth, String name) throws Exception {
		List<Person> persons = getAllPersons(auth);
		int index = -1;
		for(int i=0; i<persons.size(); i++) {
			if(persons.get(i).getName().equals(name)) {
				index = i;
			}
		}
		if(index != -1) {
			return persons.get(index);
		} else {
			return null;
		}
	}
	
	public List<Group> getAllGroups() {
		return groups;
	}
	
	public Group getGroupByName(String name) {
		int index = -1;
		for(int i=0; i<groups.size(); i++) {
			if(groups.get(i).getName().equals(name)) {
				index = i;
			}
		}
		if(index != -1) {
			return groups.get(index);
		} else {
			return null;
		}
	}
	
	public void removeGroup(String name) throws Exception {
		
		Group group = this.getGroupByName(name);
		
		if(group == null) {
			// Group with this name does not exist
			throw new Exception("A Group with this name does not exists");
		}
		
		groups.remove(group);
	}
	
	public void addGroup(Group group) throws Exception {
		
		if(this.getGroupByName(group.getName()) != null) {
			// Group with this name already exists
			throw new Exception("A Group with this name already exists");
		}
		groups.add(group);
	}
	
}
