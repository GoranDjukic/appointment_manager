package com.gdjukic.appointmentManager.service;

import com.gdjukic.appointmentManager.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface AppointmentService {

    Page<Appointment> findByDate(LocalDate date, Pageable pageable);

    Appointment create(LocalDateTime startTime, String clientEmail);

}
