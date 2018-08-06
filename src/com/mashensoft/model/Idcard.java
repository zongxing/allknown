package com.mashensoft.model;

public class Idcard {
	private String idcard;
	private String birthday;
	private String sex;
	private String address;
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Idcard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Idcard(String idcard, String birthday, String sex, String address) {
		super();
		this.idcard = idcard;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
	}
	
}
