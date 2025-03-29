package com.rapidsos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rapidsos.entity.EmergencyContact;
import com.rapidsos.entity.User;
import com.rapidsos.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Register a new user

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User savedUser = userService.registerUser(user);
		return ResponseEntity.ok(savedUser);
	}

	// Add an emergency contact for a user

	@PostMapping("/{userId}/contacts")
	public ResponseEntity<EmergencyContact> addEmergencyContact(@PathVariable Long userId,
			@RequestBody EmergencyContact contact) {
		EmergencyContact savedContact = userService.addEmergencyContact(userId, contact);
		return ResponseEntity.ok(savedContact);
	}

	// Get a user Details by id

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		Optional<User> user = userService.getUserById(userId);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Get All emergency contact of a user

	@GetMapping("/{userId}/contacts")
	public ResponseEntity<List<EmergencyContact>> getEmergencyContacts(@PathVariable Long userId) {
		List<EmergencyContact> contacts = userService.getEmergencyContacts(userId);
		return ResponseEntity.ok(contacts);
	}
	
	// Delete a user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // Delete an emergency contact by ID
    @DeleteMapping("/{userId}/contacts/{contactId}")
    public ResponseEntity<Void> deleteEmergencyContact(@PathVariable Long userId, @PathVariable Long contactId) {
        userService.deleteEmergencyContact(userId);
        return ResponseEntity.noContent().build();
    }
}
