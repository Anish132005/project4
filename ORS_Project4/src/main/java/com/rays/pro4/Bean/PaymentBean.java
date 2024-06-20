package com.rays.pro4.Bean;

import java.util.Date;

public class PaymentBean extends BaseBean {

	private String Name;
	private String Amount;
	private Date Dob;
	private String UPI;
	private String Bank;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getUPI() {
		return UPI;
	}

	public void setUPI(String uPI) {
		UPI = uPI;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
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
