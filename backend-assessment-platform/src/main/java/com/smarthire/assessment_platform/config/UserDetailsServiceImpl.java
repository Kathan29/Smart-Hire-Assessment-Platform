package com.smarthire.assessment_platform.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByUsername(username);
		
		UserDetails userDetails = User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles().name())
				.build();
		return userDetails;
	}

}
