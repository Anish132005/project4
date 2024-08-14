package com.rays.pro4.Bean;

import java.util.Date;

public class TransactionBean extends BaseBean {

	private String Description;
	private Date Transaction_Date;
	private String Transaction_Type;
	private String Account_id;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Date getTransaction_Date() {
		return Transaction_Date;
	}

	public void setTransaction_Date(Date transaction_Date) {
		Transaction_Date = transaction_Date;
	}

	public String getTransaction_Type() {
		return Transaction_Type;
	}

	public void setTransaction_Type(String transaction_Type) {
		Transaction_Type = transaction_Type;
	}

	public String getAccount_id() {
		return Account_id;
	}

	public void setAccount_id(String account_id) {
		Account_id = account_id;
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
