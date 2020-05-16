package com.tommy.rideshare.driverApp.config;

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
public class DriverAcceptedConfigration {
	
	public static final String DRIVER_ACCEPTED_TOPIC = "acme-rideshare-driver-accepted-topic";
	
	@Bean
	@ServiceActivator(inputChannel = "driverAppOutputChannel")
	public MessageHandler messageHandler(PubSubTemplate template) {
		return new PubSubMessageHandler(template, DRIVER_ACCEPTED_TOPIC);
	}
	
}
