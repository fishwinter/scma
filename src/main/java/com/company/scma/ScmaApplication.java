package com.company.scma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScmaApplication.class, args);
    }

}
