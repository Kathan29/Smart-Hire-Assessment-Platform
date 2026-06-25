package com.smarthire.assessment_platform.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.smarthire.assessment_platform.dto.McqOptionRequest;
import com.smarthire.assessment_platform.dto.McqOptionResponse;
import com.smarthire.assessment_platform.dto.UpdateMcqOption;
import com.smarthire.assessment_platform.entities.McqOption;

@Mapper(componentModel = "spring")
public interface McqOptionMapper {
	McqOption toEntity(McqOptionRequest request);
	
	McqOptionResponse toDTO(McqOption mcqOption);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(UpdateMcqOption updateMcq,@MappingTarget McqOption mcqOption);
}
