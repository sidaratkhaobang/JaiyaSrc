package com.dto;

import org.bson.types.ObjectId;

public class TimetogetpillowDto {

	String time;
	String date;
	String pillow;
	String rang;
	String iduser;
	String timetoeat;
	String datetoeat;
	String _id;
	String statustoeatpillow;
	public ObjectId getId() {
		return new ObjectId(_id);
	}
	public void setId(String _id) {
		this._id = _id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPillow() {
		return pillow;
	}
	public void setPillow(String pillow) {
		this.pillow = pillow;
	}
	public String getRang() {
		return rang;
	}
	public void setRang(String rang) {
		this.rang = rang;
	}
	public String getIduser() {
		return iduser;
	}
	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	public String getTimetoeat() {
		return timetoeat;
	}
	public void setTimetoeat(String timetoeat) {
		this.timetoeat = timetoeat;
	}
	public String getDatetoeat() {
		return datetoeat;
	}
	public void setDatetoeat(String datetoeat) {
		this.datetoeat = datetoeat;
	}
	public String getStatustoeatpillow() {
		return statustoeatpillow;
	}
	public void setStatustoeatpillow(String statustoeatpillow) {
		this.statustoeatpillow = statustoeatpillow;
	}
	
	
	
}
