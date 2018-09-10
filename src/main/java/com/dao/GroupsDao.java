package com.dao;

public class GroupsDao {
	
	String groupname;
	String service[];
	MemberDao[] member;
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String[] getService() {
		return service;
	}
	public void setService(String[] service) {
		this.service = service;
	}
	public MemberDao[] getMember() {
		return member;
	}
	public void setMember(MemberDao[] member) {
		this.member = member;
	}
	

}
