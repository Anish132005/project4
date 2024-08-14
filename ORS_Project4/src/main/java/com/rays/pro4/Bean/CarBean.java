package com.rays.pro4.Bean;

import java.util.Date;

public class CarBean extends BaseBean {

	private String CarName;
	private String CarModel;
	private Integer Price;
	private Date dob;
	private String OwnerName;

	public String getCarName() {
		return CarName;
	}

	public void setCarName(String carName) {
		CarName = carName;
	}

	public String getCarModel() {
		return CarModel;
	}

	public void setCarModel(String carModel) {
		CarModel = carModel;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getOwnerName() {
		return OwnerName;
	}

	public void setOwnerName(String ownerName) {
		OwnerName = ownerName;
	}

	@Override
	public String getkey() {
		return CarName;
	}

	@Override
	public String getValue() {
		return CarName;
	}

}
