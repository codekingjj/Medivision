package com.medivision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MedivisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedivisionApplication.class, args);
    }

}
