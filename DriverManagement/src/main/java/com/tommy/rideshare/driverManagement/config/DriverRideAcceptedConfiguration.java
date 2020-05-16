package com.tommy.rideshare.driverManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

import com.tommy.rideshare.driverManagement.handler.PubSubMessageManager;
import com.tommy.rideshare.driverManagement.handler.RideAcceptedMessageHandler;

@Configuration
public class DriverRideAcceptedConfiguration {
	private static final String DRIVER_RIDE_ACCEPTED_SUBSCRIPTION = "acme-rideshare-driver-accepted-subscription";

	@Autowired
	private PubSubMessageManager pubSubMessageManager;

	@Bean
	public PubSubInboundChannelAdapter driverRideAcceptedAdapter(
			@Qualifier("driverManagementInputChannel") MessageChannel inputChannel, PubSubTemplate template) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template,
				DRIVER_RIDE_ACCEPTED_SUBSCRIPTION);
		adapter.setOutputChannel(inputChannel);
		if (pubSubMessageManager.getMessageHandler("driver ride accepted") == null) {
			pubSubMessageManager.register("driver ride accepted", new RideAcceptedMessageHandler());
		}

		return adapter;
	}
}
