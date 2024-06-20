package com.rays.pro4.Bean;

import java.util.Date;

public class VehicleBean extends BaseBean {

	private String VehicleName;
	private String VehiclePrice;
	private Date dOB;
	private String mobilenumber;
	private String RTO;

	public String getVehicleName() {
		return VehicleName;
	}

	public void setVehicleName(String vehicleName) {
		VehicleName = vehicleName;
	}

	public String getVehiclePrice() {
		return VehiclePrice;
	}

	public void setVehiclePrice(String vehiclePrice) {
		VehiclePrice = vehiclePrice;
	}

	public Date getdOB() {
		return dOB;
	}

	public void setdOB(Date dOB) {
		this.dOB = dOB;
	}

	public String getMobileNubmer() {
		return mobilenumber;
	}

	public void setMobileNubmer(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getRTO() {
		return RTO;
	}

	public void setRTO(String rTO) {
		RTO = rTO;
	}

	@Override
	public String getkey() {
		return mobilenumber;
	}

	@Override
	public String getValue() {
		return mobilenumber;
	}

}
