package com.tommy.rideshare.driverManagement.config;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class DriverRideRequestConfiguration {
	private static final String DRIVER_RIDE_REQUEST_TOPIC = "acme-rideshare-driver-requested-topic";
	
	@Bean
	@ServiceActivator(inputChannel = "driverManagementOutputChannel")
	public MessageHandler driverRideRequestHandler(PubSubTemplate  template) {
		return new PubSubMessageHandler(template, DRIVER_RIDE_REQUEST_TOPIC);
	}
	
}
