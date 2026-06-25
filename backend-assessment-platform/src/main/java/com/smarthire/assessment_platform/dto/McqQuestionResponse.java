package com.smarthire.assessment_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class McqQuestionResponse {
	private Long id;
	private String questionText;
	private Integer marks;
}
