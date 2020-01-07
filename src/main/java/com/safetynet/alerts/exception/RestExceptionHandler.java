package com.safetynet.alerts.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.alerts.exception.DuplicatePersonException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ DuplicatePersonException.class })
	protected ResponseEntity<Object> handleDuplicatePersonException(
			Exception ex, WebRequest request) {
		return new ResponseEntity<Object>("Person already exists.",
				new HttpHeaders(), HttpStatus.CONFLICT);
	}

}
