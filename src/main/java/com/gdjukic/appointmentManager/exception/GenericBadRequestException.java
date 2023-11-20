package com.gdjukic.appointmentManager.exception;

public class GenericBadRequestException extends RuntimeException{

    private final String message;

    public GenericBadRequestException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
