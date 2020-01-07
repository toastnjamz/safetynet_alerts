package com.safetynet.alerts.exception;

public class DuplicatePersonException extends Exception {
	public DuplicatePersonException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
