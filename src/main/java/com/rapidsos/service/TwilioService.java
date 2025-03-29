package com.rapidsos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioService {

	@Value("${twilio.from-number}")
	private String fromNumber;
	
	public void sendSms(String to, String message) {
		Message.creator(new PhoneNumber(to), new PhoneNumber(fromNumber), message).create();
	}
}
