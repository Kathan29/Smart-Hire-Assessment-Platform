package com.smarthire.assessment_platform.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgNotValid(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<>();
		
		ex.getBindingResult()
		  .getFieldErrors()
		  .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.badRequest().body(errors);
			
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex){
	    return ResponseEntity
	    		.status(HttpStatus.NOT_FOUND)
	    		.body(ex.getMessage());
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> handleBadRequest(BadRequestException ex){
	    return ResponseEntity
	    		.status(HttpStatus.BAD_REQUEST)
	    		.body(ex.getMessage());
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<String> handleForbiddenRequest(ForbiddenException ex){
	    return ResponseEntity
	    		.status(HttpStatus.FORBIDDEN)
	    		.body(ex.getMessage());
	}
}
