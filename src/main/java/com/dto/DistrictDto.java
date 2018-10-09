package com.dto;

import org.bson.types.ObjectId;

public class DistrictDto {
	
	String districtName;
	int provinceId;
	int districId;
	
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getDistricId() {
		return districId;
	}

	public void setDistricId(int districId) {
		this.districId = districId;
	}

}
