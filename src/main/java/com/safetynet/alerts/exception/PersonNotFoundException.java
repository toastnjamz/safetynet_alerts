package com.safetynet.alerts.exception;

public class PersonNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public PersonNotFoundException() {}
	
	public PersonNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}