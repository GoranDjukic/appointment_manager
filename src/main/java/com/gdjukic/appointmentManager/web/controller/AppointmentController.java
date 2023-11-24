package com.gdjukic.appointmentManager.web.controller;

import com.gdjukic.appointmentManager.domain.Appointment;
import com.gdjukic.appointmentManager.service.AppointmentService;
import com.gdjukic.appointmentManager.web.request.AppointmentRequest;
import com.gdjukic.appointmentManager.web.response.AppointmentResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/date")
    public Page<AppointmentResponse> findByRange(@RequestParam LocalDate date, Pageable pageable) {
        return appointmentService.findByDate(date,pageable)
                .map(appointment -> new AppointmentResponse(
                appointment.getId(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.isConfirmed(),
                appointment.getClient().getEmail()
        ));
    }

    @PostMapping
    public AppointmentResponse create(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        final Appointment appointment = appointmentService.create(
                appointmentRequest.getStartTime(),
                appointmentRequest.getClientEmail()
        );

        return new AppointmentResponse(
                appointment.getId(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.isConfirmed(),
                appointment.getClient().getEmail()
        );
    }

}
