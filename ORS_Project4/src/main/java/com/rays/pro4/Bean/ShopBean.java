package com.rays.pro4.Bean;

import java.util.Date;

public class ShopBean extends BaseBean {

	private String ShopName;
	private String ProductName;
	private Date PurchaseDate;
	private String Amount;

	public String getShopName() {
		return ShopName;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public Date getPurchaseDate() {
		return PurchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		PurchaseDate = purchaseDate;
	}

	@Override
	public String getkey() {
		return Amount;
	}

	@Override
	public String getValue() {
		return Amount;
	}

}
