package com.dto;

import org.bson.types.ObjectId;

public class HospitalAdminDto {

	String id;
	String nameofhospital;
	double lattiude;
	double longitude;
	String tell;
	String provinceId;
	String districtId;
	String subdistrictId;

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public ObjectId getId() {
		return new ObjectId(id);
	}
	public void setId(String id) {
		this.id = id;
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

	

	public String getNameofhospital() {
		return nameofhospital;
	}

	public void setNameofhospital(String nameofhospital) {
		this.nameofhospital = nameofhospital;
	}

	public double getLattiude() {
		return lattiude;
	}

	public void setLattiude(double lattiude) {
		this.lattiude = lattiude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	
	


}
