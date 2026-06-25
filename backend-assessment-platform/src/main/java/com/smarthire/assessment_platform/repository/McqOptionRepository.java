package com.smarthire.assessment_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.assessment_platform.entities.McqOption;
import com.smarthire.assessment_platform.entities.McqQuestion;

public interface McqOptionRepository extends JpaRepository<McqOption,Long>{
	boolean existsByOptionTextAndMcqQuestion(String optionText,McqQuestion mcqQuestion);
}
