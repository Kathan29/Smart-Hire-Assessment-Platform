package com.smarthire.assessment_platform.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smarthire.assessment_platform.dto.CreateExamRequest;
import com.smarthire.assessment_platform.dto.ExamResponse;
import com.smarthire.assessment_platform.dto.UpdateExamRequest;
import com.smarthire.assessment_platform.entities.Exams;
import com.smarthire.assessment_platform.entities.Users;
import com.smarthire.assessment_platform.enums.ExamStatus;
import com.smarthire.assessment_platform.enums.UserRoles;
import com.smarthire.assessment_platform.exceptions.BadRequestException;
import com.smarthire.assessment_platform.exceptions.ForbiddenException;
import com.smarthire.assessment_platform.exceptions.ResourceNotFoundException;
import com.smarthire.assessment_platform.mappers.ExamMapper;
import com.smarthire.assessment_platform.repository.ExamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamService {
	private final ExamRepository examRepo;
	private final ExamMapper examMapper;
	private final UserService userService;

	@Transactional
	public ExamResponse createExam(CreateExamRequest examRequest) {
		
		if(examRequest.getEndTime().isBefore(examRequest.getStartTime())) {
			throw new BadRequestException("Start time must be before end time");
		}
		
		Users user = userService.getCurrentUser();
		
		Exams exam = examMapper.toEntity(examRequest);
		exam.setCreatedBy(user);
		exam.setStatus(ExamStatus.DRAFT);
		exam  = examRepo.save(exam);
		//user.getExams().add(exam);
		
		return examMapper.toDTO(exam);
	}

	@Transactional(readOnly = true)
	public List<ExamResponse> getAllExams() {
		Users user = userService.getCurrentUser();;
		
		List<Exams> examList = examRepo.findByCreatedById(user.getId());
		
		List<ExamResponse> examResponseList = new ArrayList<>();
		for(Exams exam : examList) {
			examResponseList.add(examMapper.toDTO(exam));
		}
		return examResponseList;
	}

	@Transactional(readOnly = true)
	public ExamResponse getExamById(Long id) {
		Users user = userService.getCurrentUser();
		
		Exams exam = examRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam doesnt exist"));
		
		//Coordinator who didnt created exam cant access, admin can access everything
		validateExamOwnership(user, exam);
		
		return examMapper.toDTO(exam);
	}

	@Transactional
	public ExamResponse updateExamById(Long id,UpdateExamRequest examRequest) {
		
		Users user = userService.getCurrentUser();
		
		Exams existingExam = examRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam doesnt exist"));
		
		validateExamOwnership(user, existingExam);
		
		//Once checking endDate and startDate
		LocalDateTime effectiveStart = (examRequest.getStartTime() != null) ? examRequest.getStartTime() : existingExam.getStartTime();
		LocalDateTime effectiveEnd = (examRequest.getEndTime() != null) ? examRequest.getEndTime() : existingExam.getEndTime();

		if (effectiveEnd.isBefore(effectiveStart)) {
		    throw new BadRequestException("End time must be after start time");
		}
		
		if(existingExam.getStatus() == ExamStatus.PUBLISHED){
		    throw new BadRequestException(
		        "Published exam cannot be modified"
		    );
		}
		
		examMapper.updateEntityFromDTO(examRequest, existingExam);
		
		return examMapper.toDTO(existingExam);
	}

	@Transactional
	public void deleteExamById(Long examId) {
		Users user = userService.getCurrentUser();
		
		Exams existingExam = examRepo.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam doesnt exist"));
		
		validateExamOwnership(user, existingExam);
		
		examRepo.delete(existingExam);
	}

	@Transactional
	public void publishExamById(Long examId) {
		Users user = userService.getCurrentUser();
		
		Exams existingExam = examRepo.findById(examId).orElseThrow(() -> new ResourceNotFoundException("Exam doesnt exist"));
		
		validateExamOwnership(user, existingExam);
		
		if(existingExam.getStatus()==ExamStatus.PUBLISHED) {
			throw new BadRequestException("Exam already published");
		}
			
		existingExam.setStatus(ExamStatus.PUBLISHED);
	}
	
	public void validateExamOwnership(Users user,Exams exam) {
		if(!UserRoles.ADMIN.equals(user.getRoles()) &&
				!(exam.getCreatedBy().getId().equals(user.getId()))) {
			throw new ForbiddenException("You are not authorized to access this exam");
		}
	}

}
