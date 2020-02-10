package com.safetynet.alerts.exception;

public class FireStationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public FireStationNotFoundException() {}

    public FireStationNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
