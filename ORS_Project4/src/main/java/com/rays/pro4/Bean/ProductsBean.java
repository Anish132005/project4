package com.rays.pro4.Bean;

import java.util.Date;

public class ProductsBean extends BaseBean{
	
	private String Name;
	private String Description;
	private Integer Price;
	private Date DateOfpurchase;
	private String Category;
	

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public Date getDateOfpurchase() {
		return DateOfpurchase;
	}

	public void setDateOfpurchase(Date dateOfpurchase) {
		DateOfpurchase = dateOfpurchase;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	@Override
	public String getkey() {
		return Category;
	}

	@Override
	public String getValue() {
		return Category;
	}

}
