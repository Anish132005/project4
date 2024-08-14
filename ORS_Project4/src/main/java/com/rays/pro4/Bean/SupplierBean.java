package com.rays.pro4.Bean;

import java.util.Date;

public class SupplierBean extends BaseBean {

	private String Name;
	private String Category;
	private Date Registrationdate;
	private Integer payment;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public Date getRegistrationdate() {
		return Registrationdate;
	}

	public void setRegistrationdate(Date registrationdate) {
		Registrationdate = registrationdate;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
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
