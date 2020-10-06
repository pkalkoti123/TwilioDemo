package com.twilio.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.demo.Util.KafkaUtils;
import com.twilio.demo.model.GreetingMessage;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageConsumerService {
	
	private static String ACCOUNT_SID="AC74290fd32ede4c0efd7694dff34041a4";
	private static String AUTH_TOKEN="cfdff46cf5526c1157b7683482cdf06a";
	private static String FROM_NUMBER="+16592005216";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	

    @KafkaListener(topics = KafkaUtils.MESSAGE_TOPIC, groupId = KafkaUtils.MESSAGE_GROUP)
    public void onCustomerMessage(GreetingMessage greetingMessage) throws Exception {
        log.info("Message : {}  is received", greetingMessage);
        Message message = null;
        try {
            message = Message.creator(new PhoneNumber(greetingMessage.getToNumber()), 
            		new PhoneNumber(FROM_NUMBER),greetingMessage.getMessage()).create();
        }catch(Exception ex) {
        	log.info("Incompatible message {} ", message);
            throw new RuntimeException("Incompatible message " + message);
        }
    }
}
