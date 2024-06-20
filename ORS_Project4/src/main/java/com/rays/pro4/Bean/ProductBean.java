package com.rays.pro4.Bean;

import java.util.Date;

import com.rays.pro4.Util.DataUtility;

// Create table st_product query
//CREATE TABLE st_product (id INT PRIMARY KEY, productName VARCHAR(255), productAmmount VARCHAR(255), purchaseDate DATE, productCategory VARCHAR(255));

public class ProductBean extends BaseBean {
	
	private String productName;
	private String productPrice;
	private Date   productDate;
	private String productCategory;
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String getkey() {
		return productCategory;
	}

	@Override
	public String getValue() {
		return productCategory;
	}

}