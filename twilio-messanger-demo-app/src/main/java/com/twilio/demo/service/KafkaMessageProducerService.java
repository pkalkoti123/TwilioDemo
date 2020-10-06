package com.twilio.demo.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.twilio.demo.Util.KafkaUtils;
import com.twilio.demo.model.GreetingMessage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageProducerService {

    private KafkaTemplate<String, GreetingMessage> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, GreetingMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceMessage(GreetingMessage greetingMessage) {
        log.info("Sending message {} ", greetingMessage);
        //kafkaTemplate.send(MESSAGE_TOPIC, new GreetingMessage());
        
        Message<GreetingMessage> message = MessageBuilder
                .withPayload(greetingMessage)
                .setHeader(KafkaHeaders.TOPIC, KafkaUtils.MESSAGE_TOPIC)
                .build();
        
        kafkaTemplate.send(message);
    }
}
