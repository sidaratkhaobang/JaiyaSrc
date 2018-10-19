package com.dto;

import org.bson.types.ObjectId;

public class JaiyaDto {
	String id;
	String _id;
	String username;
	String email;
	String password;
	String confirmpassword;
	String  Idmachine;
	String tel;
	String status;
	public ObjectId getId() {
		return new ObjectId(id);
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getIdmachine() {
		return Idmachine;
	}
	public void setIdmachine(String idmachine) {
		Idmachine = idmachine;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	
	
	
	
	
	
	
	
}
