package com.safetynet.alerts.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.safetynet.alerts.exception.DuplicatePersonException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
//	@ExceptionHandler({ DuplicatePersonException.class })
//	protected ResponseEntity<Object> handleDuplicatePersonException(
//			Exception e, WebRequest request) {
//		return new ResponseEntity<Object>("Person already exists.",
//				new HttpHeaders(), HttpStatus.CONFLICT);
//	}
	
//	@ResponseStatus(HttpStatus.CONFLICT)
//	@ExceptionHandler({ DuplicatePersonException.class })
//	public void handle(DuplicatePersonException errorMessage) {}
//
	@ResponseBody
	@ExceptionHandler(value = DuplicatePersonException.class)
	public ResponseEntity<?> handleDuplicatePersonException(DuplicatePersonException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getErrorMessage());
	}
}
