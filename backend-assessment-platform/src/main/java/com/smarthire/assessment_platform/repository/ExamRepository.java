package com.smarthire.assessment_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.assessment_platform.entities.Exams;

public interface ExamRepository extends JpaRepository<Exams, Long>{
	public List<Exams> findByCreatedById(Long id);
}
