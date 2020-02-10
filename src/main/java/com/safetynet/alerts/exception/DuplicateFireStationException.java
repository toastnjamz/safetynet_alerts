package com.safetynet.alerts.exception;

public class DuplicateFireStationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public DuplicateFireStationException() {}

    public DuplicateFireStationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
