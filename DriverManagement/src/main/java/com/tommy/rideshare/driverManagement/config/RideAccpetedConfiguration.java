package com.tommy.rideshare.driverManagement.config;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
public class RideAccpetedConfiguration {
	private static final String RIDE_ACCEPTED_TOPIC = "acme-rideshare-ride-accepted-topic";
	
	@Bean
	@ServiceActivator(inputChannel = "driverManagementOutputChannel")
	public MessageHandler rideAcceptedHandler(PubSubTemplate  template) {
		return new PubSubMessageHandler(template, RIDE_ACCEPTED_TOPIC);
	}
}
