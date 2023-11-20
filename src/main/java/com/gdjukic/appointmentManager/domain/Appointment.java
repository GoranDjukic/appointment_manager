package com.gdjukic.appointmentManager.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Appointment extends BaseEntity {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public static Appointment booked(LocalDateTime startTime, LocalDateTime endTime, Client client) {
        return new Appointment(
                startTime,
                endTime,
                true,
                client
        );
    }

    public Appointment() {
    }

    public Appointment(LocalDateTime startTime, LocalDateTime endTime, boolean confirmed, Client client) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.confirmed = confirmed;
        this.client = client;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
