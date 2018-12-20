package com.sda.project.bookinglist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class BookinglistApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookinglistApplication.class, args);
	}

}

