package com.smarthire.assessment_platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smarthire.assessment_platform.entities.Exams;
import com.smarthire.assessment_platform.entities.Sections;
import com.smarthire.assessment_platform.enums.SectionType;

public interface SectionRepository extends JpaRepository<Sections, Long>{
	public boolean existsByExamAndOrderNoAndIdNot(Exams exam,Integer orderNo,Long id);
	public boolean existsByExamAndOrderNo(Exams exam,Integer orderNo);
	public boolean existsByExamAndSectionType(Exams exam,SectionType sectionType);
	public boolean existsByExamAndSectionTypeAndIdNot(Exams exam,SectionType sectionType,Long id);
	public List<Sections> findAllByExam(Exams exam);
}
