package com.smarthire.assessment_platform.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamRequest {
	@NotBlank(message = "Title may not be blank")
	private String title;
	
	private String description;
	
	@NotNull(message = "Duration cannot be null")
	private Integer duration;
	
	@NotNull(message = "Start Time cannot be null")
	private LocalDateTime startTime;
	
	@NotNull(message = "End Time cannot be null")
	private LocalDateTime endTime;
}
