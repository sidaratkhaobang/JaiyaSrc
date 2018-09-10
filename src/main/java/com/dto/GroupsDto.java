package com.dto;

public class GroupsDto {
	
	String groupname;
	String service[];
	MemberDto[] member;
	
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
	public MemberDto[] getMember() {
		return member;
	}
	public void setMember(MemberDto[] member) {
		this.member = member;
	}
	

}
