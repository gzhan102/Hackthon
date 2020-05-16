package com.tommy.rideshare.driverManagement.dto;

public class DriverRideAcceptedDTO extends RequestDTO {
	private String passengerName;
	private String driverName;
	
	public DriverRideAcceptedDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DriverRideAcceptedDTO(String requestType, String msg, String passengerName, String driverName) {
		super(requestType, msg);
		this.passengerName = passengerName;
		this.driverName = driverName;
	}
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	
	public String getDriverName() {
		return driverName;
	}
}
