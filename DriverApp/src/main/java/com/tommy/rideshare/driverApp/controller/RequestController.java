package com.tommy.rideshare.driverApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tommy.rideshare.driverApp.config.DriverAppConfiguration.DriverAppGateway;

@RestController
public class RequestController {
	
	public static final String DRIVER_ACCEPTED_TOPIC = "acme-rideshare-driver-accepted-topic";
	
	@Autowired
	public DriverAppGateway driverAppGateway;
	
	@PostMapping("/driverRideAccepted")
	public String publicDriverAccepted(@RequestBody String msg) {
		driverAppGateway.sendToPubsub(DRIVER_ACCEPTED_TOPIC, msg);
		return msg;
	}
}
