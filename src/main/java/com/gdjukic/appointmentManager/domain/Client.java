package com.gdjukic.appointmentManager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends BaseEntity {

    private String email;

    @OneToMany(mappedBy = "client")
    private List<Appointment> appointments = new ArrayList<>();

    public static Client newClient(String email) {
        return new Client(email, new ArrayList<>());
    }

    public Client() {
    }

    public Client(String email, List<Appointment> appointments) {
        this.email = email;
        this.appointments = appointments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

}
