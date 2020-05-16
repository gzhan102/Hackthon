package com.tommy.rideshare.driverManagement.handler;

import org.springframework.messaging.Message;

import com.tommy.rideshare.driverManagement.config.DriverManagementConfiguration.DriverManagementGateway;

public interface PubSubMessageHandler {
	public void handlePubSubMessage(DriverManagementGateway driverManagementGateway, String payload);
}
