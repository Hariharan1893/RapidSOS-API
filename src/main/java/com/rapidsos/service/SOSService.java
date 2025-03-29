package com.rapidsos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rapidsos.entity.EmergencyContact;
import com.rapidsos.entity.User;
import com.rapidsos.repository.UserRepository;

@Service
public class SOSService {

	private final UserRepository userRepository;
	private final EmailService emailService;
	private final TwilioService twilioService;

	public SOSService(UserRepository userRepository, EmailService emailService, TwilioService twilioService) {
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.twilioService = twilioService;
	}

	public void sendSOS(Long userId, String message) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		List<EmergencyContact> contacts = user.getEmergencyContacts();

		for (EmergencyContact contact : contacts) {
			emailService.sendEmail(contact.getEmail(), "🚨 SOS Alert from " + user.getName(), message);
			twilioService.sendSms(contact.getPhoneNumber(), "🚨 SOS Alert from " + user.getName() + ": " + message);

		}
	}
}
