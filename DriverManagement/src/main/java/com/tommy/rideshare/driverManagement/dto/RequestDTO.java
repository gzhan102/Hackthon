package com.tommy.rideshare.driverManagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO {
	private String requestType;
	private String msg;
	
	public RequestDTO() {
		
	}
	
	public RequestDTO(String requestType, String msg) {
		this.requestType = requestType;
		this.msg = msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public String getRequestType() {
		return requestType;
	}
}
