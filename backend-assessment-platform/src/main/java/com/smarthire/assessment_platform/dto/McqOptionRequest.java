package com.smarthire.assessment_platform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class McqOptionRequest {
	@NotBlank(message = "Option Text cannot be blank")
	private String optionText;
	
	@NotNull(message = "Mark whether this is correct option or not.")
	private Boolean isCorrect;
}
