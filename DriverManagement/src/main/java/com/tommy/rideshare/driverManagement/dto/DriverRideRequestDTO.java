package com.tommy.rideshare.driverManagement.dto;

public class DriverRideRequestDTO extends RequestDTO{
	private String passengerName;
	
	public DriverRideRequestDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DriverRideRequestDTO(String requestType, String msg, String passengerName) {
		super(requestType, msg);
		this.passengerName = passengerName;
	}
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
}
