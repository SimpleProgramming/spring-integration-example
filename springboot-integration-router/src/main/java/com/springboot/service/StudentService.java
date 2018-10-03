package com.springboot.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import com.springboot.model.Student;

@MessageEndpoint
public class StudentService {

	@ServiceActivator(inputChannel = "student.channel")
	public void recieveMessage(Message<?> message) throws MessagingException {
		System.out.println("###student.channel###");
		System.out.println(message);
		System.out.println(message.getPayload());
	}

	// @ServiceActivator(inputChannel = "student.channel.2")
	// public void recieveMessage1(Message<?> message) throws MessagingException {
	// System.out.println("###student.channel.2###");
	// System.out.println(message);
	// System.out.println(message.getPayload());
	// }

	@ServiceActivator(inputChannel = "integration.gateway.channel")
	public void anotherMessage(Message<String> message) throws MessagingException {
		MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
		Message<String> newMessage = MessageBuilder
				.withPayload("Welcome, " + message.getPayload() + " to Spring Integration").build();
		replyChannel.send(newMessage);
	}

	@ServiceActivator(inputChannel = "integration.student.objectToJson.channel", outputChannel = "integration.student.jsonToObject.channel")
	public Message<?> recieveMessage1(Message<?> message) throws MessagingException {
		System.out.println("######################");
		System.out.println(message);
		System.out.println("######################");
		System.out.println("Object to Json - " + message.getPayload());
		return message;
	}

	@ServiceActivator(inputChannel = "integration.student.jsonToObject.fromTransformer.channel")
	public void processJsonToObject(Message<?> message) throws MessagingException {
		MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
		System.out.println("######################");
		System.out.println("Json to Object - " + message.getPayload());
		Student student = (Student) message.getPayload();
		Message<?> newMessage = MessageBuilder.withPayload(student.toString()).build();
		replyChannel.send(newMessage);
	}
}