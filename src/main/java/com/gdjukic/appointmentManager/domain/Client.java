package com.gdjukic.appointmentManager.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class Client extends BaseEntity {

    private String name;
    private String surname;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();
}
