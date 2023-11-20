package com.gdjukic.appointmentManager.web.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentRequest {

    // todo time format(eg in which format it  is provided in the request)
    // todo email should be value object
    // todo think about other potential validations in service layer - eg client already has booked appointment for the same day?
    // todo What should be a max number of appointments per  client per day?
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private String clientEmail;

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
