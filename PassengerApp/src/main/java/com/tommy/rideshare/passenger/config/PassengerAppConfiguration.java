package com.tommy.rideshare.passenger.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class PassengerAppConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(PassengerAppConfiguration.class);

	@Bean
	public MessageChannel passengerAppOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel passengerAppInputChannel() {
		return new DirectChannel();
	}

	@MessagingGateway(defaultRequestChannel = "passengerAppOutputChannel")
	public interface PassengerAppOutputGateway {
		public void sendToPubSub(@Header(GcpPubSubHeaders.TOPIC) String topic, String msg);
	}

	@Bean
	@ServiceActivator(inputChannel = "passengerAppInputChannel")
	public MessageHandler messageHandler() {
		return msg -> {
			String payload = new String((byte[]) msg.getPayload());
			LOGGER.info(payload);

//			BasicAcknowledgeablePubsubMessage originalMessage =
//					msg.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
//			originalMessage.ack();
		};
	}
}
