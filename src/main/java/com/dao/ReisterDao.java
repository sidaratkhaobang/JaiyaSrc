package com.dao;

import java.sql.Date;

import org.bson.types.ObjectId;

public class ReisterDao {
//	String id;
	String firstName;
	String lastName;
	Date dob;
	String gender;
	String allergy;
	String bloodgroup;
	String disease;
	int status;
	String iduser;
	String idmachine;
	
	
	
	public String getIdmachine() {
		return idmachine;
	}

	public void setIdmachine(String idmachine) {
		this.idmachine = idmachine;
	}

	
	
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
//	public ObjectId getId() {
//		return new ObjectId(id);
//	}
//	
//	public void setId(String id) {
//		this.id = id;
//	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
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
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
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
	
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	
}
