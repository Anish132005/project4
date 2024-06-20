package com.rays.pro4.Bean;

import java.util.Date;

public class InventoryBean extends BaseBean {

	private String SupplierName;
	private Date LastupdatedDate;
	private String Qantity;
	private String Product;

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public Date getLastupdatedDate() {
		return LastupdatedDate;
	}

	public void setLastupdatedDate(Date lastupdatedDate) {
		LastupdatedDate = lastupdatedDate;
	}

	public String getQantity() {
		return Qantity;
	}

	public void setQantity(String qantity) {
		Qantity = qantity;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	@Override
	public String getkey() {
		return Product;
	}

	@Override
	public String getValue() {
		return Product;
	}

}
