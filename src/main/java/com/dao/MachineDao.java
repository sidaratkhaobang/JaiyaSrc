package com.dao;

import org.bson.types.ObjectId;

public class MachineDao {
	String _id;
	int userid;
	String nameofmachine;
	double latijude;
	double longjijude;
	int provinceId;
	int districtId;
	int subdistrictId;
	int status;
	
	public ObjectId getId() {
		return new ObjectId(_id);
	}
	public void setId(String _id) {
		this._id = _id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getNameofmachine() {
		return nameofmachine;
	}
	public void setNameofmachine(String nameofmachine) {
		this.nameofmachine = nameofmachine;
	}
	public double getLatijude() {
		return latijude;
	}
	public void setLatijude(double latijude) {
		this.latijude = latijude;
	}
	public double getLongjijude() {
		return longjijude;
	}
	public void setLongjijude(double longjijude) {
		this.longjijude = longjijude;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getSubdistrictId() {
		return subdistrictId;
	}
	public void setSubdistrictId(int subdistrictId) {
		this.subdistrictId = subdistrictId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}

