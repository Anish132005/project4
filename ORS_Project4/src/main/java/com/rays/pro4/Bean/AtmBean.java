package com.rays.pro4.Bean;

import java.text.DecimalFormat;
import java.util.Date;

public class AtmBean extends BaseBean{
	
	private String Location;
	private Double Cash_available;
	private  Date Last_Restocked_Date;
	private String Remark;

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Double getCash_available() {
		return Cash_available;
	}

	public void setCash_available(Double cash_available) {
		Cash_available = cash_available;
	}

	public Date getLast_Restocked_Date() {
		return Last_Restocked_Date;
	}

	public void setLast_Restocked_Date(Date last_Restocked_Date) {
		Last_Restocked_Date = last_Restocked_Date;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	@Override
	public String getkey() {
		return Location;
	}

	@Override
	public String getValue() {
		return Location;
	}

}
