package com.smarthire.assessment_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.assessment_platform.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	public Users findByUsername(String username);
	public boolean existsByEmail(String email);
}
