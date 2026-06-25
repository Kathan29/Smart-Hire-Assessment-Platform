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

import com.smarthire.assessment_platform.dto.McqQuestionRequest;
import com.smarthire.assessment_platform.dto.McqQuestionResponse;
import com.smarthire.assessment_platform.dto.UpdateMcqQuestion;
import com.smarthire.assessment_platform.services.McqQuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coordinator")
@RequiredArgsConstructor
public class McqQuestionController {
	
	private final McqQuestionService mcqQuestionService;
	
	@PostMapping("/sections/{sectionId}/questions")
	public ResponseEntity<McqQuestionResponse> createQuestion(@Valid @RequestBody McqQuestionRequest mcqQuestionRequest,
			@PathVariable Long sectionId){
		McqQuestionResponse questionResponse = mcqQuestionService.createQuestion(mcqQuestionRequest,sectionId);
		return new ResponseEntity<McqQuestionResponse>(questionResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/sections/{sectionId}/questions")
	public ResponseEntity<List<McqQuestionResponse>> getAllQuestionForSection(@PathVariable Long sectionId){
		List<McqQuestionResponse> questionResponseList = mcqQuestionService.getAllQuestionForSection(sectionId);
		return new ResponseEntity<List<McqQuestionResponse>>(questionResponseList,HttpStatus.OK);
	}
	
	@GetMapping("/questions/{id}")
	public ResponseEntity<McqQuestionResponse> getQuestionById(@PathVariable Long id){
		McqQuestionResponse questionResponse = mcqQuestionService.getQuestionById(id);
		return new ResponseEntity<McqQuestionResponse>(questionResponse,HttpStatus.OK);
	}
	
	@PutMapping("/questions/{id}")
	public ResponseEntity<McqQuestionResponse> updateQuestionById(@RequestBody UpdateMcqQuestion updatedMcqQuestion,@PathVariable Long id){
		McqQuestionResponse updatedResponse = mcqQuestionService.updateQuestionById(updatedMcqQuestion, id);
		return new ResponseEntity<McqQuestionResponse>(updatedResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/questions/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable Long id){
		mcqQuestionService.deleteQuestionById(id);
		return new ResponseEntity<String>("Question Deleted Successfully",HttpStatus.OK);
	}

}
