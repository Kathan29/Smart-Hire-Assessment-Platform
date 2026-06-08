package com.smarthire.assessment_platform.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smarthire.assessment_platform.dto.CreateExamRequest;
import com.smarthire.assessment_platform.dto.ExamResponse;
import com.smarthire.assessment_platform.dto.UpdateExamRequest;
import com.smarthire.assessment_platform.services.ExamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coordinator/exams")
@RequiredArgsConstructor
public class ExamController {
	private final ExamService examService;
	
	@PostMapping
	public ResponseEntity<ExamResponse> createExam(@Valid @RequestBody CreateExamRequest examRequest) {	
		ExamResponse examResponse = examService.createExam(examRequest);
		return new ResponseEntity<ExamResponse>(examResponse,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ExamResponse>> getAllExams(){
		
		List<ExamResponse> examList = examService.getAllExams();
		
		return new ResponseEntity<List<ExamResponse>>(examList,HttpStatus.OK);
	}
	
	@GetMapping("/{examId}")
	public ResponseEntity<ExamResponse> getExamById(@PathVariable Long examId){
		
		ExamResponse exam = examService.getExamById(examId);
		
		return new ResponseEntity<ExamResponse>(exam,HttpStatus.OK);
	}
	
	@PutMapping("/{examId}")
	public ResponseEntity<ExamResponse> updateExamById(@PathVariable Long examId,@Valid @RequestBody UpdateExamRequest examRequest){
		
		ExamResponse exam = examService.updateExamById(examId,examRequest);
		
		return new ResponseEntity<ExamResponse>(exam,HttpStatus.OK);
	}
	
	@DeleteMapping("/{examId}")
	public ResponseEntity<String> deleteExamById(@PathVariable Long examId) {
		
		examService.deleteExamById(examId);
		return new ResponseEntity<String>("Exam Deleted Successfully",HttpStatus.OK);
		
	}
	
	@PutMapping("/{examId}/publish")
	public ResponseEntity<String> publishExamById(@PathVariable Long examId){
		
		examService.publishExamById(examId);
		return new ResponseEntity<String>("Exam published successfully",HttpStatus.OK);
	}
	
}
