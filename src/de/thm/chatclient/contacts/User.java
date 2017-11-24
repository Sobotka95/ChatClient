package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

public class User extends Contact {
	
	private List<Group> groups;

	public User(String name) {
		super(name);
		this.groups = new ArrayList<Group>();
	}
	
	public List<Group> getGroups() {
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
	
	public void addGroup(Group group) throws Exception {
		if(getGroupByName(group.getName()) != null) {
			throw new Exception("Group with this name already exists");
		}	
		groups.add(group);
		group.getMembers().add(this);
	}
	
	public void removeGroup(String groupName) throws Exception {
		
		Group group = this.getGroupByName(groupName);
		
		if(group == null) {
			// Member with this name does not exist
			throw new Exception("A Group with this name does not exists");
		}
		
		groups.remove(group);
		group.getMembers().remove(this);
		
	}
	
}
