package com.smarthire.assessment_platform.dto;

import com.smarthire.assessment_platform.enums.UserRoles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {
	@NotBlank(message = "Username cannot be blank")
	private String username;
	
	@NotBlank(message = "Password cannot be blank")
	private String password;
	
	@Email
	@NotBlank(message = "Email cannot be blank")
	private String email;
	
	//@NotBlank will not work here, it works with string
	@NotNull(message = "Roles cannot be null")
	private UserRoles roles;
}
