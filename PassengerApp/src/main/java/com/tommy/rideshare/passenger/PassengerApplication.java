package com.tommy.rideshare.passenger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class PassengerApplication {

	private static final Logger LOGGER = LogManager.getLogger(PassengerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PassengerApplication.class, args);
		System.out.print("Passenger Application Starts:");
		for (int i = 0; i < 20; i++) {
			System.out.print("--------------------");
		}
		System.out.println();
	}
}
