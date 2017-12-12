package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

public class Person extends Contact {
	
	private List<Group> groups;

	public Person(String name) {
		super(name);
		this.groups = new ArrayList<Group>();
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public Group getGroupByName(String groupName) {
		for(int i=0; i<groups.size(); i++) {
			if(groups.get(i).getName().equals(groupName)) {
				groups.get(i);
			}
		}
		return null;
	}
	
	public void addGroup(Group group) throws Exception {
		if(getGroupByName(group.getName()) != null) {
			throw new Exception("Eine Gruppe mit diesem Namen existiert bereits");
		}	
		groups.add(group);
		group.getMembers().add(this);
	}
	
	public void removeGroup(String groupName) throws Exception {
		
		Group group = this.getGroupByName(groupName);
		
		if(group == null) {
			// Group with this name does not exist
			throw new Exception("Eine Gruppe mit diesem Name existiert nicht");
		}
		
		groups.remove(group);
		group.getMembers().remove(this);
		
	}
	
	@Override
	public String toString() {
		
		String nickname = "";
		String username = getName();
		
		username = username.replace('-', '.');
		username = username.replace("oe", "ö");
		username = username.replace("ae", "ä");
		username = username.replace("ue", "ü");
		
		for (String element : username.split("\\.")) {
			nickname += element.substring(0, 1).toUpperCase() + element.substring(1) + " ";
		}
		
		return "(Person) " + nickname.trim();
	}
	
}
