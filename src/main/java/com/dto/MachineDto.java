package com.dto;

import org.bson.types.ObjectId;

public class MachineDto {
	String _id;
	String userid;
	String nameofmachine;
	double latitude;
	double longitude;
	String provinceId;
	String districtId;
	String subdistrictId;
	String status;
	
	public ObjectId getId() {
		return new ObjectId(_id);
	}
	public void setId(String _id) {
		this._id = _id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNameofmachine() {
		return nameofmachine;
	}
	public void setNameofmachine(String nameofmachine) {
		this.nameofmachine = nameofmachine;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getSubdistrictId() {
		return subdistrictId;
	}
	public void setSubdistrictId(String subdistrictId) {
		this.subdistrictId = subdistrictId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
