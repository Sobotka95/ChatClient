package de.thm.chatclient.contacts;

import java.util.ArrayList;
import java.util.List;

public class Group extends Contact {
	
	private List<Person> members;

	public Group(String name) {
		super(name);
		this.members = new ArrayList<Person>();
	}

	public List<Person> getMembers() {
		return members;
	}
	
	public Person getMemberByName(String membername) {
		for(int i=0; i<members.size(); i++) {
			if(members.get(i).getName().equals(membername)) {
				return members.get(i);
			}
		}
		return null;
	}
	
	public void addMember(Person member) throws Exception {
		if(getMemberByName(member.getName()) != null) {
			throw new Exception("Die Person ist bereits Mitglied dieser Gruppe");
		}	
		members.add(member);
		member.getGroups().add(this);
	}
	
	public void removeMember(String membername) throws Exception {
		
		Person member = this.getMemberByName(membername);
		
		if(member == null) {
			// Member with this name does not exist
			throw new Exception("Die Person ist nicht Mitglied der Gruppe");
		}
		
		members.remove(member);
		member.getGroups().remove(this);
		
	}
	
	@Override
	public String toString() {
		return "(Gruppe) " + super.toString();
	}

}
