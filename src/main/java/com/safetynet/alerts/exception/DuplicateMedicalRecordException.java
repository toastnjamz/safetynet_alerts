package com.safetynet.alerts.exception;

public class DuplicateMedicalRecordException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public DuplicateMedicalRecordException() {}

    public DuplicateMedicalRecordException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
