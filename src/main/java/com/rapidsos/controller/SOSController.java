package com.rapidsos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidsos.service.SOSService;

@RestController
@RequestMapping("/api/sos")
public class SOSController {

	private final SOSService sosService;
	
	public SOSController(SOSService sosService) {
        this.sosService = sosService;
    }
	
	// sending an sos alert to emergency contact
	
	@PostMapping("/{userId}")
    public ResponseEntity<String> sendSOS(@PathVariable Long userId, @RequestBody String message) {
        sosService.sendSOS(userId, message);
        return ResponseEntity.ok("🚨 SOS alert sent successfully!");
    }
	
}
