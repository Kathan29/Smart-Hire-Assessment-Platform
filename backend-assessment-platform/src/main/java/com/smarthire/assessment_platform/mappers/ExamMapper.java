package com.smarthire.assessment_platform.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.smarthire.assessment_platform.dto.CreateExamRequest;
import com.smarthire.assessment_platform.dto.ExamResponse;
import com.smarthire.assessment_platform.dto.UpdateExamRequest;
import com.smarthire.assessment_platform.entities.Exams;

@Mapper(componentModel = "spring")
public interface ExamMapper {
	
	Exams toEntity(CreateExamRequest examReq);
	
	ExamResponse toDTO(Exams exam);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntityFromDTO(UpdateExamRequest examReq,@MappingTarget Exams existingExam);
}
