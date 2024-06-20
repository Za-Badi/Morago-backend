package com.habsida.morago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.habsida.morago"})
public class MoragoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoragoBackendApplication.class, args);
    }

}
