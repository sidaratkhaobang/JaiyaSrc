package com.dao;

import org.bson.types.ObjectId;

public class ManagerhospitalDao {
	String _id;
	String nameofhospital;
	String latijude;
	String longjijude;
	String tell;
	String province;
	String district;
	String subdistrict;
	
	
	public ObjectId get_id() {
		return new ObjectId(_id);
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getNameofhospital() {
		return nameofhospital;
	}
	public void setNameofhospital(String nameofhospital) {
		this.nameofhospital = nameofhospital;
	}
	public String getLatijude() {
		return latijude;
	}
	public void setLatijude(String latijude) {
		this.latijude = latijude;
	}
	public String getLongjijude() {
		return longjijude;
	}
	public void setLongjijude(String longjijude) {
		this.longjijude = longjijude;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getSubdistrict() {
		return subdistrict;
	}
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	
	
	

}
