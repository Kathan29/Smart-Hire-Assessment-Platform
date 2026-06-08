package com.smarthire.assessment_platform.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateExamRequest {
	private Long id;
	private String title;
	private String description;
	private String duration;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
}
