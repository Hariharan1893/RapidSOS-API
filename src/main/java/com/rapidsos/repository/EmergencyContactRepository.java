package com.rapidsos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rapidsos.entity.EmergencyContact;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Long>{
	List<EmergencyContact> findByUserId(Long userId);

	void deleteByUserId(Long userId);
}
