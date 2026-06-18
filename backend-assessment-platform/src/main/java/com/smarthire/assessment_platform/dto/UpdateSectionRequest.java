package com.smarthire.assessment_platform.dto;

import com.smarthire.assessment_platform.enums.SectionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSectionRequest {
	private String sectionName;
	private Integer duration;
	private Integer orderNo;
	private SectionType sectionType;
}
