package com.springboot.service;

import org.springframework.integration.annotation.EndpointId;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

@MessageEndpoint
public class StudentService {

	@ServiceActivator(inputChannel = "student.channel")
	public void recieveMessage(Message<?> message) throws MessagingException {
		System.out.println("###student.channel###");
		System.out.println(message);
		System.out.println(message.getPayload());
	}

//	@ServiceActivator(inputChannel = "student.channel.2")
//	public void recieveMessage1(Message<?> message) throws MessagingException {
//		System.out.println("###student.channel.2###");
//		System.out.println(message);
//		System.out.println(message.getPayload());
//	}
}