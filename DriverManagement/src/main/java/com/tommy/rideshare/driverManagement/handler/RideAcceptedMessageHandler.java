package com.tommy.rideshare.driverManagement.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.rideshare.driverManagement.config.DriverManagementConfiguration.DriverManagementGateway;
import com.tommy.rideshare.driverManagement.dto.DriverRideAcceptedDTO;

public class RideAcceptedMessageHandler implements PubSubMessageHandler {
	
	private static final String RIDE_ACCEPTED_TOPIC = "acme-rideshare-ride-accepted-topic";
	private static final Logger LOGGER = LogManager.getLogger(RideAcceptedMessageHandler.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private DriverManagementGateway driverManagementGateway;
	
	@Override
	public void handlePubSubMessage(DriverManagementGateway driverManagementGateway, String payload) {
		// TODO Auto-generated method stub
		try {
			DriverRideAcceptedDTO dto = mapper.readValue(payload, DriverRideAcceptedDTO.class);
			dto.setRequestType("ride accepted");
			String json = mapper.writeValueAsString(dto);
			driverManagementGateway.sendToPubsub(RIDE_ACCEPTED_TOPIC, json);
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
