package com.smarthire.assessment_platform.entities;

import java.time.LocalDateTime;

import com.smarthire.assessment_platform.enums.ExamStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exams {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	private String description;
	
	private Integer duration;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	@Enumerated(EnumType.STRING)
	private ExamStatus status;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
	private Users createdBy;
}
