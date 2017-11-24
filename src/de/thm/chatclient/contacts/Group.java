package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

public class Group extends Contact {
	
	private List<User> members;

	public Group(String name) {
		super(name);
		this.members = new ArrayList<User>();
	}

	public List<User> getMembers() {
		return members;
	}
	
	public User getMemberByName(String memberName) {
		int index = -1;
		for(int i=0; i<members.size(); i++) {
			if(members.get(i).getName().equals(memberName)) {
				index = i;
			}
		}
		if(index != -1) {
			return members.get(index);
		} else {
			return null;
		}
	}
	
	public void addMember(User member) throws Exception {
		if(getMemberByName(member.getName()) != null) {
			throw new Exception("Member with this name already exists");
		}	
		members.add(member);
		member.getGroups().add(this);
	}
	
	public void removeMember(String memberName) throws Exception {
		
		User member = this.getMemberByName(memberName);
		
		if(member == null) {
			// Member with this name does not exist
			throw new Exception("A Member with this name does not exists");
		}
		
		members.remove(member);
		member.getGroups().remove(this);
		
	}

}
