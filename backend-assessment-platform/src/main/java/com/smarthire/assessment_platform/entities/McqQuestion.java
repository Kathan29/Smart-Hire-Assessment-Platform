package com.smarthire.assessment_platform.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mcq_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String questionText;
	
	@ManyToOne
	@JoinColumn(name = "section_id")
	private Sections section;
	
	@Min(1)
	private Integer marks;
	
	@OneToMany(mappedBy = "mcqQuestion",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<McqOption> mcqOptions = new ArrayList<>();
	
	
}
