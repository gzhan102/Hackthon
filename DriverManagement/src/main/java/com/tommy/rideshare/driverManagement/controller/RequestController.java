package com.tommy.rideshare.driverManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tommy.rideshare.driverManagement.config.DriverManagementConfiguration.DriverManagementGateway;;

@RestController
public class RequestController {
	
	private static final String DRIVER_RIDE_REQUEST_TOPIC = "acme-rideshare-driver-requested-topic";
	private static final String RIDE_ACCEPTED_TOPIC = "acme-rideshare-ride-accepted-topic";
	
	@Autowired
	private DriverManagementGateway driverManagementGateway;
	
	@PostMapping("/driverRideRequest")
	public String publishDriverRideRequest(@RequestBody String msg) {
		driverManagementGateway.sendToPubsub(DRIVER_RIDE_REQUEST_TOPIC, msg);
		return msg;
	}
	
	@PostMapping("/rideAccepted")
	public String publishRideAccepted(@RequestBody String msg) {
		driverManagementGateway.sendToPubsub(RIDE_ACCEPTED_TOPIC, msg);
		return msg;
	}
}
