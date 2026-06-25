package com.smarthire.assessment_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqQuestionRequest {
	@NotBlank(message = "Question text cannot be blank")
	private String questionText;
	
	@NotNull(message = "Marks cannot be null, minimum marks is 1.")
	private Integer marks;
}
