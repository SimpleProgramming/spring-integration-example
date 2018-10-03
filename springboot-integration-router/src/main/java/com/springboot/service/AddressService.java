package com.springboot.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

@MessageEndpoint
public class AddressService {

	@ServiceActivator(inputChannel = "address.channel")
	public void recieveMessage(Message<?> message) throws MessagingException {
		System.out.println("#########address.channel#############");
		System.out.println(message);
		System.out.println("######################");
		System.out.println(message.getPayload());
	}
}