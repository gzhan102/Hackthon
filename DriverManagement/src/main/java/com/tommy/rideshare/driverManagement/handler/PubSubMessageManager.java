package com.tommy.rideshare.driverManagement.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PubSubMessageManager {
	private static final Map<String, PubSubMessageHandler> messageHandlers = new HashMap<>();
	
	public void register(String requestType, PubSubMessageHandler handler) {
		messageHandlers.put(requestType, handler);
	}
	
	public PubSubMessageHandler getMessageHandler(String requestType) {
		return messageHandlers.get(requestType);
	}
}
