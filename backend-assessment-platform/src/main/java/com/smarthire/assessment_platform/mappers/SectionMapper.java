package com.smarthire.assessment_platform.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.smarthire.assessment_platform.dto.SectionRequest;
import com.smarthire.assessment_platform.dto.SectionResponse;
import com.smarthire.assessment_platform.dto.UpdateSectionRequest;
import com.smarthire.assessment_platform.entities.Sections;

@Mapper(componentModel = "spring")
public interface SectionMapper {
	Sections toEntity(SectionRequest sectionRequest);
	
	SectionResponse toDTO(Sections section);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEntity(UpdateSectionRequest sectionRequest,@MappingTarget Sections existingSection);
}
