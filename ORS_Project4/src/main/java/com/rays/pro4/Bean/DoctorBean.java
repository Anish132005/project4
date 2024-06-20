package com.rays.pro4.Bean;

import java.util.Date;

public class DoctorBean extends BaseBean {

	private String Name;
	private Date dob;
	private String Mobile;
	private String expertise;
	private String fess;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getFess() {
		return fess;
	}

	public void setFess(String fess) {
		this.fess = fess;
	}

	@Override
	public String getkey() {

		return expertise;
	}
	

	@Override
	public String getValue() {

		return expertise;
	}

}
