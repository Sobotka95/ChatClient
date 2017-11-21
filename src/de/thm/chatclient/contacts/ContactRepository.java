package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

import de.thm.oop.chat.base.server.*;

public class ContactRepository {
	
	BasicTHMChatServer basicTHMChatServer;
	
	private List<Group> groups;
	
	public ContactRepository() {
		basicTHMChatServer = new BasicTHMChatServer();
		groups = new ArrayList<Group>();
	}
	
	public List<User> getAllUsers(String username, String password) throws Exception {
		List<User> users = new ArrayList<User>();
		for(String name: basicTHMChatServer.getUsers(username, password)) {
			users.add(new User(name));
		}
		return users;
	}
	
	public List<Group> getAllGroups() {
		return groups;
	}
	
	public Group getGroupByName(String groupName) {
		int index = -1;
		for(int i=0; i<groups.size(); i++) {
			if(groups.get(i).getName().equals(groupName)) {
				index = i;
			}
		}
		if(index != -1) {
			return groups.get(index);
		} else {
			return null;
		}
	}
	
	public void removeGroup(String groupName) throws Exception {
		
		Group group = this.getGroupByName(groupName);
		
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
