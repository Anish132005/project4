package com.rays.pro4.Bean;

import java.util.Date;

public class BankBean extends BaseBean {
	
	private String BankName;
	private String AccountNo;
	private Date Dob;
	private String Address;

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getAccountNo() {
		return AccountNo;
	}

	public void setAccountNo(String accountNo) {
		AccountNo = accountNo;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@Override
	public String getkey() {
		return Address;
	}

	@Override
	public String getValue() {
		return Address;
	}

}
