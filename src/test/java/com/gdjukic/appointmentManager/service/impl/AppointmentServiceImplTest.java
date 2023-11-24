package com.gdjukic.appointmentManager.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDateTime;
import java.util.Optional;

import com.gdjukic.appointmentManager.config.AppointmentDurationConfiguration;
import com.gdjukic.appointmentManager.exception.AppointmentTimeNotAvailableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gdjukic.appointmentManager.domain.Appointment;
import com.gdjukic.appointmentManager.domain.Client;
import com.gdjukic.appointmentManager.repository.AppointmentRepository;
import com.gdjukic.appointmentManager.repository.ClientRepository;

public class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private AppointmentDurationConfiguration appointmentConfiguration;

    @InjectMocks
    AppointmentServiceImpl appointmentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        given(appointmentConfiguration.getTime()).willReturn(10);

    }

    @Test
    void shouldCreateAppointmentWithClientCreation() {
        // given
        final LocalDateTime startTime = LocalDateTime.now();
        final String clientEmail = "test@test.com";
        final Client client = Client.newClient(clientEmail);

        given(appointmentRepository.existsWithGivenRange(any(), any())).willReturn(false);
        given(clientRepository.findByEmail(clientEmail)).willReturn(Optional.empty());
        given(appointmentRepository.save(any())).willReturn(Appointment.booked(
                startTime,
                startTime.plusMinutes(10L),
                client
            )
        );

        // when
        Appointment appointment = appointmentService.create(startTime, clientEmail);

        // then
        assertEquals(clientEmail, appointment.getClient().getEmail());
        verify(appointmentRepository, times(1)).existsWithGivenRange(any(), any());
        verify(clientRepository, times(1)).findByEmail(anyString());
        verify(clientRepository, times(1)).save(any());
        verify(appointmentRepository, times(1)).save(any());
        verify(appointmentConfiguration, times(1)).getTime();
        verifyNoMoreInteractions(appointmentRepository, clientRepository);
    }

    @Test
    void shouldCreateAppointment() {
        // given
        final LocalDateTime startTime = LocalDateTime.now();
        final String clientEmail = "test@test.com";
        final Client client = Client.newClient(clientEmail);

        given(appointmentRepository.existsWithGivenRange(any(), any())).willReturn(false);
        given(clientRepository.findByEmail(anyString())).willReturn(Optional.of(client));
        given(appointmentRepository.save(any())).willReturn(Appointment.booked(
                startTime,
                startTime.plusMinutes(10L),
                client
            )
        );

        // when
        Appointment appointment = appointmentService.create(startTime, clientEmail);

        // then
        assertEquals(clientEmail, appointment.getClient().getEmail());
        verify(appointmentRepository, times(1)).existsWithGivenRange(any(), any());
        verify(clientRepository, times(1)).findByEmail(anyString());
        verify(clientRepository, times(1)).save(any());
        verify(appointmentRepository, times(1)).save(any());
        verify(appointmentConfiguration, times(1)).getTime();
        verifyNoMoreInteractions(appointmentRepository, clientRepository);
    }

    @Test
    void shouldThrowExceptionIfAppointmentInGivenRangeAlreadyExists() {
        // given
        given(appointmentRepository.existsWithGivenRange(any(), any())).willReturn(true);

        // when / then
        final AppointmentTimeNotAvailableException ex = Assertions.assertThrows(
            AppointmentTimeNotAvailableException.class,
            () -> appointmentService.create(LocalDateTime.now(), "test@test.com")
        );
        assertEquals("The appointment with the given time is already taken.", ex.getMessage());
    }

}
