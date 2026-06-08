package com.smarthire.assessment_platform.exceptions;

public class ForbiddenException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ForbiddenException(String message) {
		super(message);
	}
}
