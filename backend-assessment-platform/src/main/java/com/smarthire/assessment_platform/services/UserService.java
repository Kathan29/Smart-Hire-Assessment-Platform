package com.smarthire.assessment_platform.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.exceptions.BadRequestException;
import com.smarthire.assessment_platform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepo;
	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

	public Users saveUserEntry(Users user){
		if(userRepo.existsByEmail(user.getEmail())) {
			throw new BadRequestException("Email already exists");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
}
