package com.smarthire.assessment_platform.mappers;

import org.mapstruct.Mapper;

import com.smarthire.assessment_platform.dto.UserLogin;
import com.smarthire.assessment_platform.dto.UserRegistration;
import com.smarthire.assessment_platform.entities.Users;


@Mapper(componentModel = "spring")
public interface UserMapper {
	
	//Maps DTO -> Entity (For Registration)
	Users toEntity(UserRegistration dto);
	
	//Maps DTO -> Entity (For login)
	Users toEntity(UserLogin dto);
}
