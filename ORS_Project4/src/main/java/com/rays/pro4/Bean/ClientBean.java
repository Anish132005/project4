package com.rays.pro4.Bean;

import java.util.Date;

public class ClientBean extends BaseBean{
	
	private Integer identifier;
	private String contactName;
	private String location;
	private Date  dob;
	private String phoneNumber;
	

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

