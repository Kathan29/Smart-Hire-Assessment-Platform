package com.smarthire.assessment_platform.dto;

import com.smarthire.assessment_platform.enums.SectionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequest {
	
	@NotBlank(message = "Section name cannot be blank")
	private String sectionName;
	
	@NotNull(message = "Duration cannot be null")
	private Integer duration;
	
	@NotNull(message = "Order Number cannot be null")
	private Integer orderNo;
	
	@NotNull(message = "Section Type cannot be null")
	private SectionType sectionType;
}
