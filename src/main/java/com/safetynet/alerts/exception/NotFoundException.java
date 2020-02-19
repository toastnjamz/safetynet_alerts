package com.safetynet.alerts.exception;

public class NotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public NotFoundException() {}
	
	public NotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}