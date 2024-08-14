package com.rays.pro4.Bean;

import java.util.Date;

public class CustomerBean extends BaseBean{
	
	private String Name;
	private Date Date_of_Birth;
	private String PhoneNumber;
	private String Gender;
	
	

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDate_of_Birth() {
		return Date_of_Birth;
	}

	public void setDate_of_Birth(Date date_of_Birth) {
		Date_of_Birth = date_of_Birth;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	@Override
	public String getkey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}
	
	

}
