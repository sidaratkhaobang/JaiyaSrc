package com.dao;

public class HospitalAdminDao {
	int id;
	String nameofhospital;
	double latijude;
	double longjijude;
	String tell;
	int provinceId;
	int districtId;
	int subdistrictId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameofhospital() {
		return nameofhospital;
	}
	public void setNameofhospital(String nameofhospital) {
		this.nameofhospital = nameofhospital;
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
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
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
	
}
