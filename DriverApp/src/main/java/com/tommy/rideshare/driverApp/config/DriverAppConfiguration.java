package com.tommy.rideshare.driverApp.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class DriverAppConfiguration {

	private static final Logger LOGGER = LogManager.getLogger(DriverAppConfiguration.class);
	
	@Bean
	public MessageChannel driverAppInputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel driverAppOutputChannel() {
		return new DirectChannel();
	}
	
	@MessagingGateway(defaultRequestChannel = "driverAppOutputChannel")
	public interface DriverAppGateway {
		public void sendToPubsub(@Header(GcpPubSubHeaders.TOPIC) String topic, String msg);
	}
	
	@Bean
	@ServiceActivator(inputChannel = "driverAppInputChannel")
	public MessageHandler handleDriverRideRequest() {
		return msg -> {
			String payload = new String((byte[])msg.getPayload());
			LOGGER.info("Message: " + payload);
			
//			BasicAcknowledgeablePubsubMessage originalMessage =
//					msg.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
//			originalMessage.ack();
		};
	}
}
