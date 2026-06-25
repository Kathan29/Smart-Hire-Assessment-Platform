package com.smarthire.assessment_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.assessment_platform.entities.McqQuestion;
import com.smarthire.assessment_platform.entities.Sections;

public interface McqQuestionRepository extends JpaRepository<McqQuestion, Long>{
	boolean existsByQuestionTextAndSection(String questionText,Sections section);

	List<McqQuestion> findAllBySection(Sections section);

	boolean existsByQuestionTextAndSectionAndIdNot(String questionText, Sections section, Long id);
}
