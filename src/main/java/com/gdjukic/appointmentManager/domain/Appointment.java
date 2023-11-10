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


}
