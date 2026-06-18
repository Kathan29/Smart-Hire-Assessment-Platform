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

import com.smarthire.assessment_platform.dto.SectionRequest;
import com.smarthire.assessment_platform.dto.SectionResponse;
import com.smarthire.assessment_platform.dto.UpdateSectionRequest;
import com.smarthire.assessment_platform.services.SectionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/coordinator")
@RequiredArgsConstructor
public class SectionController {
	
	private final SectionService sectionService;
	
	@PostMapping("/exams/{examId}/sections")
	public ResponseEntity<SectionResponse> createSection(@Valid @RequestBody SectionRequest sectionRequest,@PathVariable Long examId){
			SectionResponse sectionResponse = sectionService.createSection(sectionRequest,examId);
			return new ResponseEntity<SectionResponse>(sectionResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/exams/{examId}/sections")
	public ResponseEntity<List<SectionResponse>> getSectionsByExam(@PathVariable Long examId){
		List<SectionResponse> sectionResponseList = sectionService.getAllSections(examId);
		return new ResponseEntity<List<SectionResponse>>(sectionResponseList,HttpStatus.OK);
	}
	
	@GetMapping("/sections/{id}")
	public ResponseEntity<SectionResponse> getSectionById(@PathVariable Long id){
		SectionResponse sectionResponse = sectionService.getSectionById(id);
		return new ResponseEntity<SectionResponse>(sectionResponse,HttpStatus.OK);
	}
	
	@PutMapping("/sections/{id}")
	public ResponseEntity<SectionResponse> updateSectionById(@RequestBody UpdateSectionRequest sectionRequest,@PathVariable Long id){
		SectionResponse sectionResponse = sectionService.updateSectionById(sectionRequest,id);
		return new ResponseEntity<SectionResponse>(sectionResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/sections/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		sectionService.deleteSectionById(id);
		return new ResponseEntity<String>("Section deleted successfully",HttpStatus.OK);
	}
}
