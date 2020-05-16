package com.tommy.rideshare.driverManagement.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.rideshare.driverManagement.DriverManagementApplication;
import com.tommy.rideshare.driverManagement.config.DriverManagementConfiguration.DriverManagementGateway;
import com.tommy.rideshare.driverManagement.dto.DriverRideRequestDTO;

public class RideRequestMessageHandler implements PubSubMessageHandler {
	
	private static final String DRIVER_RIDE_REQUEST_TOPIC = "acme-rideshare-driver-requested-topic";
	
	private static final Logger LOGGER = LogManager.getLogger(RideRequestMessageHandler.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void handlePubSubMessage(DriverManagementGateway driverManagementGateway, String payload) {
		// TODO Auto-generated method stub
		try {
			DriverRideRequestDTO dto = mapper.readValue(payload, DriverRideRequestDTO.class);
			dto.setRequestType("driver ride request");
			String json = mapper.writeValueAsString(dto);
			driverManagementGateway.sendToPubsub(DRIVER_RIDE_REQUEST_TOPIC, json);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
