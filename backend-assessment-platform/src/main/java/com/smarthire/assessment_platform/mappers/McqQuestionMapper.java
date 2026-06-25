package com.smarthire.assessment_platform.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.smarthire.assessment_platform.dto.McqQuestionRequest;
import com.smarthire.assessment_platform.dto.McqQuestionResponse;
import com.smarthire.assessment_platform.dto.UpdateMcqQuestion;
import com.smarthire.assessment_platform.entities.McqQuestion;

@Mapper(componentModel = "spring")
public interface McqQuestionMapper {
	McqQuestion toEntity(McqQuestionRequest mcqQuestionRequest);
	
	McqQuestionResponse toDTO(McqQuestion mcqQuestion);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(UpdateMcqQuestion updateMcq,@MappingTarget McqQuestion mcqQuestion);
}
