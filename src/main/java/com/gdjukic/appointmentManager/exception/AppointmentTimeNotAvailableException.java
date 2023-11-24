package com.gdjukic.appointmentManager.exception;

public class AppointmentTimeNotAvailableException extends RuntimeException{

    private final String message;

    public AppointmentTimeNotAvailableException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
