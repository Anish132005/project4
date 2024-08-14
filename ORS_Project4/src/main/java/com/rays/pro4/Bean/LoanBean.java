package com.rays.pro4.Bean;

import java.util.Date;

public class LoanBean extends BaseBean {

	private Date Loan_Start_Date;
	private String Customer_Id;
	private Integer Loan_Amount;
	private Integer Interest_Rate;
	
	
	public Date getLoan_Start_Date() {
		return Loan_Start_Date;
	}
	public void setLoan_Start_Date(Date loan_Start_Date) {
		Loan_Start_Date = loan_Start_Date;
	}
	public String getCustomer_Id() {
		return Customer_Id;
	}
	public void setCustomer_Id(String customer_Id) {
		Customer_Id = customer_Id;
	}
	public Integer getLoan_Amount() {
		return Loan_Amount;
	}
	public void setLoan_Amount(Integer loan_Amount) {
		Loan_Amount = loan_Amount;
	}
	public Integer getInterest_Rate() {
		return Interest_Rate;
	}
	public void setInterest_Rate(Integer interest_Rate) {
		Interest_Rate = interest_Rate;
	}
	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}