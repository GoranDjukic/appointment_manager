package com.gdjukic.appointmentManager.web.response;

import java.time.LocalDateTime;

public class AppointmentResponse {

    private final Long id;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final boolean confirmed;
    private final ClientResponse client;

    public AppointmentResponse(
            Long id,
            LocalDateTime startTime,
            LocalDateTime endTime,
            boolean confirmed,
            String email
    ) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmed = confirmed;
        this.client = new ClientResponse(email);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public ClientResponse getClient() {
        return client;
    }
}
