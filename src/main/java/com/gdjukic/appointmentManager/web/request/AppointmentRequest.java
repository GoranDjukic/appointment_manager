package com.gdjukic.appointmentManager.web.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentRequest {

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
