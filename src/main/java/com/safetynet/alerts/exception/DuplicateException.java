package com.safetynet.alerts.exception;

public class DuplicateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public DuplicateException() {}
	
	public DuplicateException(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}