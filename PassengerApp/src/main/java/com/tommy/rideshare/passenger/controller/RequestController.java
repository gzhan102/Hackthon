package com.tommy.rideshare.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.tommy.rideshare.passenger.config.PassengerAppConfiguration.PassengerAppOutputGateway;

@RestController
public class RequestController {
	private static final String RIDE_REQUEST_TOPIC = "acme-rideshare-ride-requested-topic";
	
	@Autowired
	public PassengerAppOutputGateway passengerAppOutputGateway;
	
	@PostMapping("/rideRequest")
	public String publishRideRequest(@RequestBody String msg) {
		passengerAppOutputGateway.sendToPubSub(RIDE_REQUEST_TOPIC, msg);
		return msg;
	}
}
