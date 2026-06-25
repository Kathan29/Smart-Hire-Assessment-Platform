package com.smarthire.assessment_platform.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="mcq_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class McqOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String optionText;
	
	@Column(nullable=false)
	private Boolean isCorrect;
	
	@ManyToOne 
	@JoinColumn(name = "question_id")
	private McqQuestion mcqQuestion;
}
