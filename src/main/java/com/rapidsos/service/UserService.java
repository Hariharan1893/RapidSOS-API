package com.rapidsos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rapidsos.entity.EmergencyContact;
import com.rapidsos.entity.User;
import com.rapidsos.repository.EmergencyContactRepository;
import com.rapidsos.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final EmergencyContactRepository contactRepository;

	public UserService(UserRepository userRepository, EmergencyContactRepository contactRepository) {
		this.userRepository = userRepository;
		this.contactRepository = contactRepository;
	}

	// Register a new user
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	// Add an emergency contact for a user
	public EmergencyContact addEmergencyContact(Long userId, EmergencyContact contact) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		User user = userOptional.get();
		contact.setUser(user);
		return contactRepository.save(contact);
	}

	// Get user details
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	// Get emergency contacts of a user
	public List<EmergencyContact> getEmergencyContacts(Long userId) {
		return contactRepository.findByUserId(userId);
	}
	
	// Delete user by ID
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        contactRepository.deleteByUserId(userId); // Delete associated contacts first
        userRepository.deleteById(userId);
    }

    // Delete emergency contact by ID
    @Transactional
    public void deleteEmergencyContact(Long contactId) {
        if (!contactRepository.existsById(contactId)) {
            throw new RuntimeException("Emergency contact not found");
        }
        contactRepository.deleteById(contactId);
    }
}
