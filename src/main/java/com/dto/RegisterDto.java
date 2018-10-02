package com.dto;

import java.sql.Date;

public class RegisterDto {
	String firstName;
	String lastName;
	Date dob;
	int gender;
	String allergy;
	String bloodgroup;
	int status;
	int iduser;
	
	

	public String getFirstName() {
		return firstName;
	}
	public void setUsername(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDOB() {
		return dob;
	}
	public void setDOB(Date dob) {
		this.dob = dob;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
}
