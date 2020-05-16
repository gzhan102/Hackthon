package com.tommy.rideshare.driverApp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
public class DriverRideRequestConfigration {

	private static final String DRIVER_RIDE_REQUEST_SUBSCRIPTION = "acme-rideshare-driver-requested-subscription";

	@Bean
	public PubSubInboundChannelAdapter driverRideRequestAdapter(
			@Qualifier("driverAppInputChannel") MessageChannel inputChannel, PubSubTemplate template) {
		PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(template,
				DRIVER_RIDE_REQUEST_SUBSCRIPTION);
		adapter.setOutputChannel(inputChannel);
		adapter.setAckMode(AckMode.MANUAL);

		return adapter;
	}

}
