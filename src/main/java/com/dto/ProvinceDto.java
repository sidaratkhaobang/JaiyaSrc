package com.dto;

import org.bson.types.ObjectId;

public class ProvinceDto {
	String id;
	int provinceId;
	String provinceName;
	double latitude;
	double longtitude;
	public ObjectId getId() {
		return new ObjectId(id);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	
}
