package com.smarthire.assessment_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
public class UpdateMcqOption {
	private String optionText;
	private Boolean isCorrect;
}
