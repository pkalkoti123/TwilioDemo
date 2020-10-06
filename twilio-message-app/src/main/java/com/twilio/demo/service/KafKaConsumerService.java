package com.twilio.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.demo.common.AppConstants;
import com.twilio.demo.model.GreetingMessage;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class KafKaConsumerService 
{
	private final Logger logger 
		= LoggerFactory.getLogger(KafKaConsumerService.class);
	
	private static String ACCOUNT_SID="AC74290fd32ede4c0efd7694dff34041a4";
	private static String AUTH_TOKEN="cfdff46cf5526c1157b7683482cdf06a";
	private static String FROM_NUMBER="+16592005216";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

	// Listener that listens to published topic name 'test'
	@KafkaListener(topics = AppConstants.TOPIC_NAME_TEST, groupId = AppConstants.GROUP_ID)
	public void consumeMessage(GreetingMessage greetingMessage) {
		logger.info(String.format("Message recieved -> %s", greetingMessage));

		Message message = null;
		try {
			if (greetingMessage.getMessage().equalsIgnoreCase("test")) {
				throw new RuntimeException("Error while sending message" + greetingMessage.getMessage());
			}
			message = Message.creator(new PhoneNumber(greetingMessage.getToNumber()), new PhoneNumber(FROM_NUMBER),
					greetingMessage.getMessage()).create();
		} catch (ApiException ex) {
			logger.info("Exception Occured"+ex.getMessage());
			throw new RuntimeException("Incompatible message " + message);
		}
	}

}
