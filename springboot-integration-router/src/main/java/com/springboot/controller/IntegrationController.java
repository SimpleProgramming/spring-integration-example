package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Address;
import com.springboot.model.Student;
import com.springboot.service.IntegrationGateway;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

	@Autowired
	private IntegrationGateway integrationGateway;

	@PostMapping("/student")
	public void processStudentDetails(@RequestBody Student student) {
		integrationGateway.process(student);
	}

	@PostMapping("/address")
	public void processAddressDetails(@RequestBody Address address) {
		integrationGateway.process(address);
	}
}
