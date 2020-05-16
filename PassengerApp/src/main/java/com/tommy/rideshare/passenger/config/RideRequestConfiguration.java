package com.tommy.rideshare.passenger.config;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Configuration
public class RideRequestConfiguration {
	private static final String RIDE_REQUEST_TOPIC = "acme-rideshare-ride-requested-topic";
	
	@Bean
	@ServiceActivator(inputChannel = "passengerAppOutputChannel")
	public MessageHandler messageSender(PubSubTemplate template) {
		return new PubSubMessageHandler(template, RIDE_REQUEST_TOPIC);
	}
}
