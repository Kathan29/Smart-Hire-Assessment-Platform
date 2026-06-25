package com.smarthire.assessment_platform.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smarthire.assessment_platform.dto.McqQuestionRequest;
import com.smarthire.assessment_platform.dto.McqQuestionResponse;
import com.smarthire.assessment_platform.dto.UpdateMcqQuestion;
import com.smarthire.assessment_platform.entities.Exams;
import com.smarthire.assessment_platform.entities.McqQuestion;
import com.smarthire.assessment_platform.entities.Sections;
import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.enums.ExamStatus;
import com.smarthire.assessment_platform.enums.SectionType;
import com.smarthire.assessment_platform.exceptions.BadRequestException;
import com.smarthire.assessment_platform.exceptions.ResourceNotFoundException;
import com.smarthire.assessment_platform.mappers.McqQuestionMapper;
import com.smarthire.assessment_platform.repository.McqQuestionRepository;
import com.smarthire.assessment_platform.repository.SectionRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class McqQuestionService {
	private final McqQuestionRepository questionRepo;
	private final UserService userService;
	private final McqQuestionMapper mcqQuestionMapper;
	private final SectionRepository sectionRepo;
	private final ExamService examService;

	@Transactional
	public McqQuestionResponse createQuestion(@Valid McqQuestionRequest mcqQuestionRequest, Long sectionId) {
		McqQuestion mcqQuestion = mcqQuestionMapper.toEntity(mcqQuestionRequest);
		Sections section = sectionRepo.findById(sectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Section id not found"));
		
		Exams exam = section.getExam();
		validateOwnership(exam);
		
		if(questionRepo.existsByQuestionTextAndSection(mcqQuestion.getQuestionText(), section)) {
			throw new BadRequestException("Question already exists for this section.");
		}
		
		if(section.getSectionType().equals(SectionType.CODING)) {
			throw new BadRequestException("This question cannot be added in coding section");
		}
		
		if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
			throw new BadRequestException("Exam is already published, cannot add new question now");
		}
		
		mcqQuestion.setSection(section);
		section.getMcqQuestions().add(mcqQuestion);
		
		mcqQuestion = questionRepo.save(mcqQuestion);
		return mcqQuestionMapper.toDTO(mcqQuestion);
	}

	@Transactional(readOnly = true)
	public List<McqQuestionResponse> getAllQuestionForSection(Long sectionId) {
		Sections section = sectionRepo.findById(sectionId)
				.orElseThrow(() -> new ResourceNotFoundException("Section id not found"));
		
		validateOwnership(section.getExam());
		
		List<McqQuestion> questionList = questionRepo.findAllBySection(section);
		
		List<McqQuestionResponse> questionResponseList = new ArrayList<>();
		for (McqQuestion mcqQuestion : questionList) {
			questionResponseList.add(mcqQuestionMapper.toDTO(mcqQuestion));
		}
		return questionResponseList;
	}
	
	@Transactional(readOnly = true)
	public McqQuestionResponse getQuestionById(Long id) {
		McqQuestion mcqQuestion = questionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Given question id doesnt exist"));
		
		Exams exam = mcqQuestion.getSection().getExam();
		validateOwnership(exam);
		
		return mcqQuestionMapper.toDTO(mcqQuestion);
	}
	
	@Transactional
	public McqQuestionResponse updateQuestionById(UpdateMcqQuestion updatedQuestion,Long id) {
		McqQuestion existingQuestion = questionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Given question id doesnt exist"));
		
		Sections section = existingQuestion.getSection();
		Exams exam = section.getExam();
		validateOwnership(exam);
		
		if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
			throw new BadRequestException("Exam is already published, now we cannot update question");
		}
		
		if(updatedQuestion.getQuestionText()!=null && questionRepo.existsByQuestionTextAndSectionAndIdNot(updatedQuestion.getQuestionText(), section,id)) {
			throw new BadRequestException("Similar question already exists..");
		}
		
		mcqQuestionMapper.updateEntity(updatedQuestion, existingQuestion);
		
		existingQuestion = questionRepo.save(existingQuestion);
		return mcqQuestionMapper.toDTO(existingQuestion);
	}
	
	@Transactional
	public void deleteQuestionById(Long id) {
		McqQuestion mcqQuestion = questionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Given question id doesnt exist"));
		
		Sections section = mcqQuestion.getSection();
		Exams exam = section.getExam();
		validateOwnership(exam);
		
		if(exam.getStatus().equals(ExamStatus.PUBLISHED)) {
			throw new BadRequestException("Exam is already published, now we cannot delete question");
		}
		
		questionRepo.deleteById(id);
	}
	
	private void validateOwnership(Exams exam) {
		Users user = userService.getCurrentUser();
		examService.validateExamOwnership(user, exam);
	}
}
