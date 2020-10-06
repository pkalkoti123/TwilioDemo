package com.twilio.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.demo.model.GreetingMessage;
import com.twilio.demo.service.KafkaMessageProducerService;

@RestController
@RequestMapping("/")
public class MessageController {

    private final KafkaMessageProducerService kafkaMessageProducerService;

    public MessageController(KafkaMessageProducerService kafkaMessageProducerService) {
        this.kafkaMessageProducerService = kafkaMessageProducerService;
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> addMessage(@RequestBody GreetingMessage message) {
        kafkaMessageProducerService.produceMessage(message);
        return ResponseEntity.ok().build();
    }

}
