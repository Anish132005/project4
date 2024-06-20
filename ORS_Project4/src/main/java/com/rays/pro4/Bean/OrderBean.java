package com.rays.pro4.Bean;

import java.util.Date;

public class OrderBean extends BaseBean {
	
	private String OrderName;
	private String OrderPrice;
	private Date OrderDate;
	private String OrderStatus;
	
	public String getOrderName() {
		return OrderName;
	}
	public void setOrderName(String orderName) {
		OrderName = orderName;
	}
	public String getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		OrderPrice = orderPrice;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	@Override
	public String getkey() {
		return id +"";
	}
	@Override
	public String getValue() {
		return OrderStatus+"";
	}
	

}
