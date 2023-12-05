package com.gdjukic.appointmentManager.handler;

import com.gdjukic.appointmentManager.exception.AppointmentTimeNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppointmentTimeNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(AppointmentTimeNotAvailableException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),
                HttpStatus.BAD_REQUEST.name()),
                HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private final String message;
        private final String code;

        public ErrorResponse(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }
}
