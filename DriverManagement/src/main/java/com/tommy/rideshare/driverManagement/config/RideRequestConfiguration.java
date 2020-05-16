package com.tommy.rideshare.driverManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.tommy.rideshare.driverManagement.handler.PubSubMessageManager;
import com.tommy.rideshare.driverManagement.handler.RideRequestMessageHandler;

@Configuration
public class RideRequestConfiguration {
	private static final String RIDE_REQUEST_SUBCRIPTION = "acme-rideshare-ride-requested-subscription";

	@Autowired
	private PubSubMessageManager pubSubMessageManager;

	@Bean
	public PubSubInboundChannelAdapter rideRequestAdapter(
			@Qualifier("driverManagementInputChannel") MessageChannel inputChannel, PubSubTemplate template) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template, RIDE_REQUEST_SUBCRIPTION);
		adapter.setOutputChannel(inputChannel);
		if (pubSubMessageManager.getMessageHandler("ride request") == null) {
			pubSubMessageManager.register("ride request", new RideRequestMessageHandler());
		}

		return adapter;
	}
}
