package com.smarthire.assessment_platform.dto;

import com.smarthire.assessment_platform.enums.ExamStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResponse {
	private Long id;
	private String title;
	private String description;
	private ExamStatus status;
	private Integer duration;
}
