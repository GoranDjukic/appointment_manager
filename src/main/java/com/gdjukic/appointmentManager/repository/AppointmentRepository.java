package com.gdjukic.appointmentManager.repository;

import com.gdjukic.appointmentManager.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select a from Appointment a where " +
            "a.startTime >= :start and a.startTime <= :end " +
            "and a.endTime >= :start and a.endTime <= :end")
    Page<Appointment> findByRange(LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query(value = "select count(a.id) > 0 from Appointment a where " +
            "a.startTime >= :start and a.startTime <= :end " +
            "and a.endTime >= :start and a.endTime <= :end")
    Boolean existsWithGivenRange(LocalDateTime start, LocalDateTime end);
}
