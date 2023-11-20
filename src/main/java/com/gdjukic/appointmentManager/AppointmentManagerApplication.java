package com.gdjukic.appointmentManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AppointmentManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentManagerApplication.class, args);
	}

}
