package com.tommy.rideshare.driverManagement.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.rideshare.driverManagement.dto.RequestDTO;
import com.tommy.rideshare.driverManagement.handler.PubSubMessageManager;

@Configuration
public class DriverManagementConfiguration {
	private static final Logger LOGGER = LogManager.getLogger(DriverManagementConfiguration.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private PubSubMessageManager pubSubMessageManager;

	@Bean
	public MessageChannel driverManagementInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel driverManagementOutputChannel() {
		return new DirectChannel();
	}

	@MessagingGateway(defaultRequestChannel = "driverManagementOutputChannel")
	public interface DriverManagementGateway {
		public void sendToPubsub(@Header(GcpPubSubHeaders.TOPIC) String topic, String msg);
	}

	@Autowired
	private DriverManagementGateway driverManagementGateway;

	@Bean
	@ServiceActivator(inputChannel = "driverManagementInputChannel")
	public MessageHandler messageHandler() {
		return msg -> {
			String payload = new String((byte[]) msg.getPayload());
			LOGGER.info("Message: " + payload);
//			BasicAcknowledgeablePubsubMessage originalMessage =
//					msg.getHeaders().get(GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class);
//			originalMessage.ack();
			try {
				RequestDTO requestDTO = mapper.readValue(payload, RequestDTO.class);
				pubSubMessageManager.getMessageHandler(requestDTO.getRequestType())
						.handlePubSubMessage(driverManagementGateway, payload);
			} catch (JsonProcessingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		};
	}

}
