package com.tommy.rideshare.passenger.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public class RideAcceptedConfiguration {
	private static final String RIDE_ACCEPTED_SUBSCRIPTION = "acme-rideshare-ride-accepted-subscription";
	
	@Bean
	public PubSubInboundChannelAdapter rideAcceptedAdapter(
			@Qualifier("passengerAppInputChannel") MessageChannel inputChannel,
			PubSubTemplate template) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template, RIDE_ACCEPTED_SUBSCRIPTION);
		adapter.setOutputChannel(inputChannel);
		adapter.setAckMode(AckMode.MANUAL);
		
		return adapter;
	}
}