package com.gdjukic.appointmentManager.web.response;

public class ClientResponse {

    private final String email;

    public ClientResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
