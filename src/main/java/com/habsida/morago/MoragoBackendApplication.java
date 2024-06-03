package com.habsida.morago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoragoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoragoBackendApplication.class, args);
    }

}
