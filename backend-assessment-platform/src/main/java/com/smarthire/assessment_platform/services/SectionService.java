package com.smarthire.assessment_platform.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smarthire.assessment_platform.dto.SectionRequest;
import com.smarthire.assessment_platform.dto.SectionResponse;
import com.smarthire.assessment_platform.dto.UpdateSectionRequest;
import com.smarthire.assessment_platform.entities.Exams;
import com.smarthire.assessment_platform.entities.Sections;
import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.enums.ExamStatus;
import com.smarthire.assessment_platform.enums.SectionType;
import com.smarthire.assessment_platform.exceptions.BadRequestException;
import com.smarthire.assessment_platform.exceptions.ResourceNotFoundException;
import com.smarthire.assessment_platform.mappers.SectionMapper;
import com.smarthire.assessment_platform.repository.ExamRepository;
import com.smarthire.assessment_platform.repository.SectionRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionService {
	private final SectionRepository sectionRepo;
	private final ExamService examService;
	private final ExamRepository examRepo;
	private final UserService userService;
	private final SectionMapper sectionMapper;
	
	@Transactional
	public SectionResponse createSection(@Valid SectionRequest sectionRequest, Long examId) {
		
		Sections section = sectionMapper.toEntity(sectionRequest);
		
		//Validate Exam Ownership and also add section for exam
		Exams exam = examRepo.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam Id not found"));
		Users user = userService.getCurrentUser();
		examService.validateExamOwnership(user, exam);
		
		if(ExamStatus.PUBLISHED.equals(exam.getStatus())) {
			throw new BadRequestException("Exam is already published , cannot add new section.");
		}
		
		//doSectionValidation(exam,section);
		if(sectionRepo.existsByExamAndOrderNo(exam, section.getOrderNo())){
			throw new BadRequestException("Given Order Number already exists for this exam");
		}
		
		if((SectionType.CODING.equals(section.getSectionType())) && sectionRepo.existsByExamAndSectionType(exam, SectionType.CODING)) {
			throw new BadRequestException("Coding Section is already defined for this exam, cannot create two Coding section.");
		}
		
		if(section.getDuration()<=0) {
			throw new BadRequestException("Duration Time must be positive");
		}
		
		exam.getSections().add(section);
		section.setExam(exam);
		
		//Finally saving section
		section = sectionRepo.save(section);
		
		return sectionMapper.toDTO(section);
	}

	@Transactional(readOnly = true)
	public List<SectionResponse> getAllSections(Long examId) {
		Exams exam = examRepo.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam Id not found"));
		Users user = userService.getCurrentUser();
		examService.validateExamOwnership(user, exam);
		
		List<Sections> allSections = sectionRepo.findAllByExam(exam);
		
		List<SectionResponse> sectionResponseList = new ArrayList<>();
		for(Sections section : allSections) {
			sectionResponseList.add(sectionMapper.toDTO(section));
		}
		
		return sectionResponseList;
	}

	@Transactional(readOnly = true)
	public SectionResponse getSectionById(Long id) {
		Sections section = sectionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section not found"));
		
		Users user = userService.getCurrentUser();

		Exams exam = section.getExam();

		examService.validateExamOwnership(user, exam);
		
		return sectionMapper.toDTO(section);
	}

	@Transactional
	public SectionResponse updateSectionById(UpdateSectionRequest sectionRequest,Long id) {
		Sections section = sectionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section not found"));
		
		Exams exam = section.getExam();
		
		Users user = userService.getCurrentUser();

		examService.validateExamOwnership(
		    user,
		    section.getExam()
		);
		
		//Once check the value we are updating is okay?
		//doSectionValidation(exam, sectionRequest);
		if((sectionRequest.getOrderNo()!= null) && sectionRepo.existsByExamAndOrderNoAndIdNot(exam, sectionRequest.getOrderNo(),section.getId())){
			throw new BadRequestException("Given Order Number already exists for this exam");
		}
		
		if((sectionRequest.getSectionType()!=null) && (SectionType.CODING.equals(sectionRequest.getSectionType())) && sectionRepo.existsByExamAndSectionTypeAndIdNot(exam, SectionType.CODING,section.getId())) {
			throw new BadRequestException("Coding Section is already defined for this exam, cannot create two Coding section.");
		}
		
		if((sectionRequest.getDuration()!=null) && sectionRequest.getDuration()<=0) {
			throw new BadRequestException("Duration Time must be positive");
		}
		
		if(ExamStatus.PUBLISHED.equals(exam.getStatus())) {
		    throw new BadRequestException(
		        "Published exam cannot be modified"
		    );
		}
		
		sectionMapper.updateEntity(sectionRequest,section);
		
		section = sectionRepo.save(section);
		return sectionMapper.toDTO(section);
	}
	
	
	@Transactional
	public void deleteSectionById(Long id) {
		Sections section = sectionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Section doesnt exist"));
		Users user = userService.getCurrentUser();

		examService.validateExamOwnership(
		    user,
		    section.getExam()
		);
		if(section.getExam().getStatus()== ExamStatus.PUBLISHED)
		{
			throw new BadRequestException("Exam is already published, cannot delete section now..");
		}
		sectionRepo.deleteById(id);
	}
	
}
