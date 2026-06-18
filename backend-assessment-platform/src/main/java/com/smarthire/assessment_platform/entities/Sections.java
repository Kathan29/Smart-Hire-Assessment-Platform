package com.smarthire.assessment_platform.entities;

import com.smarthire.assessment_platform.enums.SectionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sections", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                    "exam_id",
                    "order_no"
                }
            )
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sections {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sectionName;
	private Integer duration;
	private Integer orderNo;
	
	@Enumerated(EnumType.STRING)
	private SectionType sectionType;
	
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exams exam;
}
