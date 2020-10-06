package com.twilio.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.demo.model.GreetingMessage;
import com.twilio.demo.service.KafKaProducerService;

/**
 * Rest controller class for sending message
 * @author pkalkoti003
 *
 */
@RestController
public class KafkaProducerController {
	private final KafKaProducerService producerService;

	@Autowired
	public KafkaProducerController(KafKaProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping(value = "/messages")
	public void sendMessageToKafkaTopic(@RequestBody GreetingMessage message) {
		this.producerService.sendGreetingMessage(message);
	}
}