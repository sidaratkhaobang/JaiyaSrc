package com.dto;

import org.bson.types.ObjectId;

public class DistrictDto {
	
	String districtName;
	String provinceId;
	String districId;
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getDistricId() {
		return districId;
	}
	public void setDistricId(String districId) {
		this.districId = districId;
	}
	
	

}
