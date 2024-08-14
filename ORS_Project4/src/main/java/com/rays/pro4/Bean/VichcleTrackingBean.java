package com.rays.pro4.Bean;


public class VichcleTrackingBean extends BaseBean {


	private Double Lat;
	private Double LongId;
	private Integer VichcleId;
   
	


	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Integer getVichcleId() {
		return VichcleId;
	}

	public void setVichcleId(Integer vichcleId) {
		VichcleId = vichcleId;
	}

	public Double getLongId() {
		return LongId;
	}

	public void setLongId(Double longId) {
		LongId = longId;
	}

	@Override
	public String getkey() {
		return LongId + "";
	}

	@Override
	public String getValue() {
		return LongId + "";
	}

}
