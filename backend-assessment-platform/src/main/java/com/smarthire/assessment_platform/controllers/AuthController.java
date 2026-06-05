package com.smarthire.assessment_platform.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.assessment_platform.dto.UserLogin;
import com.smarthire.assessment_platform.dto.UserRegistration;
import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.mappers.UserMapper;
import com.smarthire.assessment_platform.services.JWTService;
import com.smarthire.assessment_platform.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final UserService userService;
	private final UserMapper userMapper;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;

	@PostMapping("/register")
	public ResponseEntity<Users> createUser(@Valid @RequestBody UserRegistration userRegistration) {

		Users user = userMapper.toEntity(userRegistration);
		user = userService.saveUserEntry(user);
		return new ResponseEntity<Users>(user, HttpStatus.CREATED);

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLogin userLogin) {

		Users user = userMapper.toEntity(userLogin);
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		String token = jwtService.generateToken(user.getUsername());

		return new ResponseEntity<>(token, HttpStatus.OK);

	}
}
