package com.gdjukic.appointmentManager.service.impl;

import com.gdjukic.appointmentManager.config.AppointmentConfiguration;
import com.gdjukic.appointmentManager.domain.Appointment;
import com.gdjukic.appointmentManager.domain.Client;
import com.gdjukic.appointmentManager.exception.GenericBadRequestException;
import com.gdjukic.appointmentManager.repository.AppointmentRepository;
import com.gdjukic.appointmentManager.repository.ClientRepository;
import com.gdjukic.appointmentManager.service.AppointmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final AppointmentConfiguration appointmentConfiguration;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            ClientRepository clientRepository,
            AppointmentConfiguration appointmentConfiguration
    ) {
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.appointmentConfiguration = appointmentConfiguration;
    }

    @Override
    public Page<Appointment> findByDate(LocalDate date, Pageable pageable) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        return appointmentRepository.findByRange(start, end, pageable);
    }

    @Override
    public Appointment create(LocalDateTime startTime, String clientEmail) {

        final LocalDateTime endTime = startTime.plusMinutes(appointmentConfiguration.getTime())
                .minusSeconds(1L);
        if (appointmentRepository.existsWithGivenRange(startTime, endTime)) {

            throw new GenericBadRequestException("Appointment exists within a given time range.");
        }
        final Client client = clientRepository.findByEmail(clientEmail)
                .orElse(clientRepository.save(Client.newClient(clientEmail)));

        final Appointment appointment = Appointment.booked(
                startTime,
                endTime,
                client
        );

        return appointmentRepository.save(appointment);
    }

}
