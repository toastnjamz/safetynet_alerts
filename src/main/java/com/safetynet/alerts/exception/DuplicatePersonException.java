package com.safetynet.alerts.exception;

public class DuplicatePersonException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public DuplicatePersonException() {}
	
	public DuplicatePersonException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}