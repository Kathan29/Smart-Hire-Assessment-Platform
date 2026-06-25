package com.smarthire.assessment_platform.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UpdateMcqQuestion {
	private String questionText;
	@Min(1)
	private Integer marks;
}
