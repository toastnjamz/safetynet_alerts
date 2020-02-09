package com.safetynet.alerts.exception;

public class MedicalRecordNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public MedicalRecordNotFoundException() {}

    public MedicalRecordNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}