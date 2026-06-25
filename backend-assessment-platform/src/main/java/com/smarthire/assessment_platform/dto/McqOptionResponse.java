package com.smarthire.assessment_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class McqOptionResponse {
	private Long id;
	private String optionText;
	private Boolean isCorrect;
}
